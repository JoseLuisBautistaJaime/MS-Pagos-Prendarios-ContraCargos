/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarIdPSRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizarSubEstatusRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaActividadesRequest;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovTransitoDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ResumenConciliacionResponseDTO;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.Conciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstatusConciliacion;
import mx.com.nmp.pagos.mimonte.model.conciliacion.SubEstatusConciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:32 PM
 */
@Service("conciliacionService")
public interface ConciliacionService {

	/**
	 * Metodo que guarda una conciliación y regresa un folio.
	 * 
	 * @param conciliacionResponseSaveDTO
	 * @param createdBy
	 * @return
	 */
	public ConciliacionDTO saveConciliacion(ConciliacionResponseSaveDTO conciliacionResponseSaveDTO, String createdBy);

	/**
	 * Metodo que actualiza las comisiones y transacciones de una conciliacion.
	 * 
	 * @param actualizaionConciliacionRequestDTO
	 * @param requestUser
	 * @return
	 */
	public ActualizaionConciliacionRequestDTO actualizaConciliacion(
			ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO, final String requestUser);

	/**
	 * Método que regresa una lista de tipo ConsultaConciliacionDTO con la
	 * información de la conciliación a partir de un objeto de tipo
	 * ConsultaConciliacionRequestDTO.
	 * 
	 * @param consultaConciliacionRequestDTO
	 * @return
	 */
	public List<ConsultaConciliacionDTO> consulta(ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO);

	/**
	 * Método que regresa la información de la conciliacion a partir del folio.
	 * 
	 * @param folio
	 * @return
	 */
	public ConciliacionDTOList consultaFolio(Long folio);

	/**
	 * Servicio que permite enviar la conciliación.
	 * 
	 * @param idConciliacion
	 * @param usuario
	 */
	public void enviarConciliacion(Long idConciliacion, String usuario);

	/**
	 * sevicio callBack que será usuado para actualizar el id del registro de las
	 * pantallas que será devuelto por PeopleSoft.
	 * 
	 * @param idsPS
	 * @param usuario
	 */
	public void actualizarPS(ActualizarIdPSRequest actualizarIdPSRequest, String usuario);

	/**
	 * Servicio que sirve para actualizar el subestatus del proceso de conciliacion
	 * para conocer el estatus de la consulta de los reportes.
	 * 
	 * @param actualizarSubEstatusRequestDTO
	 * @param usuario
	 */
	public void actualizaSubEstatusConciliacion(ActualizarSubEstatusRequestDTO actualizarSubEstatusRequestDTO,
			String usuario);

	/**
	 * Realiza a actualizacion de el subesttaus de una conciliaicon por folio
	 * 
	 * @param folio
	 * @param subEstatus
	 * @param usuario
	 * @param fecha
	 * @param estatusConciliacion
	 * @param descripcion
	 */
	public void actualizaSubEstatusConciliacionNT(Long folio, SubEstatusConciliacion subEstatus, final String usuario,
			Date fecha, EstatusConciliacion estatusConciliacion, String descripcion);

	/**
	 * Servicio que consulta el resumen de conciliaciones realizadas.
	 * @param resumenConciliacionRequestDTO
	 * @return
	 */
	public ResumenConciliacionResponseDTO resumenConciliaciones(ResumenConciliacionRequestDTO resumenConciliacionRequestDTO);

	/**
	 * Realiza la consulta del log de las ultimas actividades realizadas en el
	 * sistema como son: altas reportes, movimientos realizados durante la
	 * conciliación, cambio de estatus.
	 * 
	 * @param consultaActividadesRequest
	 */
	public List<ConsultaActividadDTO> consultaActividades(ConsultaActividadesRequest consultaActividadesRequest);

	/**
	 * Realiza la consulta de los movimientos en transito de la conciliacion (con
	 * error).
	 * 
	 * @param folio
	 * @return
	 */
	public List<MovTransitoDTO> consultaMovimientosTransito(Long folio);

	/**
	 * 
	 * @param idConcilacion
	 */
	public Conciliacion getById(Long idConcilacion);

	/**
	 * Servicio que permite generar la conciliación usando los movimientos de
	 * procesos nocturnos, del proveedor transaccional (open pay) y de estado de
	 * cuenta de acuerdo a su disponibilidad. Genera la sección global con el
	 * resumen de la conciliación, asi como la extracción de movimientos en tránsito
	 * y comisiones.
	 * 
	 * @param folio
	 * @param lastModifiedBy
	 * @throws ConciliacionException
	 */
	public void generarConciliacion(Long folio, String lastModifiedBy) throws ConciliacionException;

	/**
	 * Valida que la conciliaicon con el folio especificado ya haya pasado por el proceso de merge
	 * @param folio
	 * @return
	 */
	public Boolean validateConciliacionMerge(Long folio);
	
}