/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoReporteEnum;

/**
 * Nombre: ConciliacionProcessor
 * Descripcion: Clase que se encarga de procesar el reporte Estado Cuenta y generar los movimientos correspondientes devoluciones
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
@Component
@Service("conciliacionReporteEstadoCuentaProcessor")
public class ConciliacionReporteEstadoCuentaProcessor extends ConciliacionProcessorChain {


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.processor.ConciliacionProcessorChain#process(mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte)
	 */
	public void process(Reporte reporte) throws ConciliacionException {
	
		if (reporte.getTipo() == TipoReporteEnum.PROVEEDOR) {
			
		}
		if (next != null) {
			next.process(reporte);
		}
	}

}
