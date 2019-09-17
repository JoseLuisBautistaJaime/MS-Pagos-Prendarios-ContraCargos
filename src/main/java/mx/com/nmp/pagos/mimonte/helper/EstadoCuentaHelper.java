/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.helper;

import java.util.List;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;
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
	 * @return
	 * @throws ConciliacionException
	 */
	public boolean isClaveCategoria(String claveLeyenda, Long idCategoria) throws ConciliacionException;

	/**
	 * Obtiene la clategoria asignada a la clave
	 * @param claveLeyenda
	 * @return
	 * @throws ConciliacionException
	 */
	public Long getCategoriaFromClave(String claveLeyenda) throws ConciliacionException;

	/**
	 * Regresa el listado de codigos de estado de cuenta para la categoria indicada
	 * @param categoriaEdoCuenta
	 * @return
	 * @throws ConciliacionException
	 */
	public List<String> getCodigosEstadoCuenta(Long categoriaEdoCuenta) throws ConciliacionException;

	/**
	 * Regresa el listado de codigos de estado de cuenta
	 * @return
	 * @throws ConciliacionException
	 */
	public List<String> getCodigosEstadoCuenta() throws ConciliacionException;

	/**
	 * Obtiene la categoria asignada a la clave leyenda si existe
	 * @param codigos
	 * @param claveLeyenda
	 * @return
	 */
	public Long getCategoriaFromClave(List<CodigoEstadoCuenta> codigos, String claveLeyenda) throws ConciliacionException;

	/**
	 * Obtiene los codigos de estado de cuenta agrupados por categoria
	 * @return
	 * @throws ConciliacionException
	 */
	public CodigosEdoCuentaMap getCodigosEdoCuentaMap() throws ConciliacionException;

}
