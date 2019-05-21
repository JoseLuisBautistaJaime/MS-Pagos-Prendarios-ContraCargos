/**
 * Proyecto:        NMP - Microservicio de Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import java.util.List;

/**
 * Referencia al servicio web de Estado Cuenta
 *
 * @author jgalvez
 */
public interface EstadoCuentaAPI {

    /**
     * Consulta el estado de cuenta para un dia
     * @param fecha
     * @return Lista de estado de cuentas
     */
	public List<String> consulta(String ruta, String archivo);

}
