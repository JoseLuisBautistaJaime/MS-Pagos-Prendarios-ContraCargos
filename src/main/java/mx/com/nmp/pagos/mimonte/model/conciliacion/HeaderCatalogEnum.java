package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 15-07-2019
 */
public enum HeaderCatalogEnum {
	PAGOS(1L),
	COMISIONES_MOV(2L),
	COMISIONES_GENERALES(3L),
	DEVOLUCIONES(4L);

    private Long id;

    HeaderCatalogEnum(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
