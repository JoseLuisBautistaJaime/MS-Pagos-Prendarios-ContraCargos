/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoConciliacion;

/**
 * @name ConciliacionDataValidator
 * @description Clase que contiene metodos de verificacion de existencia de
 *              registros de base de datos tal como ids de conciliaicon, ids de
 *              movimientos, etc.
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 25/07/2019 14:14 hrs.
 * @version 0.1
 */
@Component
public class ConciliacionDataValidator {

	/**
	 * Repository de conciliacion
	 */
	@Autowired
	private ConciliacionRepository conciliacionRepository;

	/**
	 * Repository de Movimientos conciliacion
	 */
	@Autowired
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

	private ConciliacionDataValidator() {
		super();
	}

	/**
	 * Regresa un valor booleano indicando si una conciliacion existe por su folio
	 * 
	 * @param folio
	 */
	public void validateFolioExists(final Integer folio) {
		try {
			if (null != folio) {
				Optional<Conciliacion> conciliacionVal = conciliacionRepository.findById(folio);
				if (!conciliacionVal.isPresent())
					throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND,
							CodigoError.NMP_PMIMONTE_BUSINESS_045);
			} else
				throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_CONCILIACION_VALIDATION,
						CodigoError.NMP_PMIMONTE_BUSINESS_086);
		} catch (ConciliacionException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_CONCILIACION_VALIDATION,
					CodigoError.NMP_PMIMONTE_BUSINESS_086);
		}
	}

	/**
	 * Valida si existen mobvimientos de conciliacion por ids y folio d
	 * econciliacion relacionado
	 * 
	 * @param folio
	 * @param movimientosConciliacion
	 */
	public void validateIdsMovimientosConciliacionExists(final Integer folio,
			final List<Integer> movimientosConciliacion) {
		try {
			if (null != movimientosConciliacion && !movimientosConciliacion.isEmpty()) {
				List<MovimientoConciliacion> movimientos = movimientoConciliacionRepository.findByFolioAndIds(folio,
						movimientosConciliacion);
				if (null == movimientos || movimientos.isEmpty() || movimientos.size() < movimientosConciliacion.size())
					throw new ConciliacionException(
							ConciliacionConstants.NO_RELATION_BETWEEN_CONC_AND_MOVS_OR_DONESNT_EXISTS_SUCH_MOVS,
							CodigoError.NMP_PMIMONTE_BUSINESS_087);
			} else
				throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_CONCILIACION_VALIDATION,
						CodigoError.NMP_PMIMONTE_BUSINESS_086);
		} catch (ConciliacionException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new ConciliacionException(ConciliacionConstants.ERROR_WHILE_ID_CONCILIACION_VALIDATION,
					CodigoError.NMP_PMIMONTE_BUSINESS_086);
		}
	}

}
