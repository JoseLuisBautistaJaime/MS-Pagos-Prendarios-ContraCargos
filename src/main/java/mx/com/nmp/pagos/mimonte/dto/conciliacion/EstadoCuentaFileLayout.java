/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

/**
 * @name EstadoCuentaFileLayout
 * 
 * @description Interface que representa un tipo de layout del estado de cuenta
 * @author Quarksoft
 * @version 1.0
 */
public interface EstadoCuentaFileLayout {

	/**
	 * Obtiene el total de registros
	 * @return
	 */
	public int getTotal();
	
	/**
	 * Obtiene las lineas
	 * @return
	 */
	public List<EstadoCuentaLine> getRegistros();

	/**
	 * Agrega un registro a la lista
	 * @param estadoCuentaLine
	 */
	public void addRegistro(EstadoCuentaLine estadoCuentaLine);

}
