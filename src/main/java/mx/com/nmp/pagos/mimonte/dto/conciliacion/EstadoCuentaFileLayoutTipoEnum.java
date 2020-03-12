package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import mx.com.nmp.pagos.mimonte.constans.CodigoError;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;

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

		public void validar(EstadoCuentaLine line) {
			//
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

		public void validar(EstadoCuentaLine line) {
			//
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

		public void validar(EstadoCuentaLine line) {
			//
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

		public void validar(EstadoCuentaLine line) {
			//
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

		public void validar(EstadoCuentaLine line) {
			validarLongitud(line, 80);
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

		public void validar(EstadoCuentaLine line) {
			validarLongitud(line, 80);
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

		public void validar(EstadoCuentaLine line) {
			validarLongitud(line, 80);
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

		public void validar(EstadoCuentaLine line) {
			validarLongitud(line, 77);
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

		public void validar(EstadoCuentaLine line) {
			validarLongitud(line, 76);
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

		public void validar(EstadoCuentaLine line) {
			//
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

		public void validar(EstadoCuentaLine line) {
			//
		}
	};

	public abstract boolean startWith(String line);

	public abstract <T extends EstadoCuentaLine> T getInstance(String line);

	public abstract EstadoCuentaImplementacionEnum getImplementacion();

	public abstract void validar(EstadoCuentaLine line);

	private static void validarLongitud(EstadoCuentaLine line, int longitud) {
		boolean valido = (line != null && line.getLinea() != null && line.getLinea().length() == longitud);
		if (!valido) {
			throw new ConciliacionException("Linea " + line.getIndex() + " con longitud invalida: "
					+ line.getLinea().length() + ", longitud esperada: " + longitud,
					CodigoError.NMP_PMIMONTE_BUSINESS_067);
		}
	}

}
