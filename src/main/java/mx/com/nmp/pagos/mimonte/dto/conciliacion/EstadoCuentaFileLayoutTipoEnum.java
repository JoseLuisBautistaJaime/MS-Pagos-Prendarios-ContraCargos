package mx.com.nmp.pagos.mimonte.dto.conciliacion;

@SuppressWarnings("unchecked")
public enum EstadoCuentaFileLayoutTipoEnum {
	DIA_C43_11 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("11");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	},
	DIA_C43_22 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("22");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	},
	DIA_CUENTA_CONVENIO {
		public boolean startWith(String line) {
			return false;
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	},
	CIE_DIARIO {
		public boolean startWith(String line) {
			return false;
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	},
	C43_11 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("11");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return (T) new EstadoCuentaLineRegistroC43_11(line);
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.CUADERNO_43;
		}
		public boolean valido(String line) {
			return line != null && line.length() == 80;
		}
	},
	C43_22 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("22");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return (T) new EstadoCuentaLineRegistroC43_22(line);
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.CUADERNO_43;
		}
		public boolean valido(String line) {
			return line != null && line.length() == 80;
		}
	},
	C43_23 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("23");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return (T) new EstadoCuentaLineRegistroC43_23(line);
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.CUADERNO_43;
		}
		public boolean valido(String line) {
			return line != null && line.length() == 80;
		}
	},
	C43_32 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("32");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return (T) new EstadoCuentaLineRegistroC43_32(line);
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.CUADERNO_43;
		}
		public boolean valido(String line) {
			return line != null && line.length() == 80;
		}
	},
	C43_33 {
		public boolean startWith(String line) {
			return line != null && line.startsWith("33");
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return (T) new EstadoCuentaLineRegistroC43_33(line);
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.CUADERNO_43;
		}
		public boolean valido(String line) {
			return line != null && line.length() == 77;
		}
	},
	CASH {
		public boolean startWith(String line) {
			return false;
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	},
	HISTORICO {
		public boolean startWith(String line) {
			return false;
		}
		public <T extends EstadoCuentaLine> T getInstance(String line) {
			return null;
		}
		public EstadoCuentaImplementacionEnum getImplementacion() {
			return EstadoCuentaImplementacionEnum.OTRO;
		}
		public boolean valido(String line) {
			return false;
		}
	};


	public abstract boolean startWith(String line);
	
	public abstract <T extends EstadoCuentaLine> T getInstance(String line);

	public abstract EstadoCuentaImplementacionEnum getImplementacion();

	public abstract boolean valido(String line);

}
