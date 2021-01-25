package mx.com.nmp.pagos.mimonte.model.conciliacion;

import java.util.Arrays;
import java.util.List;

/**
 * @name TipoOperacionEnum
 *
 * @description Enum que contiene los tipos de Operaciones
 * @author Carlos Soto jsoto@quarksoft.net
 * @version 1.0
 * @creationDate 30/12/2020 15:03 hrs.
 */
public enum TipoOperacionEnum {
    VENTACONBILLETE (3, "Venta con Billete"),
    COBRODESEMPENO (6, "Cobro Desempeño"),
    COBROREFRENDO (8, "Cobro Refrendo"),
    ABONOPAGOSLIBRE (116, "Abono-Pagos Libres"),
    COBRODESEMPENOLINEA (148, "Cobro Desempeño En Línea"),
    COBRODESEMPENOEXTEMPORANEO (191, "Cobro Desempeño Extemporáneo"),
    COBROREFRENDOEXTEMPORANEO (198, "Cobro Refrendo Extemporáneo"),;
    /*
        3	Venta con Billete
        6	Cobro Desempeño
        8	Cobro Refrendo
        116	Abono-Pagos Libres
        148	Cobro Desempeño En Línea
        191	Cobro Desempeño Extemporáneo
        198	Cobro Refrendo Extemporáneo
     */

    private Integer idTipoOperacion;
    private String tipoOperacion;

    private TipoOperacionEnum (Integer idTipoOperacion, String tipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
        this.tipoOperacion = tipoOperacion;
    }

    public Integer getIdTipoOperacion() {
        return idTipoOperacion;
    }

    public void setIdTipoOperacion(Integer idTipoOperacion) {
        this.idTipoOperacion = idTipoOperacion;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }


}
