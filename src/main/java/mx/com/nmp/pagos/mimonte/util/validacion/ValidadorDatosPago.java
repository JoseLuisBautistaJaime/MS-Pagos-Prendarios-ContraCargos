package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;

/**
 * Nombre: ValidadorDatosPago Descripcion: Validador de Datos de Pago
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/11/2018 14:16 hrs.
 * @version 0.1
 */
public class ValidadorDatosPago {

	/**
	 * 
	 * Metodo que valida el objeto PagoRequestDTO para que no haya datos nulos
	 * 
	 * @param pagoRequestDTO
	 */
	public static final void validacionesInicialesPago(PagoRequestDTO pagoRequestDTO) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getConcepto());
		vo.noNulo(pagoRequestDTO.getGuardaTarjeta());
		vo.noNulo(pagoRequestDTO.getIdCliente());
		vo.noNulo(pagoRequestDTO.getMontoTotal());
		vo.noNulo(pagoRequestDTO.getIdTransaccionMidas());
		vo.noNulo(pagoRequestDTO.getOperaciones());
		for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			vo.noNulo(operacion.getFolioContrato());
			vo.noNulo(operacion.getIdOperacion());
			vo.noNulo(operacion.getMonto());
			vo.noNulo(operacion.getNombreOperacion());
			vo.noNulo(operacion.getFolioContrato());
			vo.noNulo(operacion.getIdOperacion());
		}
	}

	/**
	 * 
	 * Metodo que valida un objeto TarjetaPagoDTO para que no contenga valores nulos
	 * 
	 * @param tarjeta
	 */
	public static final void validacionesTrajeta(TarjetaPagoDTO tarjeta) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(tarjeta);
		vo.noNulo(tarjeta.getAlias());
		vo.noNulo(tarjeta.getDigitos());
		vo.noNulo(tarjeta.getToken());
	}

	public static void doTypeValidations(PagoRequestDTO pagoRequestDTO) throws PagoException {
		if (pagoRequestDTO.getIdCliente() <= 0) {
			throw new PagoException(PagoConstants.FieldSizeConstants.CLIENT_ID_LESS_OR_EQUAL_THAN_0);
		}
		if (pagoRequestDTO.getMontoTotal() <= 0)
			throw new PagoException(PagoConstants.TOTAL_AMOUNT_LESS_OR_EQUAL_THAN_0);
		Double sum = 0D;
		for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			sum = Double.sum(operacion.getMonto(), sum);
			if (operacion.getIdOperacion() <= 0)
				throw new PagoException(PagoConstants.OPERATION_ID_LESS_OR_EQUAL_THAN_0);
			if (operacion.getMonto() <= 0)
				throw new PagoException(PagoConstants.OPERATION_AMOUNT_LESS_OR_EQUAL_THAN_0);

			try {
				Integer.parseInt(operacion.getFolioContrato());
			} catch (NumberFormatException nex) {
				throw new PagoException(PagoConstants.NUMBER_FORMAT_IN_FOLIO_CONTRATO);
			}
		}
		if (!Double.valueOf(pagoRequestDTO.getMontoTotal()).equals(sum))
			throw new PagoException(PagoConstants.IRREGULAR_OPERATIONS_AMOUNT);
		try {
			Integer.parseInt(pagoRequestDTO.getIdTransaccionMidas());
		} catch (NumberFormatException nex) {
			throw new PagoException(PagoConstants.NUMBER_FORMAT_IN_ID_TRANSACCION_MIDAS);
		}
		if (pagoRequestDTO.getGuardaTarjeta() && null == pagoRequestDTO.getTarjeta()) {
			throw new PagoException(PagoConstants.MSG_CARD_DATA_IS_NULL);
		}
	}

	/**
	 * Realiza validaciones de tamano de campos para que sean compatibles con los
	 * definidos en base de datos
	 * 
	 * @param pagoRequestDTO
	 */
	public static void doSizeValidations(PagoRequestDTO pagoRequestDTO) {
		if (null != pagoRequestDTO) {
			if (null != pagoRequestDTO.getConcepto()
					&& pagoRequestDTO.getConcepto().length() > PagoConstants.DataBaseFieldType.PAGO_DESCRIPCION_FIELD)
				throw new PagoException(PagoConstants.FieldSizeConstants.DESCRIPTION_SIZE_TOO_LONG);
			if (null != pagoRequestDTO.getIdTransaccionMidas() && pagoRequestDTO.getIdTransaccionMidas()
					.length() > PagoConstants.DataBaseFieldType.PAGO_ID_TRANSACCION_MIDAS_FIELD)
				throw new PagoException(PagoConstants.FieldSizeConstants.MIDAS_TRANSACTION_ID_TOO_LONG);
			if (null != pagoRequestDTO.getMontoTotal()
					&& pagoRequestDTO.getMontoTotal() > PagoConstants.DataBaseFieldType.PAGO_MONTO_TOTAL_FIELD)
				throw new PagoException(PagoConstants.FieldSizeConstants.TOTAL_AMOUNT_TOO_LONG);
			if (null != pagoRequestDTO.getGuardaTarjeta() && pagoRequestDTO.getGuardaTarjeta()
					&& null == pagoRequestDTO.getTarjeta())
				throw new PagoException(PagoConstants.CARD_OBJECT_IS_NULL_OR_EMPTY);
			if (null != pagoRequestDTO.getIdCliente()
					&& pagoRequestDTO.getIdCliente() > PagoConstants.DataBaseFieldType.PAGO_ID_CLIENTE_FIELD)
				throw new PagoException(PagoConstants.FieldSizeConstants.CLIENT_ID_TOO_LONG);
			if (null == pagoRequestDTO.getOperaciones() || pagoRequestDTO.getOperaciones().isEmpty())
				throw new PagoException(PagoConstants.NO_OPERATIONS_MESSAGE);
			else {
				for (OperacionDTO operacionDTO : pagoRequestDTO.getOperaciones()) {
					if (null != operacionDTO.getFolioContrato() && Integer.parseInt(
							operacionDTO.getFolioContrato()) > PagoConstants.DataBaseFieldType.PAGO_FOLIO_PARTIDA_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.CONTRACT_FOLIO_TOO_LONG);
					if (null != operacionDTO.getIdOperacion()
							&& operacionDTO.getIdOperacion() > PagoConstants.DataBaseFieldType.PAGO_ID_OPERACION_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.OPERATION_ID_TOO_LONG);
					if (null != operacionDTO.getNombreOperacion() && operacionDTO.getNombreOperacion()
							.length() > PagoConstants.DataBaseFieldType.PAGO_NOMBRE_OPERACION_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.DESCRIPTION_SIZE_TOO_LONG);
					if (null != operacionDTO.getMonto()
							&& operacionDTO.getMonto() > PagoConstants.DataBaseFieldType.PAGO_MONTO_OPERACION_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.OPERATION_AMOUNT_TOO_LONG);
				}
			}
		} else
			throw new PagoException(PagoConstants.PAYMENT_INFORMATION_EMPTY_OR_NULL);
	}

}
