package mx.com.nmp.pagos.mimonte.util.validacion;

import mx.com.nmp.pagos.mimonte.dto.OperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.PagoRequestDTO;

/**
 * Nombre: ValidadorDatosPago
 * Descripcion: Validador de Datos de Pago
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 30/11/2018 14:16 hrs.
 * @version 0.1
 */
public class ValidadorDatosPago {

	public static final void validacionesInicialesPago(PagoRequestDTO pagoRequestDTO) {
		ValidadorObjeto vo = new ValidadorObjeto();
		vo.noNulo(pagoRequestDTO);
		vo.noNulo(pagoRequestDTO.getConcepto());
		vo.noNulo(pagoRequestDTO.getGuardaTarjeta());
		vo.noNulo(pagoRequestDTO.getIdCliente());
		vo.noNulo(pagoRequestDTO.getMontoTotal());
		vo.noNulo(pagoRequestDTO.getOperaciones());
		for (OperacionDTO operacion : pagoRequestDTO.getOperaciones()) {
			vo.noNulo(operacion.getFolioContrato());
			vo.noNulo(operacion.getIdOperacion());
			vo.noNulo(operacion.getMonto());
			vo.noNulo(operacion.getNombreOperacion());
		}
		vo.noNulo(pagoRequestDTO.getTarjeta());
		vo.noNulo(pagoRequestDTO.getTarjeta().getAlias());
		vo.noNulo(pagoRequestDTO.getTarjeta().getDigitos());
		vo.noNulo(pagoRequestDTO.getTarjeta().getToken());
	}
}
