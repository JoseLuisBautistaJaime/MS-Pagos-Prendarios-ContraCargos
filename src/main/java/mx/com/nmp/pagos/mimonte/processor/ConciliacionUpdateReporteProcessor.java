/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.Date;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ReportesWrapper;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ConciliacionMerge;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionUpdateReporteProcessor
 * Descripcion: Clase que se encarga de actualizar las fechas del ultimo merge de cada reporte
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ConciliacionUpdateReporteProcessor extends ConciliacionProcessorChain {


	public ConciliacionUpdateReporteProcessor(MergeReporteHandler mergeReporteHandler) {
		super(mergeReporteHandler);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(ReportesWrapper reportesWrapper) throws ConciliacionException {

		// Actualiza los merge
		Conciliacion conciliacion = this.mergeReporteHandler.getConciliacionRepository().findByFolio(reportesWrapper.getIdConciliacion());
		ConciliacionMerge merge = conciliacion.getMerge();
		if (merge == null) {
			merge = new ConciliacionMerge();
			conciliacion.setMerge(merge);
		}

		// Actualiza fechas para cada reporte
		if (reportesWrapper.contains(TipoReporteEnum.MIDAS)) {
			merge.setMidasLastUpdated(new Date());
		}
		if (reportesWrapper.contains(TipoReporteEnum.PROVEEDOR)) {
			merge.setProveedorLastUpdated(new Date());
		}
		if (reportesWrapper.contains(TipoReporteEnum.ESTADO_CUENTA)) {
			merge.setEstadoCuentaLastUpdated(new Date());
		}
		
		// Se guarda el merge
		this.mergeReporteHandler.getConciliacionRepository().save(conciliacion);

		// Siguiente procesador
		processNext(reportesWrapper);
	}

}
