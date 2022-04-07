/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.DiaInhabilDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CatalogoDiaInhabil;

/**
 * Nombre: DiaInhabilBuilder Descripcion: Clase de capa de builder que
 * se encarga de convertir diferentes tipos de objetos y entidades relacionadas
 * con el catálogo de días inhábiles.
 *
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @creationDate 16/12/2021 11:18 hrs.
 * @version 0.1
 */
public abstract class DiaInhabilBuilder {

	private DiaInhabilBuilder() {
		super();
	}

	/**
	 * Construye un objeto de tipo DiaInhabilDTO a partir de la entidad
	 * CatalogoDiaInhabil.
	 * 
	 * @param diaInhabil
	 * @return diaInhabilDTO
	 */
	public static DiaInhabilDTO buildDiaInhabilDTOFromCatalogoDiaInhabil(CatalogoDiaInhabil diaInhabil) {
		DiaInhabilDTO diaInhabilDTO = null;
		if (diaInhabil != null) {
			diaInhabilDTO = new DiaInhabilDTO();
			diaInhabilDTO.setId(diaInhabil.getId());
			diaInhabilDTO.setDescripcionCorta(diaInhabil.getDescripcionCorta());
			diaInhabilDTO.setDescripcion(diaInhabil.getDescripcion());
			diaInhabilDTO.setFecha(diaInhabil.getFecha());
		}
		return diaInhabilDTO;
	}

	/**
	 * Construye una entidad CatalogoDiaInhabil a partir de un objeto de tipo
	 * DiaInhabilDTO.
	 * 
	 * @param diaInhabilDTO
	 * @return diaInhabil
	 */
	public static CatalogoDiaInhabil buildCatalogoDiaInhabilFromDiaInhabilDTO( DiaInhabilDTO diaInhabilDTO) {
		CatalogoDiaInhabil diaInhabil = null;
		if (diaInhabilDTO != null) {
			diaInhabil = new CatalogoDiaInhabil();
			diaInhabil.setId(diaInhabilDTO.getId());
			diaInhabil.setDescripcionCorta(diaInhabilDTO.getDescripcionCorta());
			diaInhabil.setDescripcion(diaInhabilDTO.getDescripcion());
			diaInhabil.setFecha(diaInhabilDTO.getFecha());
		}
		return diaInhabil;
	}

}
