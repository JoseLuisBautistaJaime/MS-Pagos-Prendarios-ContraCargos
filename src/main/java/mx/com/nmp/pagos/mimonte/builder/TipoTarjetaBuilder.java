package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.TipoTarjetaDTO;
import mx.com.nmp.pagos.mimonte.model.TipoTarjeta;

/**
 * Nombre: TipoTarjetaBuilder
 * Descripcion: Builder que se encaraga de fabricar
 * objetos de tipo Tipotarheta a tipo TipoTrajetaDTO y viceversa
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 22/11/2018 12:15 hrs.
 * @version 0.1
 */
public class TipoTarjetaBuilder {
	
	private TipoTarjetaBuilder() {
		/**
		 * hidden constructor
		 */
	}

	/**
	 * Metodo que construye un entity de tipo TipoTarjeta desde un objeto tipo TipoTarjetaDTO
	 * 
	 * @param Objeto de tipo TipoTarjetaDTO tipoTarjetaDTO
	 * @return Entity de tipo TipoTarjeta
	 */
	public static TipoTarjeta buildTipoTarjetaFromTipoTrajetaDTO(TipoTarjetaDTO tipoTarjetaDTO) {
		TipoTarjeta tipoTarjetaEntity = new TipoTarjeta();
		tipoTarjetaEntity.setDescripcion(tipoTarjetaDTO.getDescripcion());
		tipoTarjetaEntity.setDescripcionCorta(tipoTarjetaDTO.getDescripcionCorta());
		tipoTarjetaEntity.setId(tipoTarjetaDTO.getId());
		return tipoTarjetaEntity;
	}
	
	/**
	 * Metodo que construye un objeto de tipo TipoTarjetaDTO desde un entity de tipo TipoTarjeta
	 * 
	 * @param Entity de tipo TipoTarjeta tipoTarjetaEntity
	 * @return Objeto de tipo TipoTarjetaDTO
	 */
	public static TipoTarjetaDTO buildTipoTarjetaDTOFromTipoTrajeta(TipoTarjeta tipoTarjetaEntity) {
		TipoTarjetaDTO tipoTarjetaDTO = new TipoTarjetaDTO();
		tipoTarjetaDTO.setDescripcion(tipoTarjetaEntity.getDescripcion());
		tipoTarjetaDTO.setDescripcionCorta(tipoTarjetaEntity.getDescripcionCorta());
		tipoTarjetaDTO.setId(tipoTarjetaEntity.getId());
		return tipoTarjetaDTO;
	}

}
