package mx.com.nmp.pagos.mimonte.services.impl;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.PagoBuilder;
import mx.com.nmp.pagos.mimonte.builder.TarjetaBuilder;
import mx.com.nmp.pagos.mimonte.constans.EstatusOperacion;
import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;
import mx.com.nmp.pagos.mimonte.dao.PagoRepository;
import mx.com.nmp.pagos.mimonte.dss.DSSModule;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusPagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;
import mx.com.nmp.pagos.mimonte.exception.TarjetaException;
import mx.com.nmp.pagos.mimonte.exception.TarjetaIdentifierException;
import mx.com.nmp.pagos.mimonte.model.Pago;
import mx.com.nmp.pagos.mimonte.services.ClienteService;
import mx.com.nmp.pagos.mimonte.services.PagoService;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;
import mx.com.nmp.pagos.mimonte.util.validacion.ValidadorDatosPago;

/**
 * Nombre: PagoServiceImpl Descripcion: Clase que realiza el pago de partidas /
 * contratos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 20/11/2018 16:22 hrs.
 * @version 0.1
 */
@Service("pagoServiceImpl")
public class PagoServiceImpl implements PagoService {

	/**
	 * Service de tarjetas para almacenar la tarjeta si asi se requiere
	 */
	@Autowired
	private TarjetasService tarjetaService;

	/**
	 * Service de clientes para obtener los datos del cliente para agregar a la
	 * tarjeta a guardar
	 */
	@Autowired
	private ClienteService clienteService;

	/**
	 * Service de pagos para registrar el pago en bd
	 */
	@Autowired
	private PagoRepository pagoRepository;

	@Autowired
	@SuppressWarnings("unused")
	private DSSModule dssModule;

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PagoServiceImpl.class);

	@Value(PagoConstants.MAXIMUM_AMOUNT_OF_CARDS_PROPERTY)
	private Integer MAXIMUM_AMOUNT_OF_CARDS_PER_CLIENT;

	/**
	 * 
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws SQLDataException
	 * @throws                       @throws @throws @throws
	 *                               DataIntegrityViolationException
	 *
	 */
	@Override
	public PagoResponseDTO savePago(PagoRequestDTO pagoRequestDTO)
			throws DataIntegrityViolationException, NumberFormatException, SQLDataException, SQLException {
		LOG.debug("Ingreso al servicio: savePago(PagoRequestDTO pagoRequestDTO)");
		PagoResponseDTO pagoResponseDTO = new PagoResponseDTO();
		List<EstatusPagoResponseDTO> estatusPagos = new ArrayList<>();
		pagoResponseDTO.setIdTipoAfiliacion(getRandomNumber());
		pagoResponseDTO.setTipoAutorizacion(new TipoAutorizacionDTO(2, "3DSecure"));

		// Esta seccion comentada es la invocacion al modulo DSS
		LOG.debug("Intentando obtener un numero de afiliacion");
		// DSS invocation
//		Map<String, Object> mapResult = dssModule.getNoAfiliacion(pagoRequestDTO);
//		pagoResponseDTO.setIdTipoAfiliacion(null != mapResult && !mapResult.isEmpty()
//				? Integer.parseInt(String.valueOf(mapResult.get(PagoConstants.ID_AFILIACION_MAPPING_NAME)))
//				: null);
//		pagoResponseDTO.setTipoAutorizacion(null != mapResult && !mapResult.isEmpty()
//				? (TipoAutorizacionDTO) mapResult.get(PagoConstants.TIPO_AUTORIZACION_MAPPING_NAME)
//				: null);

		LOG.debug("Se validara objeto pagoRequestDTO");
		ValidadorDatosPago.validacionesInicialesPago(pagoRequestDTO);
		try {
			ValidadorDatosPago.doTypeValidations(pagoRequestDTO);
		} catch (PagoException pex) {
			throw new PagoException(pex.getMessage());
		}
		// Comienzan validaciones de tarjetas
		Boolean flagOkCardData = false;
		LOG.debug("Se inician validaciones respecto a objeto pagoRequestDTO.getTarjeta()");
		if (validaSiGuardar(pagoRequestDTO)) {
			if (validaCantidadTarjetasExistentes(pagoRequestDTO)) {
				validaDatos(pagoRequestDTO.getTarjeta());
				flagOkCardData = true;
			} else {
				LOG.info(PagoConstants.MSG_CARD_WAS_NOT_SAVED);
			}
		}
		// Finalizan validaciones de tarjeta
		// Inicia validacion de id transaccion
		Integer flag = 0;
		flag = pagoRepository.countByIdTransaccionMidas(Integer.parseInt(pagoRequestDTO.getIdTransaccionMidas()));
		// Finaliza validacion de id transaccion
		ClienteDTO cl = clienteService.getClienteById(pagoRequestDTO.getIdCliente());
		if (null == cl)
			cl = clienteService.saveCliente(new ClienteDTO(pagoRequestDTO.getIdCliente(), null, new Date()));
		// Se realizan validacion propias del negocio
		LOG.debug("Se inician validaciones respecto a objeto pagoRequestDTO.getTarjeta()");
		if (flagOkCardData && (null != flag && flag == 0)) {
			try {
				tarjetaService
						.addTarjetas(TarjetaBuilder.buildTarjetaDTOFromTarjetaPagoDTO(pagoRequestDTO.getTarjeta(), cl));
			} catch (TarjetaException tex) {
				// A qui se evalua si el tipo de excepcion es de identificador de tarjeta
				// (idopenpay) existente, entonces el flujo sigue, de lo contrario el flujo se
				// detiene
				if (tex instanceof TarjetaIdentifierException)
					LOG.info(TarjetaConstants.MSG_TARJETAS_ERROR);
				else {
					LOG.info(tex.getMessage());
					throw new TarjetaException(tex.getMessage());
				}
			}
		}
		Pago pago = new Pago();
		if (null != pagoRequestDTO.getOperaciones() && !pagoRequestDTO.getOperaciones().isEmpty()) {
			LOG.debug("Se iteraran operaciones dentro de pagoRequestDTO");
			if (null != flag && flag == 0) {
				for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
					try {
						pago = PagoBuilder.buildPagoFromObject(operacion, pagoRequestDTO.getTarjeta(), cl,
								pagoRequestDTO.getIdTransaccionMidas());
						pagoRepository.save(pago);
						// Los estatus siguientes EstatusOperacion.XXXX son identicos a los del catalogo
						// de estatus de transaccion de la base de datos, se hace por medio de un enum
						// para
						// quitar carga de trabajo al servidor
						estatusPagos.add(new EstatusPagoResponseDTO(
								EstatusOperacion.SUCCESSFUL_STATUS_OPERATION.getId(), operacion.getFolioContrato()));
						LOG.debug("Se agrego operacion correcta: " + operacion);
					} catch (Exception ex) {
						estatusPagos.add(new EstatusPagoResponseDTO(EstatusOperacion.FAIL_STATUS_OPERATION.getId(),
								operacion.getFolioContrato()));
						LOG.error("Se agrego operacion fallida: " + operacion);
						LOG.error(ex.getMessage());
					}
				}
			} else if (null == flag) {
				throw new PagoException(PagoConstants.MSG_CAN_NO_CHECK_IF_PAGO_EXISTS);
			} else {
				throw new PagoException(PagoConstants.TRANSACTION_ID_ALREADY_EXISTS);
			}
			pagoResponseDTO.setExitoso(true);
		} else {
			LOG.error("Objeto pagoRequestDTO.getOperaciones() es nulo o es vacio!");
			pagoResponseDTO.setExitoso(false);
			throw new PagoException(PagoConstants.NO_OPERATIONS_MESSAGE);
		}

		pagoResponseDTO.setEstatusPagos(estatusPagos);
		return pagoResponseDTO;
	}

	/**
	 * Metodo que recibe un objeto pagoDTO y valida si se debe guardar la tarjeta
	 * que contiene
	 * 
	 * @param pagoDTO Objeto PagoRequestDTO con un objeto tarjeta dentro
	 * @return Valor boolean indicando si se debe guardar la tarjeta
	 */
	private static final boolean validaSiGuardar(PagoRequestDTO pagoDTO) {
		boolean flag = false;
		if (null != pagoDTO && pagoDTO.getGuardaTarjeta())
			flag = true;
		LOG.debug("El resultado de: validaSiGuardar(PagoRequestDTO) es: " + flag);
		return flag;
	}

	/**
	 * 
	 * Metodo que recibe un objeto PagoRequestDTO y valida su contenido para saber
	 * todos los campos de la tarjeta son validos
	 * 
	 * @param tarjetaPagoDTO
	 */
	private static final void validaDatos(TarjetaPagoDTO tarjetaPagoDTO) {
		LOG.debug("Inicia metodo: validaDatos(TarjetaPagoDTO)");
		ValidadorDatosPago.validacionesTrajeta(tarjetaPagoDTO);
	}

	/**
	 * Metodo que valida si ya existe la cantidad maxima de tarjetas en el sistema
	 * que el cliente puede tener
	 * 
	 * @return Valor boleanpo que indica con true si el usuario puede agregar la
	 *         tarjeta o false si no puede realizar dicha accion
	 */
	private boolean validaCantidadTarjetasExistentes(PagoRequestDTO pagoDTO) {
		boolean flag = false;
		int cantidadTarjetas = tarjetaService.countTarjetasByIdcliente(pagoDTO.getIdCliente());
		if (cantidadTarjetas < MAXIMUM_AMOUNT_OF_CARDS_PER_CLIENT)
			flag = true;
		LOG.debug("El resultado del metodo validaCantidadTarjetasExistentes(PagoRequestDTO) es: " + flag);
		return flag;
	}

	/**
	 * Metodo que regresa un numero aleatorio entre 1 y 3 simulando la tomade
	 * decicion para un numero de afiliacion
	 * 
	 * @return int value
	 */
	private static int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(3 - 1 + 1) + 1;
	}

}
