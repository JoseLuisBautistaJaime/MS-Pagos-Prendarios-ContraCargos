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
	public static EstatusPago buildEstatusPagoFromEstatusPagoDTO(EstatusPagoDTO estatusPagoDTO) {
		EstatusPago estatusPagoEntity = new EstatusPago();
		estatusPagoEntity.setDescripcion(estatusPagoDTO.getDescripcion());
		estatusPagoEntity.setDescripcionCorta(estatusPagoDTO.getDescripcionCorta());
		estatusPagoEntity.setId(estatusPagoDTO.getId());
		return estatusPagoEntity;
	}
	
	/**
	 * Metodo que construye un objeto de tipo EstatusPagoDTO desde un Entity de tipo EstatusPago
	 * 
	 * @param Entity de tipo EstatusPago estatusPago
	 * @return Objeto de tipo EstatusPagoDTO
	 */
	public static EstatusPagoDTO buildEstatusPagoDTOFromEstatusPago(EstatusPago estatusPago) {
		EstatusPagoDTO estatusPagoDTO = new EstatusPagoDTO();
		estatusPagoDTO.setDescripcion(estatusPago.getDescripcion());
		estatusPagoDTO.setDescripcionCorta(estatusPago.getDescripcionCorta());
		estatusPagoDTO.setId(estatusPago.getId());
		return estatusPagoDTO;
	}
		
}
