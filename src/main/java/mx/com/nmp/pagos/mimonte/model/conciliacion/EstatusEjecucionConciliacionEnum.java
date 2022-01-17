package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name EstatusEjecucionConciliacionEnum
 *
 * @description Enum que contiene los estados de ejecución del proceso de conciliación
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @creationDate 03/01/2022 09:43 hrs.
 */
public enum EstatusEjecucionConciliacionEnum {
    CREADA (1, "CREADA"),
    CARGA_MOVIMIENTOS_MIDAS (2, "CARGA MOVIMIENTOS MIDAS"),
    CARGA_MOVIMIENTOS_PROVEEDOR (3, "CARGA MOVIMIENTOS PROVEEDOR"),
    CONCILIACION_MIDAS_PROVEEDOR (4, "CONCILIACION MOVIMIENTOS MIDAS Y PROVEEDOR"),
    CARGA_MOVIMIENTOS_ESTADO_CUENTA (5, "CARGA MOVIMIENTOS ESTADO DE CUENTA"),
    CONCILIACION_MIDAS_PROVEEDOR_ESTADOCUENTA (6, "CONCILIACION MOVIMIENTOS MIDAS, PROVEEDOR Y ESTADO DE CUENTA"),
    GENERAR_LAYOUTS (7, "GENERAR LAYAOUTS"),
    ENVIAR_LAYOUTS (8, "ENVIAR LAYAOUTS"),;

    private Integer idEstadoEjecucion;
    private String estadoEjecucion;

    EstatusEjecucionConciliacionEnum(Integer idEstadoEjecucion, String estadoEjecucion) {
        this.idEstadoEjecucion = idEstadoEjecucion;
        this.estadoEjecucion = estadoEjecucion;
    }

    public Integer getIdEstadoEjecucion() {
        return idEstadoEjecucion;
    }

    public void setIdEstadoEjecucion(Integer idEstadoEjecucion) {
        this.idEstadoEjecucion = idEstadoEjecucion;
    }

    public String getEstadoEjecucion() {
        return estadoEjecucion;
    }

    public void setEstadoEjecucion(String estadoEjecucion) {
        this.estadoEjecucion = estadoEjecucion;
    }

}
