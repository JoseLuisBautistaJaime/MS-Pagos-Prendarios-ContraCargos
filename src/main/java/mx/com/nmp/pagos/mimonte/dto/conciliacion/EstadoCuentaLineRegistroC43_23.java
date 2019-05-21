package mx.com.nmp.pagos.mimonte.dto.conciliacion;

public class EstadoCuentaLineRegistroC43_23 implements EstadoCuentaLine {

	private int index;
	private String linea;
	private EstadoCuentaFileLayoutTipoEnum tipo;

	public EstadoCuentaLineRegistroC43_23(String linea) {
		this.linea = linea;
		this.index = 0;
		this.tipo = EstadoCuentaFileLayoutTipoEnum.C43_23;
	}

	public EstadoCuentaLineRegistroC43_23(String linea, int index) {
		this.linea = linea;
		this.index = index;
		this.tipo = EstadoCuentaFileLayoutTipoEnum.C43_23;
	}

	public void updateIndex(int index) {
		this.index = index;
	}


	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public EstadoCuentaFileLayoutTipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(EstadoCuentaFileLayoutTipoEnum tipo) {
		this.tipo = tipo;
	}

}
