package mx.com.nmp.pagos.mimonte.dto.conciliacion;

public interface EstadoCuentaLine {

	public int getIndex();
	public String getLinea();
	public void updateIndex(int index);
	public EstadoCuentaFileLayoutTipoEnum getTipo();

}
