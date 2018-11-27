package mx.com.nmp.pagos.mimonte.builder;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.EstatusPagoDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusPago;

/**
 * Nombre: EstatusPagoBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos entity a partir de objetos DTO
 * y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/11/2018 11:52 hrs.
 * @version 0.1
 */
@Component
public class EstatusPagoBuilder {

	/**
	 * Metodo que construye un entity tipo EstatusPago desde un objeto tipo EstatusPagoDTO
	 * 
	 * @param Objeto tipo EstatusPagoDTO estatusPagoDTO
	 * @return Entity tipo EstatusPago
	 */
	public static EstatusPago buildEstatusTransaccionFromEstatusTransaccionDTO(EstatusPagoDTO estatusTransaccionDTO) {
		EstatusPago estatusTransaccionEntity = new EstatusPago();
		estatusTransaccionEntity.setDescripcion(estatusTransaccionDTO.getDescripcion());
		estatusTransaccionEntity.setDescripcionCorta(estatusTransaccionDTO.getDescripcionCorta());
		estatusTransaccionEntity.setId(estatusTransaccionDTO.getId());
		return estatusTransaccionEntity;
	}
	
	/**
	 * Metodo que construye un objeto de tipo EstatusPagoDTO desde un Entity de tipo EstatusPago
	 * 
	 * @param Entity de tipo EstatusPago estatusPago
	 * @return Objeto de tipo EstatusPagoDTO
	 */
	public static EstatusPagoDTO buildEstatusTransaccionDTOFromEstatusTransaccion(EstatusPago estatusTransaccion) {
		EstatusPagoDTO estatusTransaccionDTO = new EstatusPagoDTO();
		estatusTransaccionDTO.setDescripcion(estatusTransaccion.getDescripcion());
		estatusTransaccionDTO.setDescripcionCorta(estatusTransaccion.getDescripcionCorta());
		estatusTransaccionDTO.setId(estatusTransaccion.getId());
		return estatusTransaccionDTO;
	}
	
}
