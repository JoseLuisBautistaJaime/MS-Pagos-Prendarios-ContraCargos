package mx.com.nmp.pagos.mimonte.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.builder.PagoBuilder;
import mx.com.nmp.pagos.mimonte.builder.TarjetaBuilder;
import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dao.PagoRepository;
import mx.com.nmp.pagos.mimonte.dto.EstatusPagoResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.DatosIncompletosException;
import mx.com.nmp.pagos.mimonte.exception.PagoException;
import mx.com.nmp.pagos.mimonte.model.EstatusPago;
import mx.com.nmp.pagos.mimonte.model.Pago;
import mx.com.nmp.pagos.mimonte.services.ClienteService;
import mx.com.nmp.pagos.mimonte.services.PagoService;
import mx.com.nmp.pagos.mimonte.services.TarjetasService;

/**
 * Nombre: PagoServiceImpl
 * Descripcion: Clase que realiza el pago de partidas /
 * contratos
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate: 20/11/2018 16:22 hrs.
 * @version 0.1
 */
@Service("pagoServiceImpl")
public class PagoServiceImpl implements PagoService {

	@Autowired
	private TarjetasService tarjetaService;

	@Autowired
	@Qualifier("clienteServiceImpl")
	private ClienteService clienteService;

	@Autowired
	@Qualifier("pagoRepository")
	private PagoRepository pagoRepository;

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);

	/**
	 * Metodo que registra una nueva trajeta si dicha bandera es activa, envia el
	 * registro de un pago al ESB y registra un pago en Base de Datos
	 *
	 */
	@Override
	public PagoResponseDTO savePago(PagoRequestDTO pagoRequestDTO) throws PagoException {
		log.info("Ingreso al servicio de pago: POST");
		PagoResponseDTO pagoResponseDTO = new PagoResponseDTO();
		List<EstatusPagoResponseDTO> estatusPagos = new ArrayList<>();
		// Aqui se obtiene un numero de afiliacion aleatorio, pero se debe obtener de un modulo de toma de decisiones
		pagoResponseDTO.setIdTipoAfiliacion(getRandomNumber());
		boolean estatusTarjeta = false;
		if (validaSiGuardar(pagoRequestDTO)) {
			if (validaDatos(pagoRequestDTO)) {
				if (validaCantidadTarjetasExistentes(pagoRequestDTO)) {
					tarjetaService.addTarjetas(TarjetaBuilder.buildTarjetaDTOFromTarjetaPagoDTO(pagoRequestDTO.getTarjeta(),
							clienteService.getClienteById(pagoRequestDTO.getIdCliente())));
					estatusTarjeta = true;
				} else {
					// No se lanzara excepcion debido a que de esa manera no se guardaria el pago de
					// las partidas indicadas
					// throw new
					// CantidadMaximaTarjetasAlcanzadaException(PagoConstants.MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED);
				}
			} else {
				throw new DatosIncompletosException(PagoConstants.INCOMPLETE_CARD_DATA);
			}
		}
		// Guardar pago de partidas
		Pago pago = new Pago();
		EstatusPago ep = new EstatusPago();
		for (int i = 0; i < pagoRequestDTO.getOperaciones().size(); i++) {
			try {
				pago = PagoBuilder.buildPagoFromObject(pagoRequestDTO, clienteService.getClienteById(pagoRequestDTO.getIdCliente()),i);
				pagoRepository.save(pago);
			} catch (Exception ex) {
				log.error(ex.getMessage());
			}
		}
		pagoResponseDTO.setEstatusPagos(estatusPagos);
		pagoResponseDTO.setExitoso(true);
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
		return flag;
	}

	/**
	 * Metodo que recibe un objeto PagoRequestDTO y valida su contenido para saber
	 * todos los campos de la tarjeta son validos
	 * 
	 * @param pagoDTO Objeto PagoRequestDTO con un objeto tarjeta dentro
	 * @return Valor boleano indicando si es posible guardar la tarjeta
	 */
	private static final boolean validaDatos(PagoRequestDTO pagoDTO) {
		boolean flag = false;
		if (null != pagoDTO && null != pagoDTO.getTarjeta() && null != pagoDTO.getTarjeta().getAlias()
				&& null != pagoDTO.getTarjeta().getToken())
			flag = true;
		return flag;
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
		int cantidadTarjetas = tarjetaService.countTarjetasByIdCliente(pagoDTO.getIdCliente());
		if (cantidadTarjetas < PagoConstants.MAXIMUM_AMOUNT_OF_CARDS)
			flag = true;
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
