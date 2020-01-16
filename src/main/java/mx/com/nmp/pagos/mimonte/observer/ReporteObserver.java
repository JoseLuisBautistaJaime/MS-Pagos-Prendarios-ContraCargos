/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.observable.ReporteObservable;

/**
 * Nombre: ReporteObserver
 * Descripcion: Clase tipo Observer que se encarga de notificar cambios que se han realizado sobre los Reportes
 *
 * @author JGALVEZ
 * Fecha: 30/05/2019 9:44 PM
 * @version 0.1
 */
@Service("reporteObserver")
public class ReporteObserver implements Observer {

	@Inject
	private MergeReporteHandler reporteHandler;


	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		ReporteObservable reporteObservable = (ReporteObservable)o;
		
		// Obtiene el listado de reportes actualizados
		List<Reporte> reportesActualizados = reporteObservable.getReportes();
		if (CollectionUtils.isNotEmpty(reportesActualizados)) {
			reporteHandler.handle(reportesActualizados, reporteObservable.getIdConciliacion(), reporteObservable.getIdEntidad());
		}
	}

}
