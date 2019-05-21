package mx.com.nmp.pagos.mimonte.dto;

import java.util.List;

import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaCabecera;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaTotales;
import mx.com.nmp.pagos.mimonte.model.conciliacion.EstadoCuentaTotalesAdicional;
import mx.com.nmp.pagos.mimonte.model.conciliacion.MovimientoEstadoCuenta;

public class EstadoCuentaWraper  {

	public EstadoCuentaCabecera cabecera;
	public EstadoCuentaTotales totales;
	public EstadoCuentaTotalesAdicional totalesAdicional;
	public List<MovimientoEstadoCuenta> movimientos;

}
