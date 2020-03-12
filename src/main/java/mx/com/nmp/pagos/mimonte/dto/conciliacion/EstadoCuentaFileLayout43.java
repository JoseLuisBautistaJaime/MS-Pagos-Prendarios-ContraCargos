package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.ArrayList;
import java.util.List;

public class EstadoCuentaFileLayout43 implements EstadoCuentaFileLayout {

	private int total;
	private List<EstadoCuentaLine> registros;


	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<EstadoCuentaLine> getRegistros() {
		return registros;
	}

	public void setRegistros(List<EstadoCuentaLine> registros) {
		this.registros = registros;
	}

	public void addRegistro(EstadoCuentaLine estadoCuentaLine) {
		if (this.registros == null || this.registros.size() == 0) {
			this.total = 0;
			this.registros = new ArrayList<EstadoCuentaLine>();
		}
		this.total ++;
		estadoCuentaLine.updateIndex(total);
		this.registros.add(estadoCuentaLine);
	}
	
}
