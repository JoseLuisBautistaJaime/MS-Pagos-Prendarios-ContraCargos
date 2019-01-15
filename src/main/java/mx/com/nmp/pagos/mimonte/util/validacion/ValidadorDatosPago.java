package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.constans.PagoConstants;
import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.TarjetaPagoDTO;
import mx.com.nmp.pagos.mimonte.exception.PagoException;

/**
 * Nombre: ValidadorDatosPago
 * Descripcion: Validador de Datos de Pago
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
	
	public static void doTypeValidations(PagoRequestDTO pagoRequestDTO) throws PagoException{
		if(pagoRequestDTO.getIdCliente() <= 0) {
			throw new PagoException(PagoConstants.CLIENT_ID_LESS_OR_EQUAL_THAN_0);
		}
		if(pagoRequestDTO.getMontoTotal() <= 0)
			throw new PagoException(PagoConstants.TOTAL_AMOUNT_LESS_OR_EQUAL_THAN_0);
		Double sum = 0D;
		for(OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			sum = Double.sum(operacion.getMonto(),sum);
			if(operacion.getIdOperacion() <= 0)
				throw new PagoException(PagoConstants.OPERATION_ID_LESS_OR_EQUAL_THAN_0);
			if(operacion.getMonto() <= 0)
				throw new PagoException(PagoConstants.OPERATION_AMOUNT_LESS_OR_EQUAL_THAN_0);
			
			try {
				Integer.parseInt(operacion.getFolioContrato());				
			}
			catch(NumberFormatException nex) {
				throw new PagoException(PagoConstants.NUMBER_FORMAT_IN_FOLIO_CONTRATO);
			}
		}
		if(!Double.valueOf(pagoRequestDTO.getMontoTotal()).equals(sum))
			throw new PagoException(PagoConstants.IRREGULAR_OPERATIONS_AMOUNT);
		try {
			Integer.parseInt(pagoRequestDTO.getIdTransaccionMidas());
		}
		catch(NumberFormatException nex) {
			throw new PagoException(PagoConstants.NUMBER_FORMAT_IN_ID_TRANSACCION_MIDAS);
		}
	}
	
}
