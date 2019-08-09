package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * @author Quarksoft
 * @version 1.0
 * @created 31-Mar-2019 5:58:02 PM
 */
public enum TipoLayoutEnum {
	PAGOS {
		public LineaCatalogEnum getLineaCatalog() {
			return LineaCatalogEnum.PAGOS;
		}
		public HeaderCatalogEnum getHeaderCatalog() {
			return HeaderCatalogEnum.PAGOS;
		}
	},
	COMISIONES_MOV {
		public LineaCatalogEnum getLineaCatalog() {
			return LineaCatalogEnum.COMISIONES_MOV;
		}
		public HeaderCatalogEnum getHeaderCatalog() {
			return HeaderCatalogEnum.COMISIONES_MOV;
		}
	},
	COMISIONES_GENERALES {
		public LineaCatalogEnum getLineaCatalog() {
			return LineaCatalogEnum.COMISIONES_GENERALES;
		}
		public HeaderCatalogEnum getHeaderCatalog() {
			return HeaderCatalogEnum.COMISIONES_GENERALES;
		}
	},
	DEVOLUCIONES {
		public LineaCatalogEnum getLineaCatalog() {
			return LineaCatalogEnum.DEVOLUCIONES;
		}
		public HeaderCatalogEnum getHeaderCatalog() {
			return HeaderCatalogEnum.DEVOLUCIONES;
		}
	};

	public abstract LineaCatalogEnum getLineaCatalog();
	public abstract HeaderCatalogEnum getHeaderCatalog();

}