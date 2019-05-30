/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dao.conciliacion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;

/**
 * @name ComisionesRepository
 * @description Interface de tipo repository que contiene metodos para realizar
 *              operaciones a nivel de base de datos sobre objetos relacionados
 *              con comisiones.
 *
 * @author Quarksoft
 * @creationDate 31-Mar-2019 6:34:10 PM
 * @version 0.1
 */
@Repository("comisionesRepository")
public interface ComisionesRepository extends JpaRepository<MovimientoComision, Integer> {

	/**
	 * Elimina una comision de la entidad de Movimientos Comisiones
	 * 
	 * @param idsComisiones
	 * @param idConciliacion
	 */
	@Modifying
	@Query("DELETE FROM MovimientoComision mc WHERE mc.id IN :idsComisiones AND mc.id IN (SELECT mcon.id FROM MovimientoConciliacion mcon WHERE mcon.id in :idsComisiones AND mcon.nuevo = true AND mcon.idConciliacion = :idConciliacion)")
	public void deleteByIdsAndIdConciliacion(@Param("idsComisiones") final List<Integer> idsComisiones,
			@Param("idConciliacion") final Integer idConciliacion);

	/**
	 * Regresa el total de movimientos de pagos que estan entre las fechas
	 * especificadas
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT COUNT(mp.id) FROM MovimientoPago mp INNER JOIN MovimientoConciliacion mc ON mp.id = mc.id WHERE mc.createdDate BETWEEN :fechaDesde AND :fechaHasta")
	public Long findTransaccionesPagosByFechas(@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa el total de movimientos de devoluciones que estan entre las fechas
	 * especificadas
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT COUNT(md.id) FROM MovimientoDevolucion md INNER JOIN MovimientoConciliacion mc ON md.id = mc.id WHERE mc.createdDate BETWEEN :fechaDesde AND :fechaHasta")
	public Long findTransaccionesDevolucionesByFechas(@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Encuentra el id de conciliacion para las comisiones reales
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 * @return
	 */
	@Query("SELECT mc.idConciliacion FROM MovimientoDevolucion md INNER JOIN MovimientoConciliacion mc ON md.id = mc.id WHERE mc.createdDate BETWEEN :fechaDesde AND :fechaHasta")
	public Integer findIdConciliacionByFechas(@Param("fechaDesde") final Date fechaDesde,
			@Param("fechaHasta") final Date fechaHasta);

	/**
	 * Regresa la suma de los movimientos segun su tipo
	 * 
	 * @param tipoComision
	 * @return
	 */
	@Query("SELECT SUM(mc) FROM MovimientoComision mc WHERE mc.tipo = :tipoComision")
	public BigDecimal findMovimientosSum(@Param("tipoComision") final String tipoComision);

	/**
	 * Rehresa las comisiones por tipo de operacion (COMISION E IVA_COMISION)
	 * 
	 * @param folio
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDTO(mc.id, mc.fechaOperacion, mc.fechaCargo, mc.monto, mc.descripcion, mcon.nuevo) FROM MovimientoComision mc INNER JOIN MovimientoConciliacion mcon ON mc.id = mcon.id AND mcon.idConciliacion = :folio")
	public List<ComisionDTO> findByFolio(@Param("folio") final Integer folio);

	/**
	 * Regresa un movimiento comision por id
	 * 
	 * @param idComision
	 * @return
	 */
	@Query("SELECT new mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO(mcon.idConciliacion, mc.id, mc.fechaOperacion, mc.fechaCargo, mc.monto, mc.descripcion, mcon.nuevo) FROM MovimientoComision mc INNER JOIN MovimientoConciliacion mcon ON mc.id = mcon.id WHERE mc.id = :idComision")
	public ComisionSaveResponseDTO findByIdComision(@Param("idComision") final Integer idComision);
}