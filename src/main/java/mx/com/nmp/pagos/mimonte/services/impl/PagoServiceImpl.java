/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.impl;

import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.PagoBuilder;
import mx.com.nmp.pagos.mimonte.builder.TarjetaBuilder;
import mx.com.nmp.pagos.mimonte.constans.EstatusOperacion;
import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.constans.TarjetaConstants;
import mx.com.nmp.pagos.mimonte.dao.PagoRepository;
import mx.com.nmp.pagos.mimonte.dss.DSSModule;
import mx.com.nmp.pagos.mimonte.dto.BayonetReglasDTO;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.EstatusPagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoAutorizacionDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;
import mx.com.nmp.pagos.mimonte.exception.TarjetaException;
import mx.com.nmp.pagos.mimonte.exception.TarjetaIdentifierException;
import mx.com.nmp.pagos.mimonte.model.Pago;
import mx.com.nmp.pagos.mimonte.model.PagoPartidas;
import mx.com.nmp.pagos.mimonte.services.ClienteService;
import mx.com.nmp.pagos.mimonte.services.DSSService;
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
	 * Servicio DSS para procesar peticiones referentes a modulo DSS
	 */
	@Autowired
	private DSSService dssService;
	
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

	/**
	 * Modulo DSS para generaion de tipo de afiliacion
	 */
	@Autowired
	private DSSModule dssModule;
	
	/**
	 * Modulo DSS para las operaciones correspondientes a la respuesta de bayonet
	 */
	@Autowired
	private BayonetReglasServiceImpl bayonetReglasServiceImpl;

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private static final Logger LOG = LoggerFactory.getLogger(PagoServiceImpl.class);

	/**
	 * Cantidad maxima de tarjetas por cliente
	 */
	@Value(PagoConstants.VariablesConstants.MAXIMUM_AMOUNT_OF_CARDS_PROPERTY)
	private Integer maximumAmountOfCardsPerClient;

	/**
	 * Propiedad con cantidad maxima posible de partidas a pagar
	 */
	@Value("${mimonte.variables.cantidad-maxima-partidas}")
	private int cantidadMaximaPartidas;

	/**
	 * Metodo que se encarga de guardar nuevos pagos, y algunas veces tarjetas y
	 * clientes
	 * 
	 * @throws SQLException
	 * @throws NumberFormatException
	 * @throws SQLDataException
	 * @throws                       @throws @throws @throws
	 *                               DataIntegrityViolationException
	 *
	 */
	@Override
	@Transactional(rollbackFor = Throwable.class, noRollbackFor = TarjetaIdentifierException.class, propagation = Propagation.REQUIRED)
	public PagoResponseDTO savePago(PagoRequestDTO pagoRequestDTO)
			throws DataIntegrityViolationException, NumberFormatException, SQLDataException, SQLException {
		LOG.debug("Ingreso al servicio: savePago(PagoRequestDTO pagoRequestDTO)");
		PagoResponseDTO pagoResponseDTO = new PagoResponseDTO();
		List<EstatusPagoResponseDTO> estatus;
		TipoAutorizacionDTO tipoAutorizacionDTO = new TipoAutorizacionDTO();
		
		LOG.debug("Recuperando la respuesta de bayonet (de bus)");
		if (null == pagoRequestDTO.getBayonet() || pagoRequestDTO.getBayonet().isEmpty()) {
			LOG.debug("No viene respuesta de bayonet");
			obtieneReglasNormales(pagoRequestDTO, pagoResponseDTO);
		}
		else {
			LOG.debug("  ========  La respuesta de bayonet es: " + pagoRequestDTO.getBayonet());
			BayonetReglasDTO bayonetReglasDTO = bayonetReglasServiceImpl.getReglasBayonetByStatus(pagoRequestDTO.getBayonet().toLowerCase().trim());
			if (null  == bayonetReglasDTO)
				throw new PagoException(PagoConstants.BAYONET_RESPONSE_INCORRECT);

			if (bayonetReglasDTO.getReglaAutorizacion().equals(0)){
				LOG.debug("  0 ==> vamos por reglas normales " );
				obtieneReglasNormales(pagoRequestDTO, pagoResponseDTO);
			}
			else {
				LOG.debug("  1,2 ==> vamos por reglas bayonet " );
				tipoAutorizacionDTO = dssService.getTipoAutorizacionById(bayonetReglasDTO.getReglaAutorizacion());
				pagoResponseDTO.setIdTipoAfiliacion(tipoAutorizacionDTO.getId());
				pagoResponseDTO.setTipoAutorizacion(tipoAutorizacionDTO);
			}
		}
		
		
		LOG.debug("Se validara objeto pagoRequestDTO");
		ValidadorDatosPago.validacionesInicialesPago(pagoRequestDTO, cantidadMaximaPartidas);
		try {
			ValidadorDatosPago.doTypeValidations(pagoRequestDTO);
			ValidadorDatosPago.doSizeValidations(pagoRequestDTO);
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
				// Solo se muestra un log de info que la tarjeta no se guardo, pero el proceso de pago se realiza de forma correcta
				LOG.info("INFO >>> " + PagoConstants.MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED);
			}
		}
		// Finalizan validaciones de tarjeta
		// Inicia validacion de id transaccion
		Integer flag = 0;
		flag = pagoRepository.countByIdTransaccionMidas(Long.parseLong(pagoRequestDTO.getIdTransaccionMidas()));
		// Finaliza validacion de id transaccion
		
		ClienteDTO cl = clienteService.getClienteById(pagoRequestDTO.getIdCliente());
		
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
		Pago pago = null;
		boolean globalStatus = true;
		if (null != pagoRequestDTO.getOperaciones() && !pagoRequestDTO.getOperaciones().isEmpty()) {
			LOG.debug("Se iteraran operaciones dentro de pagoRequestDTO");
			if (null != flag && flag == 0) {
				try {
					pago = PagoBuilder.buildPagoFromPagoRequestDTO(pagoRequestDTO, cl);
					pago.setIdTipoAutorizacion(pagoResponseDTO.getIdTipoAfiliacion());
					pagoRepository.save(pago);
				} catch (Exception ex) {
					LOG.warn(PagoConstants.ROLL_BACK_EXCEPCION_MESSAGE.concat(" : ").concat(ex.getMessage()));
					globalStatus = false;
					throw new PagoException(PagoConstants.ROLL_BACK_EXCEPCION_MESSAGE);
				}
			} else if (null == flag) {
				throw new PagoException(PagoConstants.MSG_CAN_NO_CHECK_IF_PAGO_EXISTS);
			} else {
				throw new PagoException(PagoConstants.TRANSACTION_ID_ALREADY_EXISTS);
			}
			pagoResponseDTO.setExitoso(globalStatus);
			if (globalStatus) {
				estatus = new ArrayList<>();
				for (PagoPartidas partidas : pago.getPagoPartidasList()) {
					estatus.add(new EstatusPagoResponseDTO(EstatusOperacion.SUCCESSFUL_STATUS_OPERATION.getId(),
							partidas.getFolioPartida().toString()));
				}
				pagoResponseDTO.setEstatusPagos(estatus);
			}
		} else {
			LOG.error("Objeto pagoRequestDTO.getOperaciones() es nulo o es vacio!");
			pagoResponseDTO.setExitoso(false);
			throw new PagoException(PagoConstants.NO_OPERATIONS_MESSAGE);
		}
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
		LOG.debug("El resultado de: validaSiGuardar(PagoRequestDTO) es: {}", flag);
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
		if (cantidadTarjetas < maximumAmountOfCardsPerClient)
			flag = true;
		LOG.debug("El resultado del metodo validaCantidadTarjetasExistentes(PagoRequestDTO) es: {}", flag);
		return flag;
	}

	/**
	 * Metodo que va por un numero de afilicacion y obtener el tipo de autorizacion (3d secure o no)
	 *
	 * @return void
	 */
	private void obtieneReglasNormales(PagoRequestDTO pagoRequestDTO, PagoResponseDTO pagoResponseDTO) throws SQLDataException, SQLException{
		LOG.debug("Intentando obtener un numero de afiliacion");
		// DSS invocation
		TipoAutorizacionDTO tipoAutorizacionDTO = this.dssModule.getNoAfiliacion(pagoRequestDTO);
		pagoResponseDTO.setIdTipoAfiliacion(null != tipoAutorizacionDTO && null != tipoAutorizacionDTO.getId() ? tipoAutorizacionDTO.getId() : null);
		pagoResponseDTO.setTipoAutorizacion(tipoAutorizacionDTO);
	}

}
