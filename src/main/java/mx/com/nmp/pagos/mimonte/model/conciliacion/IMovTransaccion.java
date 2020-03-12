package mx.com.nmp.pagos.mimonte.model.conciliacion;

/**
 * Interface que representa un movimiento en el sistema
 * @author Quarksoft
 */
public interface IMovTransaccion {
	public MovimientoMidas getMovimientoMidas();
	public TipoMovimientoEnum getTipoMovimientoBD();
}