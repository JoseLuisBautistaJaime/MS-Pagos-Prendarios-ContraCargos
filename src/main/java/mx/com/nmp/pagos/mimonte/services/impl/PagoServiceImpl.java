package mx.com.nmp.pagos.mimonte.services.impl;

import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;
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
	TarjetasService tarjetaService;

	/**
	 * Logger para el registro de actividad en la bitacora
	 */
	private final Logger log = LoggerFactory.getLogger(PagoServiceImpl.class);

	/**
	 * Metodo que registra una nueva trajeta si dicha bandera es activa, envia el
	 * registro de un pago al ESB y registra una transaccion en Base de Datos
	 *
	 */
	@Override
	public PagoRequestDTO savePago(PagoRequestDTO pagoDTO) throws PagoException {
		log.info("Ingreso al servicio de pago: POST");
		if (validaSiGuardar(pagoDTO)) {
			if (validaDatos(pagoDTO)) {
				if (validaCantidadTarjetasExistentes(pagoDTO)) {
					//tarjetaService.addTarjetas( TarjetaBuilder.buildTarjetaDTOFromTarjetaPagoDTO(pagoDTO.getTarjeta(), ) );
				} else {
					//throw new CantidadMaximaTarjetasAlcanzadaException(PagoConstants.MAXIMUM_AMOUNT_OF_CARDS_ACHIEVED);
				}
			} else {
				//throw new DatosIncompletosException(PagoConstants.INCOMPLETE_CARD_DATA);
			}
		}
		boolean peticionBUS = false;
		try {
			// ---------------------- SEND REQUEST TO ESB (ENTERPRISE SERVICE BUS)
			peticionBUS = true;
		} catch (HTTPException hex) {

		}
		if (peticionBUS) {
			//transaccionService.saveTransaccion(PagoBuilder.buildTransaccionDTOFromPagoDTO(pagoDTO));
		} else {

		}
		return pagoDTO;
	}

	/**
	 * Metodo que recibe un objeto pagoDTO y valida si se debe guardar la
	 * tarjeta que contiene
	 * 
	 * @param pagoDTO Objeto PagoRequestDTO con un objeto tarjeta dentro
	 * @return Valor boleano indicando si se debe guardar la tarjeta
	 */
	private static final boolean validaSiGuardar(PagoRequestDTO pagoDTO) {
		boolean flag = false;
		if (null != pagoDTO && pagoDTO.getGuardaTarjeta())
			flag = true;
		return flag;
	}

	/**
	 * Metodo que recibe un objeto PagoRequestDTO y valida su contenido para saber todos
	 * los campos de la tarjeta son validos
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
	public boolean validaCantidadTarjetasExistentes(PagoRequestDTO pagoDTO) {
		boolean flag = false;
		int cantidadTarjetas = tarjetaService.countTarjetasByIdCliente(pagoDTO.getIdCliente());
		if (cantidadTarjetas < PagoConstants.MAXIMUM_AMOUNT_OF_CARDS)
			flag = true;
		return flag;
	}

}
