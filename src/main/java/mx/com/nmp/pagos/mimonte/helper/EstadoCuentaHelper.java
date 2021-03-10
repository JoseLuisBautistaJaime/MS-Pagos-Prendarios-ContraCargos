/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper;

import java.util.Date;
import java.util.List;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoLayoutEnum;
import mx.com.nmp.pagos.mimonte.util.CodigosEdoCuentaMap;

/**
 * @name EstadoCuentaHelper
 * @description Clase helper con metodos comunes usados para validacion y acciones comunes de estado de cuenta
 *
 * @author Jorge Galvez
 * @creationDate 16/20/2019 17:34 hrs.
 * @version 0.1
 */
public interface EstadoCuentaHelper {

	/**
	 * Valida si la clave de leyenda del estado de cuenta corresponde a una categoria especifica
	 * @param claveLeyenda
	 * @param idCategoria
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	public boolean isClaveCategoria(String claveLeyenda, Long idCategoria, Long idEntidad) throws ConciliacionException;

	/**
	 * Obtiene la clategoria asignada a la clave
	 * @param claveLeyenda
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	public Long getCategoriaFromClave(String claveLeyenda, Long idEntidad) throws ConciliacionException;

	/**
	 * Regresa el listado de codigos de estado de cuenta para la categoria indicada
	 * @param idCategoria
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	public List<String> getCodigosEstadoCuenta(Long idCategoria, Long idEntidad) throws ConciliacionException;

	/**
	 * Regresa el listado de codigos de estado de cuenta para la categoria y conciliacion.
	 * @param idCategoria
	 * @param idConciliacion
	 * @return
	 * @throws ConciliacionException
	 */
	public List<String> getCodigosEstadoCuentaByConciliacion(Long idCategoria, Long idConciliacion) throws ConciliacionException;

	/**
	 * Regresa el listado de codigos de estado de cuenta
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	public List<String> getCodigosEstadoCuenta(Long idEntidad) throws ConciliacionException;

	/**
	 * Obtiene la categoria asignada a la clave leyenda si existe
	 * @param codigos
	 * @param claveLeyenda
	 * @return
	 */
	public Long getCategoriaFromClave(List<CodigoEstadoCuenta> codigos, String claveLeyenda) throws ConciliacionException;

	/**
	 * Obtiene los codigos de estado de cuenta agrupados por categoria
	 * @param idEntidad
	 * @return
	 * @throws ConciliacionException
	 */
	public CodigosEdoCuentaMap getCodigosEdoCuentaMap(Long idEntidad) throws ConciliacionException;

	/**
	 * Obtiene los movimientos de estado de cuenta por categoria
	 * 
	 * @param idConciliacion
	 * @param idCategoria
	 * @param idEntidad
	 * @return
	 */
	public List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaByCategoria(Long idConciliacion, Long idCategoria, Long idEntidad) throws ConciliacionException;

	/**
	 * Obtiene los movimientos del estado de cuenta por category y conciliacion
	 * @param idConciliacion
	 * @param idCategoria
	 * @return
	 * @throws ConciliacionException
	 */
	public List<MovimientoEstadoCuenta> getMovimientosEstadoCuentaByCategoria(Long idConciliacion, Long idCategoria) throws ConciliacionException;

	/**
	 * Obtiene un movimiento del estado de cuenta cargado a la conciliacion para obtener la fecha de la operacion
	 * @param idConciliacion
	 * @param corresponsal
	 * @param tipoLayout 
	 * @return
	 * @throws ConciliacionException
	 */
	public Date getFechaOperacionEstadoCuenta(Long idConciliacion, CorresponsalEnum corresponsal, TipoLayoutEnum tipoLayout) throws ConciliacionException;

}
