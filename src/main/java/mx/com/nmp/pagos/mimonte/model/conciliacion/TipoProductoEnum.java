package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @name TipoProductoEnum
 *
 * @description Enum que contiene los tipos de productos
 * @author Carlos Soto jsoto@quarksoft.net
 * @version 1.0
 * @creationDate 30/12/2020 15:03 hrs.
 */
public enum TipoProductoEnum {
    CLASICO (145, "CLASICO"),
    PAGOSLIBRES (146, "PAGOS LIBRES");
    /*
        145 - CLASICO
        146 - PAGOS LIBRES
     */
    private Integer idTipoProducto;
    private String tipoProducto;

    private TipoProductoEnum(Integer idTipoProducto, String tipoProducto) {
        this.idTipoProducto = idTipoProducto;
        this.tipoProducto = tipoProducto;
    }

    public Integer getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(Integer idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }
}
