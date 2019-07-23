package mx.com.nmp.pagos.mimonte.util.validacion;

import java.math.BigDecimal;

import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;

/**
 * @name ValidadorDatosPago
 * @description Validador de Datos de Pago
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/11/2018 14:16 hrs.
 * @version 0.1
 */
public interface ValidadorDatosPago {

	/**
	 * Metodo que valida el objeto PagoRequestDTO para que no haya datos nulos
	 * 
	 * @param pagoRequestDTO
	 * @param cantidadMaximaPartidas
	 */
	public static void validacionesInicialesPago(PagoRequestDTO pagoRequestDTO, int cantidadMaximaPartidas) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getConcepto());
		if("".equals(pagoRequestDTO.getConcepto()))
			throw new PagoException(PagoConstants.CONCEPTO_PAGO_CANT_BE_EMPTY);
		vo.noNulo(pagoRequestDTO.getGuardaTarjeta());
		vo.noNulo(pagoRequestDTO.getIdCliente());
		vo.noNulo(pagoRequestDTO.getMontoTotal());
		vo.noNulo(pagoRequestDTO.getIdTransaccionMidas());
		if("".equals(pagoRequestDTO.getIdTransaccionMidas()))
			throw new PagoException(PagoConstants.ID_TRANSACCION_MIDAS_CANT_BE_EMPTY);
		vo.noNulo(pagoRequestDTO.getOperaciones());
		if (pagoRequestDTO.getOperaciones().size() > cantidadMaximaPartidas)
			throw new PagoException(PagoConstants.PARTIDAS_QUANTITY_EXCEDED);
		for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			vo.noNulo(operacion.getFolioContrato());
			if("".equals(operacion.getFolioContrato()))
				throw new PagoException(PagoConstants.FOLIO_CONTRATO_CANT_BE_EMPTY);
			vo.noNulo(operacion.getIdOperacion());
			vo.noNulo(operacion.getMonto());
			vo.noNulo(operacion.getNombreOperacion());
			if("".equals(operacion.getNombreOperacion()))
				throw new PagoException(PagoConstants.NOMBRE_OPERACION_CANT_BE_EMPTY);
			vo.noNulo(operacion.getFolioContrato());
			vo.noNulo(operacion.getIdOperacion());
		}
	}

	/**
	 * Metodo que valida un objeto TarjetaPagoDTO para que no contenga valores nulos
	 * 
	 * @param tarjeta
	 */
	public static void validacionesTrajeta(TarjetaPagoDTO tarjeta) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(tarjeta);
		vo.noNulo(tarjeta.getAlias());
		vo.noNulo(tarjeta.getDigitos());
		vo.noNulo(tarjeta.getToken());
	}

	/**
	 * Valida que un objeto de tipo PagoRequestDTO y sus atributos sean correctos
	 * 
	 * @param pagoRequestDTO
	 * @throws PagoException
	 */
	public static void doTypeValidations(PagoRequestDTO pagoRequestDTO) throws PagoException {
		if (pagoRequestDTO.getIdCliente() <= 0) {
			throw new PagoException(PagoConstants.FieldSizeConstants.CLIENT_ID_LESS_OR_EQUAL_THAN_0);
		}
		if (pagoRequestDTO.getMontoTotal().compareTo(BigDecimal.ZERO) <= 0)
			throw new PagoException(PagoConstants.TOTAL_AMOUNT_LESS_OR_EQUAL_THAN_0);
		BigDecimal sum = new BigDecimal("0");
		for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			sum = operacion.getMonto().add(sum);
			if (operacion.getIdOperacion() <= 0)
				throw new PagoException(PagoConstants.OPERATION_ID_LESS_OR_EQUAL_THAN_0);
			if (operacion.getMonto().compareTo(BigDecimal.ZERO) < 0)
				throw new PagoException(PagoConstants.OPERATION_AMOUNT_LESS_OR_EQUAL_THAN_0);

			try {
				Long.parseLong(operacion.getFolioContrato());
			} catch (NumberFormatException nex) {
				throw new PagoException(PagoConstants.NUMBER_FORMAT_IN_FOLIO_CONTRATO);
			}
		}
		if (!pagoRequestDTO.getMontoTotal().equals(sum))
			throw new PagoException(PagoConstants.IRREGULAR_OPERATIONS_AMOUNT);
		try {
			Long.parseLong(pagoRequestDTO.getIdTransaccionMidas());
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
				throw new PagoException(PagoConstants.FieldSizeConstants.CONCEPTO_SIZE_TOO_LONG);
			if (null != pagoRequestDTO.getIdTransaccionMidas() && Long.parseLong(pagoRequestDTO
					.getIdTransaccionMidas()) > PagoConstants.DataBaseFieldType.PAGO_ID_TRANSACCION_MIDAS_FIELD)
				throw new PagoException(PagoConstants.FieldSizeConstants.MIDAS_TRANSACTION_ID_TOO_LONG);
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
					if (null != operacionDTO.getFolioContrato() && Long.parseLong(
							operacionDTO.getFolioContrato()) > PagoConstants.DataBaseFieldType.PAGO_FOLIO_PARTIDA_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.CONTRACT_FOLIO_TOO_LONG);
					if (null != operacionDTO.getIdOperacion()
							&& operacionDTO.getIdOperacion() > PagoConstants.DataBaseFieldType.PAGO_ID_OPERACION_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.OPERATION_ID_TOO_LONG);
					if (null != operacionDTO.getNombreOperacion() && operacionDTO.getNombreOperacion()
							.length() > PagoConstants.DataBaseFieldType.PAGO_NOMBRE_OPERACION_FIELD)
						throw new PagoException(PagoConstants.FieldSizeConstants.NOMBRE_OPERACION_SIZE_TOO_LONG);
				}
			}
		} else
			throw new PagoException(PagoConstants.PAYMENT_INFORMATION_EMPTY_OR_NULL);
	}

}
