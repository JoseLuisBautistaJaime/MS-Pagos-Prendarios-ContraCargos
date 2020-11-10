package mx.com.nmp.pagos.mimonte.helper;

import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * Se encarga de generar los folios de acuerdo al corresponsale
 * @author Quarksoft
 *
 */
public interface FolioConciliacionHelper {

	/**
	 * Obtiene el siguiente folio de la sequencia
	 * @param idCorresponsal
	 * @return
	 * @throws ConciliacionException
	 */
	public Long getNextFolio(CorresponsalEnum idCorresponsal) throws ConciliacionException;

}
