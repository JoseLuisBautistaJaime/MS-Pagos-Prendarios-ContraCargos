/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;


/**
 * @name ConciliacionHelperImpl
 * @description Clase helper con metodos comunes usados para validacion y acciones comunes
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionHelperImpl implements ConciliacionHelper {


	/**
	 * Conciliacion repository
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper#getConciliacionByFolio(java.lang.Integer)
	 */
	public Conciliacion getConciliacionByFolio(Integer folio) throws ConciliacionException {

		Conciliacion conciliacion = null;
		try {
			conciliacion = this.conciliacionRepository.findByFolio(folio);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("No existe conciliacion para el folio " + folio);
		}
		return conciliacion;
	}

}
