/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;

import mx.com.nmp.pagos.mimonte.constans.EstatusPago;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.model.Pago;

/**
 * @name PagoBuilder Descripcion: Builder que se encaraga de fabricar objetos
 *       entity a partir de objetos DTO y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 21/11/2018 17:30 hrs.
 * @version 0.1
 */
public class PagoBuilder {

	/**
	 * DecimalFormat para truncar a 2 decimales el monto de operaciones
	 */
	private static final DecimalFormat DF;

	static {
		DF = new DecimalFormat("#.##");
		DF.setRoundingMode(RoundingMode.FLOOR);
	}

	private PagoBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * 
	 * Metodo que construye un entity tipo Pago de objetos DTO recibidos como
	 * parametro
	 * 
	 * @param operacion
	 * @param tarjeta
	 * @param clienteDTO
	 * @return
	 */
	public static Pago buildPagoFromObject(OperacionDTO operacion, TarjetaPagoDTO tarjeta, ClienteDTO clienteDTO,
			String idTransaccionMidas) {
		Pago pago = new Pago();
		pago.setCliente(ClienteBuilder.buildClienteFromClienteDTO(clienteDTO));
		pago.setDescripcion(operacion.getNombreOperacion());
		pago.setEstatusPago(
				new mx.com.nmp.pagos.mimonte.model.EstatusPagos(EstatusPago.REGISTERED_PAYMENT_STATUS.getId(),
						EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcionCorta(),
						EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcion()));
		pago.setFechaCreacion(new Date());
		pago.setFechaTarnsaccion(new Date());
		pago.setAutorizacion(null);
		pago.setIdOpenPay(null);
		pago.setIdOrder(null);
		pago.setMetodo(null);
		pago.setRestResponse(null);
		pago.setIdTransaccionMidas(Long.parseLong(idTransaccionMidas));
		pago.setMonto(new Double(DF.format(operacion.getMonto())));
		pago.setFolioPartida(Long.parseLong(operacion.getFolioContrato()));
		pago.setIdOperacion(operacion.getIdOperacion());
		pago.setTarjeta(null != tarjeta && null != tarjeta.getDigitos() ? tarjeta.getDigitos() : null);
		return pago;
	}

	/**
	 * Metodo que construye un objeto de tipo PagoDTO desde un Entity tipo Pago
	 * 
	 * @param Entity de tipo Pago pagoEntity
	 * @return Objeto de tipo PagoDTO
	 */
	public static PagoDTO buildPagoDTO(Pago pagoEntity) {
		PagoDTO pagoDTO = new PagoDTO();
		pagoDTO.setAutorizacion(pagoEntity.getAutorizacion());
		pagoDTO.setCliente(ClienteBuilder.buildClienteDTOFromCliente(pagoEntity.getCliente()));
		pagoDTO.setDescripcion(pagoEntity.getDescripcion());
		pagoDTO.setFechaCreacion(pagoEntity.getFechaCreacion());
		pagoDTO.setFechaTarnsaccion(pagoEntity.getFechaTarnsaccion());
		pagoDTO.setId(pagoEntity.getId());
		pagoDTO.setIdOpenPay(pagoEntity.getIdOpenPay());
		pagoDTO.setIdOrder(pagoEntity.getIdOrder());
		pagoDTO.setMetodo(pagoEntity.getMetodo());
		pagoDTO.setMonto(pagoEntity.getMonto());
		pagoDTO.setRestResponse(pagoEntity.getRestResponse());
		// No es un Foreign Key por eso solo requiere los ultimos digitos de la tarjeta
		TarjetaDTO tarjeta = new TarjetaDTO();
		tarjeta.setDigitos(pagoEntity.getTarjeta());
		pagoDTO.setTarjeta(tarjeta);
		return pagoDTO;
	}

}
