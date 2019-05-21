/**
 * Proyecto:        NMP - Microservicio Mi Monte Conciliacion
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.conector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import mx.com.nmp.pagos.mimonte.conector.ws.OpenPayService;
import mx.com.nmp.pagos.mimonte.dto.conciliacion.MovimientoProveedorDTO;
import java.util.Date;
import java.util.List;

/**
 * Referencia al servicio web Open Pay
 *
 * @author jgalvez
 */
@Component
public class OpenPayProxy implements OpenPayAPI {

	private Logger LOGGER = LoggerFactory.getLogger(OpenPayProxy.class);

	/**
	 * Referencia al conector hacia el Servicio Web OpenPay
	 */
	@Inject
	private OpenPayConector openPayConector;


	// METODOS

	/**
	 * {@inheritDoc}
	 */
	@Cacheable("OpenPayProxy.consulta")
	@Override
	public List<MovimientoProveedorDTO> consulta(Date fechaInicio, Date fechaFin) {
		LOGGER.info(">> consulta({}, {})", fechaInicio, fechaFin);

		OpenPayService openPayService = openPayConector.getReferenciaWsOpenPay();
		
		// openPayService.xxxx();

		// TODO: Mapear resultado
		List<MovimientoProveedorDTO> movimientos = null;

		return movimientos;
	}

}
