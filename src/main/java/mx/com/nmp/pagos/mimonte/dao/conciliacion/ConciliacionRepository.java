package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:59 PM
 */
@Repository("conciliacionRepository")
public interface ConciliacionRepository extends PagingAndSortingRepository<Conciliacion, Long> {

	/**
	 * Búsquda de la conciliación a partir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE c.id = :folio")
	public Conciliacion findByFolio(@Param("folio") final Long folio);

	/**
	 * Búsqueda de la conciliación a partir de del id.
	 */
	public Optional<Conciliacion> findById(Long id);

	/**
	 * Búsqueda de la conciliacíon a partir de la entidad.
	 * 
	 * @param entidad
	 * @return
	 */
	public List<Conciliacion> findByEntidad(Entidad entidad);

	/**
	 * Búsqueda de la conciliación a partir del estatus de la conciliación.
	 * 
	 * @param estatus
	 * @return
	 */
	public List<Conciliacion> findByEstatus(EstatusConciliacion estatus);

	/**
	 * Búsqueda de la conciliación a partir de la cuenta.
	 * 
	 * @param cuenta
	 * @return
	 */
	public List<Conciliacion> findByCuenta(Cuenta cuenta);

	/**
	 * Busqueda de la conciliacion a partir del folio, id entidad, id estatus de la
	 * conciliacion, fecha desde y fecha hasta.
	 * 
	 * @param folio
	 * @param idEntidad
	 * @param idEstatus
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE ( :folio IS NULL OR c.id = :folio ) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) AND c.createdDate BETWEEN :fechaDesde AND :fechaHasta")
	public List<Conciliacion> findByFolioAndIdEntidadAndIdEstatusAndFechas(@Param("folio") final Long folio,
			@Param("idEntidad") final Long idEntidad, @Param("idEstatus") final Integer idEstatus,
			@Param("fechaDesde") final Date fechaDesde, @Param("fechaHasta") final Date fechaHasta);

	/**
	 * Búsqueda de la conciliacion a partir del folio, id entidad e id estatus de la
	 * conciliacion.
	 * 
	 * @param folio
	 * @param idEntidad
	 * @param idEstatus
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE ( :folio IS NULL OR c.id = :folio ) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND ( :idEstatus IS NULL OR c.estatus.id = :idEstatus)")
	public List<Conciliacion> findByFolioAndIdEntidadAndIdEstatus(@Param("folio") final Long folio,
			@Param("idEntidad") final Long idEntidad, @Param("idEstatus") final Integer idEstatus);

	/**
	 * Busqueda de la conciliacion a partir del folio, id entidad, id estatus de la
	 * conciliacion y fecha desde.
	 * 
	 * @param folio
	 * @param idEntidad
	 * @param idEstatus
	 * @param fechaDesde
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE ( :folio IS NULL OR c.id = :folio ) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) AND c.createdDate >= :fechaDesde")
	public List<Conciliacion> findByFolioAndIdEntidadAndIdEstatusAndFechaDesde(@Param("folio") final Long folio,
			@Param("idEntidad") final Long idEntidad, @Param("idEstatus") final Integer idEstatus,
			@Param("fechaDesde") final Date fechaDesde);

	/**
	 * Busqueda de la conciliacion a partir del folio, id entidad, id estatus de la
	 * conciliacion y fecha hasta.
	 * 
	 * @param folio
	 * @param idEntidad
	 * @param idEstatus
	 * @param fechaHasta
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE ( :folio IS NULL OR c.id = :folio ) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) AND c.createdDate <= :fechaHasta")
	public List<Conciliacion> findByFolioAndIdEntidadAndIdEstatusAndFechaHasta(@Param("folio") final Long folio,
			@Param("idEntidad") final Long idEntidad, @Param("idEstatus") final Integer idEstatus,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa una lista de objetos de tipo DevolucionEntidadDetalleDTO en base a
	 * los parametros recibidos (fecha inicio y fin)
	 * 
	 * @param idEstatus
	 * @param idEntidad
	 * @param identificadorCuenta
	 * @param sucursal
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO (md.id, c.entidad.id, c.entidad.nombre, c.entidad.description, md.fecha, md.estatus.id, c.estatus.descripcion, c.estatus.estatus, md.sucursal,  md.identificadorCuenta, md.monto, md.esquemaTarjeta, md.titular, md.codigoAutorizacion, md.fechaLiquidacion ) FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id "
			+ " WHERE ( :idEstatus IS NULL OR md.estatus.id = :idEstatus ) AND "
			+ "( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND "
			+ "( :identificadorCuenta IS NULL OR md.identificadorCuenta = :identificadorCuenta ) AND "
			+ "( :sucursal IS NULL OR md.sucursal = :sucursal ) AND " + " md.fecha BETWEEN :fechaDesde AND :fechaHasta")
	public List<DevolucionEntidadDetalleDTO> findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursal(
			@Param("idEstatus") final Integer idEstatus, @Param("idEntidad") final Long idEntidad,
			@Param("identificadorCuenta") final String identificadorCuenta, @Param("sucursal") final Integer sucursal,
			@Param("fechaDesde") final Date fechaDesde, @Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa una lista de objetos de tipo DevolucionEntidadDetalleDTO en base a
	 * los parametros recibidos (solo fecha inicio)
	 * 
	 * @param idEstatus
	 * @param idEntidad
	 * @param identificadorCuenta
	 * @param sucursal
	 * @param fechaDesde
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO (md.id, c.entidad.id, c.entidad.nombre, c.entidad.description, md.fecha, md.estatus.id, c.estatus.descripcion, c.estatus.estatus, md.sucursal,  md.identificadorCuenta, md.monto, md.esquemaTarjeta, md.titular, md.codigoAutorizacion, md.fechaLiquidacion ) FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id "
			+ " WHERE ( :idEstatus IS NULL OR md.estatus.id = :idEstatus ) AND "
			+ "( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND "
			+ "( :identificadorCuenta IS NULL OR md.identificadorCuenta = :identificadorCuenta ) AND "
			+ "( :sucursal IS NULL OR md.sucursal = :sucursal ) AND " + " md.fecha >= :fechaDesde")
	public List<DevolucionEntidadDetalleDTO> findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaHasta(
			@Param("idEstatus") final Integer idEstatus, @Param("idEntidad") final Long idEntidad,
			@Param("identificadorCuenta") final String identificadorCuenta, @Param("sucursal") final Integer sucursal,
			@Param("fechaDesde") final Date fechaDesde);

	/**
	 * Regresa una lista de objetos de tipo DevolucionEntidadDetalleDTO en base a
	 * los parametros recibidos (solo fecha fin)
	 * 
	 * @param idEstatus
	 * @param idEntidad
	 * @param identificadorCuenta
	 * @param sucursal
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO (md.id, c.entidad.id, c.entidad.nombre, c.entidad.description, md.fecha, md.estatus.id, c.estatus.descripcion, c.estatus.estatus, md.sucursal,  md.identificadorCuenta, md.monto, md.esquemaTarjeta, md.titular, md.codigoAutorizacion, md.fechaLiquidacion ) FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id "
			+ " WHERE ( :idEstatus IS NULL OR md.estatus.id = :idEstatus ) AND "
			+ "( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND "
			+ "( :identificadorCuenta IS NULL OR md.identificadorCuenta = :identificadorCuenta ) AND "
			+ "( :sucursal IS NULL OR md.sucursal = :sucursal ) AND " + " md.fecha <= :fechaHasta")
	public List<DevolucionEntidadDetalleDTO> findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechaDesde(
			@Param("idEstatus") final Integer idEstatus, @Param("idEntidad") final Long idEntidad,
			@Param("identificadorCuenta") final String identificadorCuenta, @Param("sucursal") final Integer sucursal,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa una lista de objetos de tipo DevolucionEntidadDetalleDTO en base a
	 * los parametros recibidos (sin fechas)
	 * 
	 * @param idEstatus
	 * @param idEntidad
	 * @param identificadorCuenta
	 * @param sucursal
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO (md.id, c.entidad.id, c.entidad.nombre, c.entidad.description, md.fecha, md.estatus.id, c.estatus.descripcion, c.estatus.estatus, md.sucursal,  md.identificadorCuenta, md.monto, md.esquemaTarjeta, md.titular, md.codigoAutorizacion, md.fechaLiquidacion ) FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id "
			+ " WHERE ( :idEstatus IS NULL OR md.estatus.id = :idEstatus ) AND "
			+ "( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND "
			+ "( :identificadorCuenta IS NULL OR md.identificadorCuenta = :identificadorCuenta ) AND "
			+ "( :sucursal IS NULL OR md.sucursal = :sucursal )")
	public List<DevolucionEntidadDetalleDTO> findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursalWOFechas(
			@Param("idEstatus") final Integer idEstatus, @Param("idEntidad") final Long idEntidad,
			@Param("identificadorCuenta") final String identificadorCuenta, @Param("sucursal") final Integer sucursal);

	/**
	 * Regresa una lista de entities de tipo Conciliacion en base a los ids de
	 * devolucion especificados
	 * 
	 * @param ids
	 * @return
	 */
	@Query("FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id WHERE md.id = :ids ")
	public List<Conciliacion> findByIdDevolucion(@Param("ids") final Integer ids);

	/**
	 * Regresa un mapa con tres valores, [en_proceso]: Con el total de las
	 * conciliaciones en proceso, [dev_liquidadas]: Con el total de las devoluciones
	 * liquidadas y [conc_totales]: Con el total de conciliaciones, esto con un
	 * filtro de fecha que se aplica para la fecha y creacion o modificacion de la
	 * conciliacion y el campo fecha de el movimiento devolucion
	 * 
	 * @param fechaIncial
	 * @param fechaFinal
	 * @param estatusConcProcesada
	 * @param estatusDevLiquidada
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT " + "(SELECT COUNT(*) " + "FROM to_conciliacion "
			+ "	WHERE id_estatus_conciliacion = :estatusConcProcesada " + "		AND " + "			CASE "
			+ "				WHEN last_modified_date IS NOT NULL "
			+ "					THEN last_modified_date BETWEEN :fechaIncial AND :fechaFinal "
			+ "				ELSE created_date BETWEEN :fechaIncial AND :fechaFinal END " + "	) AS en_proceso, "
			+ "(SELECT COUNT(*) " + "	FROM to_movimiento_devolucion " + "	WHERE estatus = :estatusDevLiquidada "
			+ "		AND fecha BETWEEN :fechaIncial AND :fechaFinal " + "    ) AS dev_liquidadas, " + "(SELECT COUNT(*) "
			+ "	FROM to_conciliacion " + "    ) AS conc_totales;")
	public Map<String, BigInteger> resumenConciliaciones(@Param("fechaIncial") Date fechaIncial,
			@Param("fechaFinal") Date fechaFinal, @Param("estatusConcProcesada") final Integer estatusConcProcesada,
			@Param("estatusDevLiquidada") final Integer estatusDevLiquidada);

	/**
	 * Regresa un mapa con tres valores, [en_proceso]: Con el total de las
	 * conciliaciones en proceso, [dev_liquidadas]: Con el total de las devoluciones
	 * liquidadas y [conc_totales]: Con el total de conciliaciones
	 * 
	 * @param estatusConcProcesada
	 * @param estatusDevLiquidada
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT " + "(SELECT COUNT(*) " + "FROM to_conciliacion "
			+ "	WHERE id_estatus_conciliacion = :estatusConcProcesada " + "	) AS en_proceso, " + "(SELECT COUNT(*) "
			+ "	FROM to_movimiento_devolucion " + "	WHERE estatus = :estatusDevLiquidada " + "    ) AS dev_liquidadas, "
			+ "(SELECT COUNT(*) " + "	FROM to_conciliacion " + "    ) AS conc_totales;")
	public Map<String, BigInteger> resumenConciliaciones(
			@Param("estatusConcProcesada") final Integer estatusConcProcesada,
			@Param("estatusDevLiquidada") final Integer estatusDevLiquidada);

	/**
	 * Regresa el id de estatus de una conciliacion dependiendo de su id de
	 * subestatus
	 * 
	 * @param idSubEstatus
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT DISTINCT tr.id_estatus AS estatus, ec.order_number AS estatus_order, sc.order_number AS sub_estatus_order FROM tr_estatus_conciliacion_sub_estatus_conciliacion tr INNER JOIN tk_estatus_conciliacion ec ON ec.id = tr.id_estatus INNER JOIN tk_sub_estatus_conciliacion sc ON sc.id = tr.id_sub_estatus WHERE tr.id_sub_estatus = :idSubEstatus ORDER BY id_estatus ASC LIMIT 1")
	public Map<String, Object> findIdEstatusConciliacion(@Param("idSubEstatus") Long idSubEstatus);

	/**
	 * Se actualiza el subestatus de una conciliacion por folio de la misma
	 * 
	 * @param folio
	 * @param subEstatus
	 * @param usuario
	 * @param fecha
	 */
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE to_conciliacion SET id_sub_estatus_conciliacion = :subEstatus, id_estatus_conciliacion = :estatusConciliacion, sub_estatus_descripcion = :descripcion, last_modified_by = :usuario, last_modified_date = :fecha WHERE id = :folio")
	public void actualizaSubEstatusConciliacion(@Param("folio") final Long folio,
			@Param("subEstatus") SubEstatusConciliacion subEstatus, @Param("usuario") final String usuario,
			@Param("fecha") Date fecha, @Param("estatusConciliacion") final EstatusConciliacion estatusConciliacion,
			@Param("descripcion") final String descripcion);

	/**
	 * Regresa el orden de sub estatus y estatus de conciliaicon en un mapa por
	 * folio de conciliacion
	 * 
	 * @param folio
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT ec.order_number AS estatus_order, sc.order_number AS sub_estatus_order FROM tk_estatus_conciliacion ec INNER JOIN to_conciliacion c ON c.id_estatus_conciliacion = ec.id INNER JOIN tk_sub_estatus_conciliacion sc ON sc.id = c.id_sub_estatus_conciliacion where C.ID = :folio")
	public Map<String, Object> findOrderSubstatusAndStatusByFolio(@Param("folio") final Long folio);

	/**
	 * Regresa una conciliacion por id de entidad, id de cuenta y fecha creacion
	 * 
	 * @param entidadId
	 * @param cuentaId
	 * @param createdDate
	 * @return
	 */
	public Conciliacion findByEntidadIdAndCuentaIdAndCreatedDate(Long entidadId, Long cuentaId, Date createdDate);

	/**
	 * Regresa un valor booleano indicando si una cuenta esta relacionada con una
	 * entidad por sus ids
	 * 
	 * @param idCuenta
	 * @param idEntidad
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN (SELECT COUNT(*) FROM tr_entidad_cuenta_afiliacion tec where tec.id_entidad = :idEntidad AND tec.id_cuenta = :idCuenta ) > 0 THEN TRUE ELSE FALSE END AS result")
	public Object checkCuentaEntidadRelationship(@Param("idCuenta") final Long idCuenta,
			@Param("idEntidad") final Long idEntidad);

	/**
	 * Regresa un valor verdadero (numero 1) si el subestatus recibido es valido
	 * para el subestatus inicial indicado
	 * 
	 * @param folio
	 * @param idSubEstatus
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN :idSubEstatus IN (SELECT id_sub_estatus_posible FROM tk_maquina_estados_subestatus_conciliacion WHERE id_sub_estatus_inicial = (SELECT id_sub_estatus_conciliacion FROM to_conciliacion WHERE id = :folio)) THEN TRUE ELSE FALSE END AS RESULT")
	public Object checkIfSubEstatusIsRightByFolioAnfIdSubEstatus(@Param("folio") final Long folio,
			@Param("idSubEstatus") final Long idSubEstatus);

	/**
	 * Regresa un nombre de entidad y un numero de cuenta por folio de conciliacion
	 * asociado en la tabla to_conciliacion
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT en.nombre AS entidad, cta.numeroCuenta AS cuenta FROM Entidad en INNER JOIN Conciliacion c ON en.id = c.entidad.id INNER JOIN Cuenta cta ON cta.id = c.cuenta.id WHERE c.id = :folio")
	public Map<String, Object> getEntidadNombreAndCuentaNumeroByConciliacionId(@Param("folio") final Long folio);

	/**
	 * Regresa un valor 1 cuando la conciliacion tiene al menos uno de los estatus
	 * especificados, y un 0 en caso contrario
	 * 
	 * @param folio
	 * @param estatus
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT CASE WHEN ((SELECT COUNT(con.id) FROM to_conciliacion con WHERE con.id = :folio AND con.id_sub_estatus_conciliacion IN :subEstatusList) > 0) THEN 1 ELSE 0 END")
	public Object checkIfConciliciacionHasValidStatus(@Param("folio") final Long folio,
			@Param("subEstatusList") final List<Long> subEstatusList);

	/**
	 * 
	 * @param idConciliacion
	 * @return
	 */
	@Query("SELECT con.estatus FROM Conciliacion con WHERE con.id = :idConciliacion")
	public EstatusConciliacion findEstatusByConciliacionId(@Param("idConciliacion") final Long idConciliacion);

	/**
	 * Regresa una lista con los ids iniciales que debe tener una conciliacion para
	 * poder llegar al subestatus especificado como parametro
	 * 
	 * @param idSubEstatus
	 * @return
	 */
	@Query(nativeQuery = true, value = "SELECT me.id_sub_estatus_inicial FROM tk_maquina_estados_subestatus_conciliacion me WHERE me.id_sub_estatus_posible = :idSubEstatus")
	public List<Object> getPossibleSubestatusList(@Param("idSubEstatus") final Long idSubEstatus);

}