package mx.com.nmp.pagos.mimonte.builder.conciliacion;

import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaFileLayoutTipoEnum;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaImplementacionEnum;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.EstadoCuentaLine;

public class EstadoCuentaLineBuilder {

	private String line;
	private EstadoCuentaImplementacionEnum implementacion;

	
	public EstadoCuentaLineBuilder(String line, EstadoCuentaImplementacionEnum implementacion) {
		this.line = line;
		this.implementacion = implementacion;
	}

	public EstadoCuentaLine buildLine() {
		EstadoCuentaLine estadoCuentaLine = null;
		if (this.line != null && implementacion != null) {
			for (EstadoCuentaFileLayoutTipoEnum fileLayout : EstadoCuentaFileLayoutTipoEnum.values()) {
				if (fileLayout.getImplementacion() == implementacion) {
					if (fileLayout.startWith(this.line)) {
						estadoCuentaLine = fileLayout.getInstance(this.line);
						break;
					}
				}
			}
		}
		return estadoCuentaLine;
	}

}
