/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientosMidasRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoMidas;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ConciliacionProcessor
 * Descripcion: Clase que se encarga de procesar el reporte midas y generar los movimientos correspondientes (transito)
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public class ConciliacionReporteMidasProcessor extends ConciliacionProcessorChain {

	private MovimientosMidasRepository movimientosMidasRepository;

	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(Reporte reporte) throws ConciliacionException {
	
		if (reporte.getTipo() == TipoReporteEnum.MIDAS) {
			
			// Obtiene los movimientos midas
			List<MovimientoMidas> movimientosMidas = movimientosMidasRepository.findByReporteId(reporte.getId());
			if (CollectionUtils.isNotEmpty(movimientosMidas)) {
				
				// Por cada movimiento verifica si se encuentra en error o es correcto
				
				
			}
			
		}
		if (next != null) {
			next.process(reporte);
		}
	}

}
