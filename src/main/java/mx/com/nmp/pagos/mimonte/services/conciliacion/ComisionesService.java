/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.ComisionesBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionTransaccionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionesRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.MovimientoConciliacionRepository;
import mx.com.nmp.pagos.mimonte.dto.ComisionSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionDeleteDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionSaveResponseDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransProyeccionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransRealDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesOperacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ComisionesTransaccionesRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ProyeccionComisionDTO;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.OperacionComisionProyeccionEnum;
import mx.com.nmp.pagos.mimonte.model.conciliacion.TipoMovimientoEnum;

/**
 * @name ComisionesService
 * @description Clase de tipo service que contiene los metodos para realizar
 *              operaciones de logica de negocios sobre objetos de tipo
 *              comisiones.
 *
 * @author Quarksoft
 * @creationDate 31-Mar-2019 6:33:46 PM
 * @version 0.1
 */
@Service("comisionesService")
public class ComisionesService {

	/**
	 * Repository de Movimientos Comision
	 */
	@Autowired
	@Qualifier("comisionesRepository")
	private ComisionesRepository comisionesRepository;

	/**
	 * Repository de Movimientos Conciliacion
	 */
	@Autowired
	@Qualifier("movimientoConciliacionRepository")
	private MovimientoConciliacionRepository movimientoConciliacionRepository;

	/**
	 * Repository de ComisionTransaccionRepository
	 */
	@Autowired
	@Qualifier("comisionTransaccionRepository")
	private ComisionTransaccionRepository comisionTransaccionRepository;

	/**
	 * Propiedad IVA
	 */
	@Value(ConciliacionConstants.ConstantProperties.IVA)
	private String iva;

	/**
	 * 
	 * @param idConciliacion
	 */
	public List<ComisionDTO> listByConciliacion(Long idConciliacion) {
		return null;
	}

	/**
	 * 
	 * @param fechaDesde
	 * @param fechaHasta
	 */
	public ProyeccionComisionDTO getProyeccion(Date fechaDesde, Date fechaHasta) {
		return null;
	}

	/**
	 * Regresa una lista con las comisiones por folio de conciliacion
	 * 
	 * @param folio
	 * @return
	 */
	public List<ComisionDTO> findByFolio(final Integer folio) {
		List<ComisionDTO> comisionDTOList = null;
		if (null != folio) {
			comisionDTOList = comisionesRepository.findByFolio(folio);
		}
		return comisionDTOList;
	}

	/**
	 * Regresa un objeto de tipo ComisionesTransDTO con las proyecciones de pagos y
	 * devoluciones y el total real de comisiones
	 * 
	 * @param comisionesTransaccionesRequestDTO
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ComisionesTransDTO findByFechasAndComision(
			final ComisionesTransaccionesRequestDTO comisionesTransaccionesRequestDTO, String requestUser) {
		// Declaracion de objetos
		List<ComisionesTransaccionesOperacionDTO> comisionesTransaccionesOperacionDTOList = new ArrayList<>();
		ComisionesTransDTO comisionesTransDTO = new ComisionesTransDTO();
		ComisionesTransProyeccionDTO comisionesTransProyeccionDTO = new ComisionesTransProyeccionDTO();
		ComisionesTransRealDTO comisionesTransRealDTO = new ComisionesTransRealDTO();
		ComisionesTransaccionesOperacionDTO movimientoPagos = new ComisionesTransaccionesOperacionDTO();
		ComisionesTransaccionesOperacionDTO movimientoDevoluciones = new ComisionesTransaccionesOperacionDTO();
		ComisionTransaccion comisionTransaccion = null;
		BigDecimal totalOperaciones = null;
		BigDecimal sumaComision = null;
		BigDecimal sumaIva = null;
		// Encontrar suma total de pagos
		Long transaccionesPagos = comisionesRepository.findTransaccionesPagosByFechas(
				comisionesTransaccionesRequestDTO.getFechaDesde(), comisionesTransaccionesRequestDTO.getFechaHasta());
		// Encontrar suma total de devoluciones
		Long transaccionesDevoluciones = comisionesRepository.findTransaccionesDevolucionesByFechas(
				comisionesTransaccionesRequestDTO.getFechaDesde(), comisionesTransaccionesRequestDTO.getFechaHasta());
		// Se obtiene el id de conciliacion asociado a las comisiones reales
		Integer idConciliacion = comisionesRepository.findIdConciliacionByFechas(
				comisionesTransaccionesRequestDTO.getFechaDesde(), comisionesTransaccionesRequestDTO.getFechaHasta());
		// Construir objeto pagos
		buildMovimiento(movimientoPagos, OperacionComisionProyeccionEnum.PAGOS.getDescripcion(), transaccionesPagos,
				comisionesTransaccionesRequestDTO.getComision());
		// Construir objeto devoluciones
		buildMovimiento(movimientoDevoluciones, OperacionComisionProyeccionEnum.DEVOLUCIONES.getDescripcion(),
				transaccionesDevoluciones, comisionesTransaccionesRequestDTO.getComision());
		// Agregar movimientos a la lista
		comisionesTransaccionesOperacionDTOList.add(movimientoPagos);
		comisionesTransaccionesOperacionDTOList.add(movimientoDevoluciones);
		// Agregar lista de operaciones a objeto padre
		comisionesTransProyeccionDTO.setOperaciones(comisionesTransaccionesOperacionDTOList);
		// Agregar suma de total iva + comisiones de pagos y devoluciones
		totalOperaciones = movimientoPagos.getTotalComision().add(movimientoDevoluciones.getTotalComision());
		comisionesTransProyeccionDTO.setTotalOperaciones(totalOperaciones);
		// Poner el objeto proyeccion en el objeto padre
		comisionesTransDTO.setProyeccion(comisionesTransProyeccionDTO);
		// Realiza suma de comisiones
		sumaComision = comisionesRepository.findMovimientosSum(TipoMovimientoEnum.COMISION.getDescripcion());
		// Realiza suma de iva's
		sumaIva = comisionesRepository.findMovimientosSum(TipoMovimientoEnum.IVA_COMISION.getDescripcion());
		// Construye el objeto de comisiones real
		buildComisionReal(comisionesTransRealDTO, sumaComision, sumaIva);
		// Agrega las comisions reales a el objeto padre
		comisionesTransDTO.setReal(comisionesTransRealDTO);
		
		// Guarda las comisiones y proyecciones (NUEVAS ESTRUCTURAS DE DATOS)
		comisionTransaccion = ComisionesBuilder
				.buildComisionTransaccionFromComisionesTransDTOAndComisionesTransaccionesRequestDTO(comisionesTransDTO,
						comisionesTransaccionesRequestDTO, idConciliacion, requestUser);
		comisionTransaccionRepository.save(comisionTransaccion);
		
		// regresa el objeto
		return comisionesTransDTO;
	}

	/**
	 * Guarda una comision, pobla primero la entidad MovimientosConciliacion y
	 * despues MovimientosComision
	 * 
	 * @param comisionSaveDTO
	 * @param requestUser
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public ComisionSaveResponseDTO save(final ComisionSaveDTO comisionSaveDTO, final String requestUser) {
		ComisionSaveResponseDTO comisionSaveResponseDTO = null;
		movimientoConciliacionRepository.save(ComisionesBuilder
				.buildMovimientoConciliacionFromConciliacionIdAndRequestUser(comisionSaveDTO.getFolio(), requestUser));
		comisionesRepository
				.save(ComisionesBuilder.buildMovimientoComisionFromComisionSaveDTO(comisionSaveDTO, requestUser));
		comisionSaveResponseDTO = comisionesRepository.findByIdComision(comisionSaveDTO.getId());
		return comisionSaveResponseDTO;
	}

	/**
	 * Elimina movimientos de tipo comision en base a sus ids
	 * 
	 * @param comisionDeleteDTO
	 * @param requestuser
	 */
	public void delete(final ComisionDeleteDTO comisionDeleteDTO, final String requestUser) {
		comisionesRepository
				.deleteByIdsAndIdConciliacion(comisionDeleteDTO.getIdComisiones()/*
																					 * , comisionDeleteDTO.getFolio()
																					 */);
	}

	/**
	 * Construye el objeto Movimiento para pagos y devocuiones
	 * 
	 * @param movimiento
	 * @param operacion
	 * @param transacciones
	 * @param comision
	 */
	public void buildMovimiento(ComisionesTransaccionesOperacionDTO movimiento, final String operacion,
			final Long transacciones, final BigDecimal comision) {
		movimiento.setOperacion(operacion);
		movimiento.setTransacciones(transacciones);
		movimiento.setComision(comision.multiply(BigDecimal.valueOf(transacciones)));
		movimiento.setIvaComision(comision.multiply(new BigDecimal(iva)));
		movimiento.setTotalComision(movimiento.getComision().add(movimiento.getIvaComision()));
	}

	/**
	 * Construye el objeto de comisiones real
	 * 
	 * @param comisionesTransRealDTO
	 * @param sumaComision
	 * @param sumaIva
	 */
	public void buildComisionReal(ComisionesTransRealDTO comisionesTransRealDTO, final BigDecimal sumaComision,
			final BigDecimal sumaIva) {
		comisionesTransRealDTO.setComision(sumaComision);
		comisionesTransRealDTO.setIvaComision(sumaIva);
		comisionesTransRealDTO.setTotalComision(sumaComision.add(sumaIva));
	}

}