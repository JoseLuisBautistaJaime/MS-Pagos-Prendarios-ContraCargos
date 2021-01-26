package mx.com.nmp.pagos.mimonte.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.com.nmp.pagos.mimonte.dao.conciliacion.ConciliacionRepository;
import mx.com.nmp.pagos.mimonte.exception.ConciliacionException;
import mx.com.nmp.pagos.mimonte.helper.FolioConciliacionHelper;
import mx.com.nmp.pagos.mimonte.model.conciliacion.CorresponsalEnum;

/**
 * Implementacion helper para generacion folios
 * @author Quarksoft
 *
 */
@Service("folioConciliacionHelperImpl")
public class FolioConciliacionHelperImpl implements FolioConciliacionHelper {

	@Autowired
	private ConciliacionRepository conciliacionRepository;


	/**
	 * Obtiene el siguiente folio de la sequencia
	 * @param idCorresponsal
	 * @return
	 * @throws ConciliacionException
	 */
	public Long getNextFolio(CorresponsalEnum idCorresponsal) throws ConciliacionException {

		// Genera el siguiente folio
		updateFolio(idCorresponsal);

		// Obtiene el folio
		Long folio = getFolio(idCorresponsal);

		// Se crea la sequencia si no existe e inicializa el folio a 1
		if (folio == null) { 
			folio = crearSequencia(idCorresponsal, 1L);
		}

		return folio;
	}


	/**
	 * Actualiza el siguiente folio
	 * @param idCorresponsal
	 */
	private void updateFolio(CorresponsalEnum idCorresponsal) throws ConciliacionException {
		try {
			conciliacionRepository.updateNextSeqValue(idCorresponsal.name());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al generar el siguiente folio para " + idCorresponsal);
		}
	}


	/**
	 * Obtiene el folio para el corresponsal
	 * @param idCorresponsal
	 * @return
	 */
	private Long getFolio(CorresponsalEnum idCorresponsal) {
		Long folio = null;
		try {
			folio = conciliacionRepository.getSeqValueByName(idCorresponsal.name());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al obtener el siguiente folio para " + idCorresponsal);
		}
		return folio;
	}


	/**
	 * Crea la secuencia para folios para el corresponsal
	 * @param idCorresponsal
	 * @return
	 * @throws ConciliacionException
	 */
	private Long crearSequencia(CorresponsalEnum idCorresponsal, Long folio) throws ConciliacionException {
		try {
			this.conciliacionRepository.crearSeqConciliacion(idCorresponsal.name(), folio);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new ConciliacionException("Error al crear la secuencia para " + idCorresponsal);
		}
		return folio;
	}

}
