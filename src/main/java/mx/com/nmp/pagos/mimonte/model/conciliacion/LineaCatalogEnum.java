package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 17-07-2019
 */
public enum LineaCatalogEnum {

	PAGOS(1L),
	COMISIONES_MOV(2L),
	COMISIONES_GENERALES(3L),
	DEVOLUCIONES(4L);

    private Long id;

    LineaCatalogEnum(Long id) {
        this.id = id;
    }

    public Long id() {
        return id;
    }
}
