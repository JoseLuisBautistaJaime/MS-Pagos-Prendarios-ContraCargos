package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.EstatusTarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.EstatusTarjeta;

/**
 * Nombre: EstatusTarjetaBuilder
 * Descripcion: Builder que se encaraga de fabricar objetos de tipo EstatusTarjeta a tipo EstatusTarjetaDTO
 * y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @CreationDate 22/11/2018 11:49 hrs.
 * @version 0.1
 */
public class EstatusTarjetaBuilder {

	
	/**
	 * Metodo que construye un entity de tipo EstatusTarjeta desde un objeto de tipo EstatusTarjetaDTO
	 * 
	 * @param Objeto de tipo EstatusTarjetaDTO estatusTarjetaDTO
	 * @return Entity de tipo EstatusTarjeta
	 */
	public static EstatusTarjeta buildEstatusTarjetaFromEstatusTarjetaDTO(EstatusTarjetaDTO estatusTarjetaDTO) {
		EstatusTarjeta estatusTarjetaEntity = new EstatusTarjeta();
		estatusTarjetaEntity.setDescripcion(estatusTarjetaDTO.getDescripcion());
		estatusTarjetaEntity.setDescripcionCorta(estatusTarjetaDTO.getDescripcionCorta());
		estatusTarjetaEntity.setId(estatusTarjetaDTO.getId());
		return estatusTarjetaEntity;
	}

	/**
	 * Metodo que construye un objeto EstatusTarjetaDTO desde un entity EstatusTarjeta
	 * 
	 * @param Entity de tipo estatusTarjetaEntity
	 * @return Objeto de tipo EstatusTarjetaDTO
	 */
	public static EstatusTarjetaDTO buildEstatusTarjetaDTOFromEstatusTarjeta(EstatusTarjeta estatusTarjetaEntity) {
		EstatusTarjetaDTO estatusTarjetaDTO = new EstatusTarjetaDTO();
		estatusTarjetaDTO.setDescripcion(estatusTarjetaEntity.getDescripcion());
		estatusTarjetaDTO.setDescripcionCorta(estatusTarjetaEntity.getDescripcionCorta());
		estatusTarjetaDTO.setId(estatusTarjetaEntity.getId());
		return estatusTarjetaDTO;
	}
}
