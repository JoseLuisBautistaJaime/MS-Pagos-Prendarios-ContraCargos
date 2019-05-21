/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.services.conciliacion;

import java.util.List;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.ActualizaionConciliacionRequestDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionDTOList;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConciliacionResponseSaveDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionDTO;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.ConsultaConciliacionRequestDTO;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 6:33:32 PM
 */
public interface ConciliacionService {
	
	/**
	 * Metodo que guarda una conciliación y regresa un folio.
	 * @param conciliacionResponseSaveDTO
	 * @param createdBy
	 * @return
	 */
	public ConciliacionDTO saveConciliacion(ConciliacionResponseSaveDTO conciliacionResponseSaveDTO, String createdBy);
	
	/**
	 * Método que actualiza las comisiones y transacciones de una conciliacion.
	 * @param actualizaionConciliacionRequestDTO
	 * @return
	 */
	public ActualizaionConciliacionRequestDTO actualizaConciliacion(ActualizaionConciliacionRequestDTO actualizaionConciliacionRequestDTO);
	
	/**
	 * Método que regresa una lista de tipo ConsultaConciliacionDTO con la información de la conciliación a partir de un objeto de tipo
	 * ConsultaConciliacionRequestDTO.
	 * @param consultaConciliacionRequestDTO
	 * @return
	 */
	public List<ConsultaConciliacionDTO> consulta(ConsultaConciliacionRequestDTO consultaConciliacionRequestDTO);
	
	/**
	 * Método que regresa la información de la conciliacion a partir del folio.
	 * @param folio
	 * @return
	 */
	public ConciliacionDTOList consultaFolio(Integer folio);

//	public ConciliacionService(){
//
//	}
//
//	public void finalize() throws Throwable {
//
//	}
//
//	/**
//	 * 
//	 * @param idEntidad
//	 * @param idCuenta
//	 * @param usuario
//	 */
//	public ConciliacionDTO altaConciliacion(Long idEntidad, Long idCuenta, String usuario){
//		return null;
//	}
//
//	/**
//	 * 
//	 * @param idConciliacion
//	 * @param usuario
//	 */
//	public void generarConciliacion(Long idConciliacion, String usuario){
//
//	}
//
//	/**
//	 * 
//	 * @param idConcilacion
//	 */
//	public ConciliacionDTO getById(Long idConcilacion){
//		return null;
//	}
//
//	/**
//	 * 
//	 * @param consulta
//	 * @param page
//	 * @param pageSize
//	 */
//	public List<ConsultaConciliacionDTO> search(ConsultaConciliacionRequestDTO consulta, Integer page, Integer pageSize){
//		return null;
//	}
//
//	/**
//	 * 
//	 * @param criterios
//	 */
//	public Integer count(ConsultaConciliacionRequestDTO criterios){
//		return 0;
//	}
//
//	/**
//	 * 
//	 * @param idConciliacion
//	 * @param usuario
//	 */
//	public void enviarConciliacion(Long idConciliacion, String usuario){
//
//	}

}