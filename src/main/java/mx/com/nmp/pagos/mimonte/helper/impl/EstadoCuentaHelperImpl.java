/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.dao.CodigoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.dao.EntidadRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoEstadoCuentaRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.util.CodigosEdoCuentaMap;

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
public class EstadoCuentaHelperImpl implements EstadoCuentaHelper {

	/**
	 * Utilizada para manipular los mensajes informativos y de error.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EstadoCuentaHelperImpl.class);

	/**
	 * Codigo de estado de cuenta repository
	 */
	@Autowired
	private CodigoEstadoCuentaRepository codigoEstadoCuentaRepository;

	/**
	 * Movimiento estado de cuenta repository
	 */
	@Autowired
	private MovimientoEstadoCuentaRepository movimientoEstadoCuentaRepository;

	/**
	 * Entidad repository
	 */
	@Autowired
	private EntidadRepository entidadRepository;




	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#isClaveCategoria(java.lang.String, java.lang.Long, java.lang.Long)
	 */
	public boolean isClaveCategoria(String claveLeyenda, Long idCategoria, Long idEntidad) throws ConciliacionException {

		LOG.debug("isClaveCategoria {}, {}", claveLeyenda, idCategoria);

		List<CodigoEstadoCuenta> codigos = null;
		try {
			codigos = codigoEstadoCuentaRepository.findByCategoriaIdAndEstatusAndEntidadId(idCategoria, true, idEntidad);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("No existen codigos de estado de cuenta para la categoria " + idCategoria, CodigoError.NMP_PMIMONTE_0011);
		}

		if (codigos == null || codigos.size() == 0) {
			throw new ConciliacionException("No existen codigos de estado de cuenta para la categoria " + idCategoria, CodigoError.NMP_PMIMONTE_0005);
		}

		return isClaveLeyendaInCodigos(codigos, claveLeyenda);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCategoriaFromClave(java.lang.String, java.lang.Long)
	 */
	@Override
	public Long getCategoriaFromClave(String claveLeyenda, Long idEntidad) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigos = null;
		try {
			codigos = codigoEstadoCuentaRepository.findAllByEstatusAndEntidadId(true, idEntidad);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Ocurrio un error al consultar los codigos de estado de cuenta", CodigoError.NMP_PMIMONTE_0011);
		}

		if (codigos == null || codigos.size() == 0) {
			throw new ConciliacionException("No existen codigos de estado de cuenta configurados", CodigoError.NMP_PMIMONTE_0005);
		}

		return getCategoriaFromClave(codigos, claveLeyenda);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCodigosEstadoCuenta(java.lang.Long, java.lang.Long)
	 */
	public List<String> getCodigosEstadoCuenta(Long idCategoria, Long idEntidad) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigosComisiones = null;
		try {
			codigosComisiones = this.codigoEstadoCuentaRepository.findByCategoriaIdAndEstatusAndEntidadId(idCategoria, true, idEntidad);
		} catch (Exception ex) {
			throw new ConciliacionException("Error al obtener los codigos de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_072);
		}

		if (CollectionUtils.isEmpty(codigosComisiones)) {
			throw new ConciliacionException(
					"No existen codigos de estado de cuenta para la categoria de comisiones configurados",
					CodigoError.NMP_PMIMONTE_BUSINESS_073);
		}

		List<String> codigosEstadoCuenta = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(codigosComisiones)) {
			for (CodigoEstadoCuenta codigoComision : codigosComisiones) {
				codigosEstadoCuenta.add(codigoComision.getCodigo());
			}
		}

		return codigosEstadoCuenta;
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCodigosEstadoCuenta(java.lang.Long, java.lang.Long)
	 */
	public List<String> getCodigosEstadoCuentaByConciliacion(Long idCategoria, Long idConciliacion) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigosComisiones = null;
		try {
			
			Entidad entidad = this.entidadRepository.findByConciliacion(idConciliacion);
			if (entidad == null) {
				throw new ConciliacionException("La conciliacion " + idConciliacion + " no tiene asignada una entidad");
			}
			codigosComisiones = this.codigoEstadoCuentaRepository.findByCategoriaIdAndEstatusAndEntidadId(idCategoria, true, entidad.getId());
		} catch (Exception ex) {
			throw new ConciliacionException("Error al obtener los codigos de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_072);
		}

		if (CollectionUtils.isEmpty(codigosComisiones)) {
			throw new ConciliacionException(
					"No existen codigos de estado de cuenta para la categoria de comisiones configurados",
					CodigoError.NMP_PMIMONTE_BUSINESS_073);
		}

		List<String> codigosEstadoCuenta = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(codigosComisiones)) {
			for (CodigoEstadoCuenta codigoComision : codigosComisiones) {
				codigosEstadoCuenta.add(codigoComision.getCodigo());
			}
		}

		return codigosEstadoCuenta;
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCodigosEstadoCuenta(java.lang.Long)
	 */
	public List<String> getCodigosEstadoCuenta(Long idEntidad) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigos = null;
		try {
			codigos = this.codigoEstadoCuentaRepository.findAllByEstatusAndEntidadId(true, idEntidad);
		} catch (Exception ex) {
			throw new ConciliacionException("Error al obtener los codigos de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_072);
		}

		if (CollectionUtils.isEmpty(codigos)) {
			throw new ConciliacionException(
					"No existen codigos de estado de cuenta configurados",
					CodigoError.NMP_PMIMONTE_BUSINESS_073);
		}

		List<String> codigosEstadoCuenta = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(codigos)) {
			for (CodigoEstadoCuenta codigo : codigos) {
				codigosEstadoCuenta.add(codigo.getCodigo());
			}
		}

		return codigosEstadoCuenta;
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCategoriaFromClave(java.util.List, java.lang.String)
	 */
	public Long getCategoriaFromClave(List<CodigoEstadoCuenta> codigos, String claveLeyenda) {
		// Verificar si el codigo existe
		Long idCategoria = null;
		for (CodigoEstadoCuenta codigo : codigos) {
			if (codigo.getCodigo().equalsIgnoreCase(claveLeyenda)) {
				idCategoria = codigo.getCategoria().getId();
				break;
			}
		}
		return idCategoria;
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getCodigosEdoCuentaMap()
	 */
	public CodigosEdoCuentaMap getCodigosEdoCuentaMap(Long idEntidad) throws ConciliacionException {

		List<CodigoEstadoCuenta> codigos = null;
		try {
			codigos = this.codigoEstadoCuentaRepository.findAllByEstatusAndEntidadId(true, idEntidad);
		} catch (Exception ex) {
			throw new ConciliacionException("Error al obtener los codigos de estado de cuenta",
					CodigoError.NMP_PMIMONTE_BUSINESS_072);
		}

		if (CollectionUtils.isEmpty(codigos)) {
			throw new ConciliacionException(
					"No existen codigos de estado de cuenta configurados",
					CodigoError.NMP_PMIMONTE_BUSINESS_073);
		}

		return new CodigosEdoCuentaMap(codigos);
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getMovimientosEstadoCuentaByCategoria(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	public List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaByCategoria(Long idConciliacion, Long idCategoria, Long idEntidad) throws ConciliacionException {

		// Codigos de Estado de Cuenta
		List<String> codigosEstadoCuenta = getCodigosEstadoCuenta(idCategoria, idEntidad);

		// Obtiene los movimientos de estado de cuenta que contiene el codigo
		// correspondiente
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = movimientoEstadoCuentaRepository.findByConciliacionAndClaveLeyendaIn(idConciliacion, codigosEstadoCuenta);

		return movimientosEstadoCuenta;
	}


	/* (non-Javadoc)
	 * @see mx.com.nmp.pagos.mimonte.helper.EstadoCuentaHelper#getMovimientosEstadoCuentaByCategoria(java.lang.Long, java.lang.Long)
	 */
	public List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaByCategoria(Long idConciliacion, Long idCategoria) throws ConciliacionException {

		// Codigos de Estado de Cuenta
		List<String> codigosEstadoCuenta = getCodigosEstadoCuentaByConciliacion(idCategoria, idConciliacion);

		// Obtiene los movimientos de estado de cuenta que contiene el codigo
		// correspondiente
		List<MovimientoEstadoCuenta> movimientosEstadoCuenta = movimientoEstadoCuentaRepository.findByConciliacionAndClaveLeyendaIn(idConciliacion, codigosEstadoCuenta);

		return movimientosEstadoCuenta;
	}


	/**
	 * Verifica si el codigo del estado de cuenta se encuentra dentro del listado de codigos
	 * @param codigos
	 * @param claveLeyenda
	 * @return
	 */
	private boolean isClaveLeyendaInCodigos(List<CodigoEstadoCuenta> codigos, String claveLeyenda) {
		Long idCategoria = getCategoriaFromClave(codigos, claveLeyenda);
		return idCategoria != null;
	}

}
