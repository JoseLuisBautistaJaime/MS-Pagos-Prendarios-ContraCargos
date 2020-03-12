/**
 * Proyecto:        NMP - Microservicio de Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import java.util.Date;
import java.util.List;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;

/**
 * Referencia al servicio web de OpenPay
 *
 * @author jgalvez
 */
public interface OpenPayAPI {

    /**
     * Consulta el listado de movimientos de un rango de fechas especifico (un dia)
     * @param fechaInicio
     * @param fechaFin
     * @return Lista de MovimientoProveedorDTO que contiene la informcion de la respuesta.
     */
    List<MovimientoProveedorDTO> consulta(Date fechaInicio, Date fechaFin);

}
