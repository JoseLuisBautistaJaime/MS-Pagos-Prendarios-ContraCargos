


package mx.com.nmp.pagos.mimonte.constans;


/**
 * Nombre: CodigoError
 * Descripcion: Mapea los mensajes a enviar en estructura ENUM agrupando tipo de error y tipo de severidad
 * @author: Javier Hernandez Barraza jhernandez@quarksoft.net
 * @version: 0.1
 */

public enum CodigoError {
    NMP_PMIMONTE_0001("Los parametros no son correctos", TipoError.CLIENTE, SeveridadError.BAJA),
    NMP_PMIMONTE_0004("El resultado que desea consultar no existe", TipoError.CLIENTE, SeveridadError.MEDIA),
    NMP_PMIMONTE_0005("No existen registros para el catalogo especificado", TipoError.CLIENTE, SeveridadError.MEDIA),
    NMP_PMIMONTE_0006("Cuerpo de la peticion vacio o ocurrio un error al leerla",
            TipoError.CLIENTE, SeveridadError.MEDIA),
    NMP_PMIMONTE_0007("Los valores de las propiedades del cuerpo de la petición no son correctos",
            TipoError.CLIENTE, SeveridadError.MEDIA),
    NMP_PMIMONTE_0008("Los parametros de la peticion no son correctos",
            TipoError.CLIENTE, SeveridadError.MEDIA),
    NMP_PMIMONTE_9999("Ocurrio un error inesperado, intente mas tarde",
                      TipoError.CLIENTE, SeveridadError.MEDIA);

    /**
     * Descripción del error
     */
    private String descripcion;

    /**
     * Tipo de error
     */
    private TipoError tipoError;

    /**
     * Severidad del error
     */
    private SeveridadError severidadError;

    /**
     * Constructor
     *
     * @param descripcion Descripción del error
     * @param tipoError Tipo de error
     * @param severidadError Severidad del error
     */
    CodigoError(String descripcion, TipoError tipoError, SeveridadError severidadError) {
        this.descripcion = descripcion;
        this.tipoError = tipoError;
        this.severidadError = severidadError;
    }

    /**
     * Recupera el valor de {@code codigo}
     *
     * @return Valor de {@code codigo}
     */
    public String getCodigo() {
        return name();
    }

    /**
     * Recupera el valor de {@code descripcion}
     *
     * @return Valor de {@code descripcion}
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Recupera el valor de {@code tipoError}
     *
     * @return Valor de {@code tipoError}
     */
    public TipoError getTipoError() {
        return tipoError;
    }

    /**
     * Recupera el valor de {@code severidadError}
     *
     * @return Valor de {@code severidadError}
     */
    public SeveridadError getSeveridadError() {
        return severidadError;
    }
}
