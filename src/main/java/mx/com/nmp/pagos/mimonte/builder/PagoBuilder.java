/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mx.com.nmp.pagos.mimonte.constans.EstatusPago;
import mx.com.nmp.pagos.mimonte.dto.ClienteDTO;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.model.Pago;
import mx.com.nmp.pagos.mimonte.model.PagoPartidas;

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

	public static Pago buildPagoFromPagoRequestDTO(PagoRequestDTO pagoRequestDTO, ClienteDTO cliente) {
		Pago pago = null;
		PagoPartidas pagoPartidas = null;
		List<PagoPartidas> pagoPartidasList = null;
		if (null != pagoRequestDTO) {
			pago = new Pago();
			pagoPartidasList = new ArrayList<>();
			pago.setCliente(ClienteBuilder.buildClienteFromClienteDTO(cliente));
			pago.setConcepto(pagoRequestDTO.getConcepto());
			pago.setEstatusPago(
					new mx.com.nmp.pagos.mimonte.model.EstatusPagos(EstatusPago.REGISTERED_PAYMENT_STATUS.getId(),
							EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcionCorta(),
							EstatusPago.REGISTERED_PAYMENT_STATUS.getDescripcion()));
			pago.setFechaCreacion(new Date());
			pago.setFechaTarnsaccion(new Date());
			pago.setIdTransaccionMidas(Long.valueOf(pagoRequestDTO.getIdTransaccionMidas()));
			pago.setMonto(pagoRequestDTO.getMontoTotal());
			pago.setTarjeta(null != pagoRequestDTO.getTarjeta() && null != pagoRequestDTO.getTarjeta().getDigitos()
					? pagoRequestDTO.getTarjeta().getDigitos()
					: null);
			for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
				pagoPartidas = new PagoPartidas();
				pagoPartidas.setFolioPartida(Long.valueOf(operacion.getFolioContrato()));
				pagoPartidas.setIdOperacion(operacion.getIdOperacion());
				pagoPartidas.setMonto(operacion.getMonto());
				pagoPartidas.setNombreOperacion(operacion.getNombreOperacion());
				pagoPartidasList.add(pagoPartidas);
				pagoPartidas.setPagos(pago);
			}
			pago.setPagoPartidasList(pagoPartidasList);
		}
		return pago;
	}

}
