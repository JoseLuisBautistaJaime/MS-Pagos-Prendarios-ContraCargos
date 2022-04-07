/*
 * Proyecto:        NMP - HABILITAR COBRANZA 24/7 -  CONCILIACION AUTOMATICA.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.GestionConciliacionResponseDTO;

/**
 * Referencia al servicio que gestiona la creación del proceso de conciliación.
 *
 * @author jmreveles
 */
public interface GestionConciliacionBroker {

    /**
     * Ejecuta el creación del proceso de conciliación.
     * @param idCuenta
     * @param idEntidad
     * @param idCorresponsal
     * @return
     */
	public GestionConciliacionResponseDTO crearConciliacion(Long idCuenta, Long idEntidad,String idCorresponsal) ;

}
