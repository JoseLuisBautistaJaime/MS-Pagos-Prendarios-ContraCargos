package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name EstatusEjecucionPreconciliacionEnum
 *
 * @description Enum que contiene los estados de ejecución del proceso de pre-conciliación
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @creationDate 16/12/2021 09:03 hrs.
 */
public enum EstatusEjecucionPreconciliacionEnum {
    SOLICITUD (1, "SOLICITUD"),
    DESCARGACORRECTA (2, "DESCARGA CORRECTA"),
    DESCARGAINCORRECTA (3, "DESCARGA INCORRECTA"),;

    private Integer idEstadoEjecucion;
    private String estadoEjecucion;

    EstatusEjecucionPreconciliacionEnum(Integer idEstadoEjecucion, String estadoEjecucion) {
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
