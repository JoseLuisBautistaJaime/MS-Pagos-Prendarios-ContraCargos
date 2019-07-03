/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import mx.com.nmp.pagos.mimonte.builder.conciliacion.ComisionesBuilder;
import mx.com.nmp.pagos.mimonte.constans.ConciliacionConstants;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionTransaccionProyeccionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionTransaccionRealRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionTransaccionRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ComisionesRepository;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
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
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.exception.InformationNotFoundException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.ComisionTransaccionReal;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoComision;
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
	 * Repository de Conciliacion
	 */
	@Autowired
	@Qualifier("conciliacionRepository")
	private ConciliacionRepository conciliacionRepository;

	/**
	 * repository de ComisionTransaccionProyeccion
	 */
	@Autowired
	@Qualifier("comisionTransaccionProyeccionRepository")
	private ComisionTransaccionProyeccionRepository comisionTransaccionProyeccionRepository;

	/**
	 * repository de ComsionTransaccionReal
	 */
	@Autowired
	@Qualifier("comisionTransaccionRealRepository")
	private ComisionTransaccionRealRepository comisionTransaccionRealRepository;

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
		if (null == comisionDTOList || comisionDTOList.isEmpty())
			throw new InformationNotFoundException(ConciliacionConstants.INFORMATION_NOT_FOUND);
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
		Map<String, Object> mapResult = null;
		Map<String, BigDecimal> sums = null;
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
		Long transaccionesPagos = comisionesRepository.findTransaccionesPagosByFechasAndIdConciliacion(
				comisionesTransaccionesRequestDTO.getFechaDesde(), comisionesTransaccionesRequestDTO.getFechaHasta(),
				comisionesTransaccionesRequestDTO.getIdConciliacion());
		// Se obtiene el id de devolucion, el conteo del mismo y el id de la
		// conciliacion
		mapResult = comisionesRepository.findDataByFechasAndIdConciliacion(
				comisionesTransaccionesRequestDTO.getFechaDesde(), comisionesTransaccionesRequestDTO.getFechaHasta(),
				comisionesTransaccionesRequestDTO.getIdConciliacion());
		Long transaccionesDevoluciones = (Long) mapResult.get("countId");
		Optional<Conciliacion> conciliacion = conciliacionRepository
				.findById(comisionesTransaccionesRequestDTO.getIdConciliacion());
		if (null == comisionesTransaccionesRequestDTO.getIdConciliacion())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_NOT_FOUND_FOR_SUCH_PARAMS);
		if (null == conciliacion || !conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND);
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
		// Realiza suma de comisiones e ivas
		sums = comisionesRepository.findMovimientosSum(TipoMovimientoEnum.COMISION.getDescripcion(),
				TipoMovimientoEnum.IVA_COMISION.getDescripcion());
		sumaComision = sums.get("comision");
		sumaIva = sums.get("iva");
		sumaComision = null != sumaComision ? sumaComision : new BigDecimal("0");
		sumaIva = null != sumaIva ? sumaIva : new BigDecimal("0");
		// Construye el objeto de comisiones real
		buildComisionReal(comisionesTransRealDTO, sumaComision, sumaIva);
		// Agrega las comisions reales a el objeto padre
		comisionesTransDTO.setReal(comisionesTransRealDTO);

		// Guarda las comisiones (NUEVAS ESTRUCTURAS DE DATOS)
		// Se hace esto de la lista de enteros y despues Long por que por alguna razon
		// siempre el query regresa una lista de enteros aunque se especifique un
		// retorno de tipo List<Long> o ArrayList<Long>
		List<Integer> comisionTransaccionVerInteger = null;
		comisionTransaccionVerInteger = comisionTransaccionRepository
				.findByConciliacionId(comisionesTransaccionesRequestDTO.getIdConciliacion());
		Long comisionTransaccionVer = null;
		if (null != comisionTransaccionVerInteger && !comisionTransaccionVerInteger.isEmpty()) {
			for (Integer val : comisionTransaccionVerInteger) {
				comisionTransaccionVer = Long.valueOf(val);
				break;
			}
		}
		comisionTransaccion = ComisionesBuilder
				.buildComisionTransaccionFromComisionesTransDTOAndComisionesTransaccionesRequestDTO(
						comisionTransaccionVer, comisionesTransDTO, comisionesTransaccionesRequestDTO,
						comisionesTransaccionesRequestDTO.getIdConciliacion(), requestUser);
		comisionTransaccion = comisionTransaccionRepository.save(comisionTransaccion);
		// Guarda las proyecciones
		List<Long> comisionTransaccionProyeccionSet = comisionTransaccionProyeccionRepository
				.findBycomisionTransaccionId(comisionTransaccion.getId());
		comisionTransaccionProyeccionRepository
				.saveAll(ComisionesBuilder.buildComisionTransaccionProyeccionFromComisionTransaccion(
						comisionTransaccionProyeccionSet, comisionTransaccion, comisionesTransDTO));
		// Guarda los datos reales de la comisones y proyecicones
		Set<ComisionTransaccionReal> comisionTransaccionRealSet = comisionTransaccionRealRepository
				.findByComisionTransaccion_Id(comisionTransaccion.getId());
		comisionTransaccionRealRepository.save(ComisionesBuilder.buildComisionTransaccionRealFromComisionTransaccion(
				comisionTransaccionRealSet, comisionTransaccion, comisionesTransDTO));

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
	public Map<String, Object> save(final ComisionSaveDTO comisionSaveDTO, final String requestUser) {
		Map<String, Object> result;
		ComisionSaveResponseDTO comisionSaveResponseDTO = null;
		Optional<MovimientoComision> movimientoComision;
		boolean flagNew = true;
		Optional<Conciliacion> conciliacion = conciliacionRepository.findById(comisionSaveDTO.getFolio());
		if (!conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND);
		if (0 != comisionSaveDTO.getId()) {
			movimientoComision = comisionesRepository.findById(comisionSaveDTO.getId());
			if (null == movimientoComision || !movimientoComision.isPresent())
				throw new ConciliacionException(ConciliacionConstants.COMISION_ID_NOT_FOUND);
			else
				flagNew = false;
		}
		result = new HashMap<>();
		MovimientoComision movimientoComisionRes = comisionesRepository.save(
				ComisionesBuilder.buildMovimientoComisionFromComisionSaveDTO(comisionSaveDTO, requestUser, flagNew));
		comisionSaveResponseDTO = comisionesRepository.findByIdComision(movimientoComisionRes.getId());
		result.put("result", comisionSaveResponseDTO);
		result.put("flag", flagNew);
		return result;
	}

	/**
	 * Elimina movimientos de tipo comision en base a sus ids
	 * 
	 * @param comisionDeleteDTO
	 * @param requestuser
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(final ComisionDeleteDTO comisionDeleteDTO, final String requestUser)
			throws ConciliacionException {
		Optional<Conciliacion> conciliacion = null;
		List<Long> flags = null;
		// Valida que el folio de conciliacion exista
		conciliacion = conciliacionRepository.findById(comisionDeleteDTO.getFolio());
		if (!conciliacion.isPresent())
			throw new ConciliacionException(ConciliacionConstants.CONCILIACION_ID_NOT_FOUND);
		// Valida si se puede eliminar esa comision
		flags = comisionesRepository.verifyById(comisionDeleteDTO.getIdComisiones(), comisionDeleteDTO.getFolio());
		if (null == flags || flags.size() < comisionDeleteDTO.getIdComisiones().size())
			throw new ConciliacionException(ConciliacionConstants.COMISION_CANT_BE_DELETED);
		// Elimina la comision
		comisionesRepository.deleteByIdsAndIdConciliacion(comisionDeleteDTO.getIdComisiones(),
				comisionDeleteDTO.getFolio());
	}

	/**
	 * Construye el objeto Movimiento para pagos y devocuiones
	 * 
	 * @param movimiento
	 * @param operacion
	 * @param transacciones
	 * @param comision
	 */
	private void buildMovimiento(ComisionesTransaccionesOperacionDTO movimiento, final String operacion,
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
	private void buildComisionReal(ComisionesTransRealDTO comisionesTransRealDTO, final BigDecimal sumaComision,
			final BigDecimal sumaIva) {
		comisionesTransRealDTO.setComision(sumaComision);
		comisionesTransRealDTO.setIvaComision(sumaIva);
		comisionesTransRealDTO.setTotalComision(sumaComision.add(sumaIva));
	}

}