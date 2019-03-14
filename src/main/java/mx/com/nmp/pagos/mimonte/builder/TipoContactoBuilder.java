package mx.com.nmp.pagos.mimonte.builder;

import mx.com.nmp.pagos.mimonte.dto.TipoContactoDTO;
import mx.com.nmp.pagos.mimonte.dto.TipoContactoRespDTO;
import mx.com.nmp.pagos.mimonte.model.TipoContacto;

/**
 * Nombre: TipoContactoBuilder Descripcion: clase de capa de builder que se
 * encarga de convertir difrentes tipos de objetos y entidades relacionadas con
 * el catalogo TipoContacto
 *
 * @author José Rodríguez jgrodriguez@quarksoft.net Fecha: 05/03/2019 12:16 Hrs.
 * @version 0.1
 */
public abstract class TipoContactoBuilder {

	private TipoContactoBuilder() {
		super();
	}

	public static TipoContacto builTipoContactoFromContactoBaseDTO(TipoContactoDTO tipoContactoDTO) {

		TipoContacto tipoContacto = null;

		if (tipoContactoDTO != null) {

			tipoContacto = new TipoContacto();
			tipoContacto.setId(tipoContactoDTO.getId());
			tipoContacto.setDescription(tipoContactoDTO.getDescription());
			tipoContacto.setShortDescription(tipoContactoDTO.getShortDescription());
			tipoContacto.setEstatus(tipoContactoDTO.getEstatus());
			tipoContacto.setCreatedBy(tipoContactoDTO.getCreatedBy());
			tipoContacto.setCreatedDate(tipoContactoDTO.getCreatedDate());
			tipoContacto.setLastModifiedBy(tipoContactoDTO.getLastModifiedBy());
		}

		return tipoContacto;

	}

	public static TipoContactoDTO builTipoContactoBaseDTOFromContacto(TipoContacto tipoContacto) {

		TipoContactoDTO tipoContactoDTO = null;

		if (tipoContacto != null) {

			tipoContactoDTO = new TipoContactoDTO();
			tipoContactoDTO.setId(tipoContacto.getId());
			tipoContactoDTO.setDescription(tipoContacto.getDescription());
			tipoContactoDTO.setShortDescription(tipoContacto.getShortDescription());
			tipoContactoDTO.setEstatus(tipoContacto.getEstatus());
			tipoContactoDTO.setCreatedBy(tipoContacto.getCreatedBy());
			tipoContactoDTO.setCreatedDate(tipoContacto.getCreatedDate());
			tipoContactoDTO.setLastModifiedBy(tipoContacto.getLastModifiedBy());
		}

		return tipoContactoDTO;

	}

	public static TipoContactoRespDTO builTipoTipoContactoRespDTOFromTipoContactoDTO(TipoContactoDTO tipoContactoDTO) {

		TipoContactoRespDTO tipoContactoRespDTO = null;

		if (tipoContactoDTO != null) {

			tipoContactoRespDTO = new TipoContactoRespDTO();
			tipoContactoRespDTO.setDescripcion(tipoContactoDTO.getDescription());
			tipoContactoRespDTO.setId(tipoContactoDTO.getId());
		}

		return tipoContactoRespDTO;

	}

}
