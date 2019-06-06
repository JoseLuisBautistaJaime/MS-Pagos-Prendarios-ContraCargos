package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO;
import mx.com.nmp.pagos.mimonte.model.Cuenta;
import mx.com.nmp.pagos.mimonte.model.Entidad;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:59 PM
 */
@Repository("conciliacionRepository")
public interface ConciliacionRepository extends PagingAndSortingRepository<Conciliacion, Integer> {

	/**
	 * Búsquda de la conciliación a partir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE c.id = :folio")
	public Conciliacion findByFolio(@Param("folio") final Integer folio);

	/**
	 * Búsqueda de la conciliación a partir de del id.
	 */
	public Optional<Conciliacion> findById(Integer id);

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
	 * Búsqueda de la conciliacion a partir del folio, id entidad, id estatus de la
	 * conciliación, fecha desde y fecha hasta.
	 * 
	 * @param folio
	 * @param idEntidad
	 * @param idEstatus
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("FROM Conciliacion c WHERE ( :folio IS NULL OR c.id = :folio ) AND ( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND ( :idEstatus IS NULL OR c.estatus.id = :idEstatus) AND c.createdDate BETWEEN :fechaDesde AND :fechaHasta")
	public List<Conciliacion> findByFolioAndIdEntidadAndIdEstatusAndFecha(@Param("folio") final Integer folio,
			@Param("idEntidad") final Long idEntidad, @Param("idEstatus") final Integer idEstatus,
			@Param("fechaDesde") final Date fechaDesde, @Param("fechaHasta") final Date fechaHasta);
	
	/**
	 * 
	 * @param idEstatus
	 * @param idEntidad
	 * @param identificadorCuenta
	 * @param sucursal
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.DevolucionEntidadDetalleDTO (md.id, c.entidad.id, c.entidad.nombre, c.entidad.description, md.fecha, c.estatus.id, c.estatus.descripcion, c.estatus.estatus, md.sucursal,  md.identificadorCuenta, md.monto, md.esquemaTarjeta, md.titular, md.codigoAutorizacion, md.fechaLiquidacion ) FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id "
			+ " WHERE ( :idEstatus IS NULL OR c.estatus.id = :idEstatus ) AND "
			+ "( :idEntidad IS NULL OR c.entidad.id = :idEntidad ) AND "
			+ "( :identificadorCuenta IS NULL OR md.identificadorCuenta = :identificadorCuenta ) AND "
			+ "( :sucursal IS NULL OR md.sucursal = :sucursal ) AND "
			+ " c.createdDate BETWEEN :fechaDesde AND :fechaHasta ")
	public List<DevolucionEntidadDetalleDTO> findByIdEstatusOrIdEntidadOrIdentificadorCuentaOrSucursal(
			@Param("idEstatus") final Integer idEstatus, 
			@Param("idEntidad") final Long idEntidad, 
			@Param("identificadorCuenta") final String identificadorCuenta,
			@Param("sucursal") final Integer sucursal,
			@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta);
	
	@Query("FROM Conciliacion c INNER JOIN MovimientoConciliacion mc ON c.id = mc.idConciliacion INNER JOIN MovimientoDevolucion md ON mc.id = md.id WHERE md.id = :ids ")
	public List<Conciliacion> findByIdDevolucion(@Param("ids") final Integer ids);
}