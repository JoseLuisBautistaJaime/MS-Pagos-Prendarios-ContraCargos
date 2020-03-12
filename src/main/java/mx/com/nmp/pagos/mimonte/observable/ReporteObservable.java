/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;

/**
 * Nombre: ReporteObservable
 * Descripcion: Clase tipo Observable que contiene los datos del Reporte, se encarga de notificar cambios a los Observers
 * @author JGALVEZ
 * Fecha: 30/05/2019 9:44 PM
 * @version 0.1
 */
public class ReporteObservable extends Observable {

	private List<Reporte> reportes;
	private Long idConciliacion;
	private Long idEntidad;

	public ReporteObservable(List<Reporte> reportes, Long idConciliacion, Long idEntidad) {
		this.reportes = null;
		this.idConciliacion = idConciliacion;
		this.idEntidad = idEntidad;
		verifyReportes(reportes);
	}

	public ReporteObservable(Reporte reporte, Long idConciliacion, Long idEntidad) {
		this.reportes = null;
		this.idConciliacion = idConciliacion;
		this.idEntidad = idEntidad;
		verifyReportes(Arrays.asList(reporte));
	}

	private void verifyReportes(List<Reporte> reportes) {
		if (CollectionUtils.isNotEmpty(reportes)) {
			this.reportes = new ArrayList<Reporte>();
			for (Reporte reporte : reportes) {
				if (!reporte.isMergeUpdated()) { // El merge no ha sido actualizado (el reporte es mas nuevo)
					this.reportes.add(reporte);
				}
			}
			if (CollectionUtils.isNotEmpty(this.reportes)) {
				super.setChanged(); // Notificar cambios
			}
		}
	}

	public List<Reporte> getReportes() {
		return reportes;
	}

	public Long getIdConciliacion() {
		return idConciliacion;
	}

	public Long getIdEntidad() {
		return idEntidad;
	}

}
