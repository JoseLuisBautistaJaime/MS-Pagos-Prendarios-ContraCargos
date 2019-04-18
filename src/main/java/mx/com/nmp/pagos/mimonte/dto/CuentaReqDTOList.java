/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

/**
 * @name CuentaReqDTOList
 * @description Clase que encapsula la informacion perteneciente a una lista de
 *              objetos de tipo CuentaReqDTO
 *
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 18/04/2019 00:25 hrs.
 * @version 0.1
 */
public class CuentaReqDTOList {

	private CuentaReqDTO cuentaReqDTO;
	private List<AfiliacionReqDTO> AfiliacionReqDTOList;

	public CuentaReqDTOList() {
		super();
	}

	public CuentaReqDTOList(CuentaReqDTO cuentaReqDTO, List<AfiliacionReqDTO> afiliacionReqDTOList) {
		super();
		this.cuentaReqDTO = cuentaReqDTO;
		AfiliacionReqDTOList = afiliacionReqDTOList;
	}

	public CuentaReqDTO getCuentaReqDTO() {
		return cuentaReqDTO;
	}

	public void setCuentaReqDTO(CuentaReqDTO cuentaReqDTO) {
		this.cuentaReqDTO = cuentaReqDTO;
	}

	public List<AfiliacionReqDTO> getAfiliacionReqDTOList() {
		return AfiliacionReqDTOList;
	}

	public void setAfiliacionReqDTOList(List<AfiliacionReqDTO> afiliacionReqDTOList) {
		AfiliacionReqDTOList = afiliacionReqDTOList;
	}

}
