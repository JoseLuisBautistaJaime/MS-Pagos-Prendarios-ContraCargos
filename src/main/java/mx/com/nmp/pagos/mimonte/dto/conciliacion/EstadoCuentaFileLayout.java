package mx.com.nmp.pagos.mimonte.dto.conciliacion;

import java.util.List;

public interface EstadoCuentaFileLayout {

	public int getTotal();
	
	public List<EstadoCuentaLine> getRegistros();

	public void addRegistro(EstadoCuentaLine estadoCuentaLine);

}
