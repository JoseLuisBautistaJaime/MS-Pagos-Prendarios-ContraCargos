/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte;
import mx.com.nmp.pagos.mimonte.observable.ReporteObservable;
import mx.com.nmp.pagos.mimonte.observer.ReporteObserver;

/**
 * @name ConciliacionHelperImpl
 * @description Clase helper con metodos comunes usados para validacion y
 *              acciones comunes
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionHelperImpl implements ConciliacionHelper {

	/**
	 * Utilizada para manipular los mensajes informativos y de error.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ConciliacionHelperImpl.class);

	/**
	 * Conciliacion repository
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

	@Autowired
	private ReporteObserver reporteObserver;



	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper#getConciliacionByFolio(java.lang.Long, java.lang.Integer)
	 */
	public Conciliacion getConciliacionByFolio(Long folio, Integer idStatusConciliacion) throws ConciliacionException {

		LOG.info(">> validateFolioExists");

		if (folio == null) {
			LOG.debug("El folio recibido es nulo");
			throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_CONCILIACION_VALIDATION,
					CodigoError.NMP_PMIMONTE_BUSINESS_086);
		}

		Conciliacion conciliacion = null;
		try {
			conciliacion = this.conciliacionRepository.findByFolio(folio);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Conciliacion con el folio " + folio + " no existe", CodigoError.NMP_PMIMONTE_BUSINESS_045);
		}

		if (conciliacion == null) {
			throw new ConciliacionException("Conciliacion con el folio " + folio + " no existe", CodigoError.NMP_PMIMONTE_BUSINESS_045);
		}
		if (idStatusConciliacion != null && conciliacion.getEstatus().getId() != idStatusConciliacion) {
			throw new ConciliacionException("Conciliacion con estado incorrecto", CodigoError.NMP_PMIMONTE_BUSINESS_034);
		}

		return conciliacion;
	}

	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper#generarConciliacion(java.lang.Long, mx.com.nmp.pagos.mimonte.model.conciliacion.Reporte[])
	 */
	@Override
	public void generarConciliacion(Long folio, List<Reporte> reportes) throws ConciliacionException {
		ReporteObservable reporteObservable = new ReporteObservable(reportes, folio);
		reporteObservable.addObserver(reporteObserver);
		reporteObservable.notifyObservers();
	}

}
