package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name ProcesoEnum
 *
 * @description Enum que contiene los procesos automatizados correspondientes a la ejecución de los proceso de pre-conciliación y conciliación
 * @author Juan Manuel Reveles jmreveles@quarksoft.net
 * @version 1.0
 * @creationDate 03/01/2022 09:03 hrs.
 */
public enum ProcesoEnum {
    PRE_CONCILIACION (1, "PRE-CONCILIACIÓN"),
    CONCILIACION_ETAPA_1_DN (2, "CONCILIACIÓN ETAPA 1 (DÍA NORMAL)"),
    CONCILIACION_ETAPA_1_FS (3, "CONCILIACIÓN ETAPA 1 (FIN DE SEMANA)"),
    CONCILIACION_ETAPA_2_DN (4, "CONCILIACIÓN ETAPA 2 (DÍA NORMAL)"),
    CONCILIACION_ETAPA_2_FS (5, "CONCILIACIÓN ETAPA 2 (FIN DE SEMANA)"),
    CONCILIACION_ETAPA_3 (6, "CONCILIACIÓN ETAPA 3"),;

    private Integer idProceso;
    private String proceso;

    ProcesoEnum(Integer idProceso, String proceso) {
        this.idProceso = idProceso;
        this.proceso = proceso;
    }

    public Integer getIdProceso() {
        return idProceso;
    }

    public void setIdProceso(Integer idProceso) {
        this.idProceso = idProceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

}
