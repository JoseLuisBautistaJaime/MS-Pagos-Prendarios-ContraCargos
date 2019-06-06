/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.processor;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.observer.MergeReporteHandler;

/**
 * Nombre: ConciliacionMergeProcessorChain
 * Descripcion: Clase obstracta que representa un proceso dentro de la cadena de procesamiento de la conciliacion
 *
 * @author JGALVEZ
 * Fecha: 04/06/2019 9:44 PM
 * @version 0.1
 */
public abstract class ConciliacionProcessorChain {

	protected MergeReporteHandler mergeReporteHandler;

	protected ConciliacionProcessorChain next;


	/**
	 * Setea el siguiente procesador dentro de la cadena
	 * @param next
	 */
	public void setNextProcessor(ConciliacionProcessorChain next) {
		this.next = next;
	}


	/**
	 * Se encarga de procesar el reporte
	 * @param reporte
	 * @throws ConciliacionException
	 */
	public abstract void process(Reporte reporte) throws ConciliacionException;

}
