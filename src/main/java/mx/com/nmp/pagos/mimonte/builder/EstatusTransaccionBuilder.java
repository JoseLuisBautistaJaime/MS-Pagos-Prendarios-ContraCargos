package mx.com.nmp.pagos.mimonte.builder;

import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dto.EstatusTransaccionDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusTransaccion;

/**
 * Nombre: EstatusTransaccionBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos entity a partir de objetos DTO
 * y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/11/2018 11:52 hrs.
 * @version 0.1
 */
@Component
public class EstatusTransaccionBuilder {

	/**
	 * Metodo que construye un entity tipo EstatusTransaccion desde un objeto tipo EstatusTransaccionDTO
	 * 
	 * @param Objeto tipo EstatusTransaccionDTO estatusTransaccionDTO
	 * @return Entity tipo EstatusTransaccion
	 */
	public static EstatusTransaccion buildEstatusTransaccionFromEstatusTransaccionDTO(EstatusTransaccionDTO estatusTransaccionDTO) {
		EstatusTransaccion estatusTransaccionEntity = new EstatusTransaccion();
		estatusTransaccionEntity.setDescripcion(estatusTransaccionDTO.getDescripcion());
		estatusTransaccionEntity.setDescripcionCorta(estatusTransaccionDTO.getDescripcionCorta());
		estatusTransaccionEntity.setId(estatusTransaccionDTO.getId());
		return estatusTransaccionEntity;
	}
	
	/**
	 * Metodo que construye un objeto de tipo EstatusTransaccionDTO desde un Entity de tipo EstatusTransaccion
	 * 
	 * @param Entity de tipo EstatusTransaccion estatusTransaccion
	 * @return Objeto de tipo EstatusTransaccionDTO
	 */
	public static EstatusTransaccionDTO buildEstatusTransaccionDTOFromEstatusTransaccion(EstatusTransaccion estatusTransaccion) {
		EstatusTransaccionDTO estatusTransaccionDTO = new EstatusTransaccionDTO();
		estatusTransaccionDTO.setDescripcion(estatusTransaccion.getDescripcion());
		estatusTransaccionDTO.setDescripcionCorta(estatusTransaccion.getDescripcionCorta());
		estatusTransaccionDTO.setId(estatusTransaccion.getId());
		return estatusTransaccionDTO;
	}
	
}
