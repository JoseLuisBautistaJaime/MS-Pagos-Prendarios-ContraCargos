package mx.com.nmp.pagos.mimonte.aspects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import mx.com.nmp.pagos.mimonte.helper.ConciliacionHelper;

/**
 * Bean que contiene un mapa de asociacion id y folio conciliacion en sesion,
 * para evitar concurrencia a BD y se limita a 100 valores disponibles
 * 
 * @author user
 *
 */
@Component
@Scope("application")
public class ObjectsInSession {

	/**
	 * Mapa de valores id / folio
	 */
	private Map<Long, Long> foliosConciliacionMap = null;

	/**
	 * Hace la consulta de folio en base a id en la BD
	 */
	@Autowired
	private ConciliacionHelper conciliacionHelper;

	public ObjectsInSession() {
		super();
		foliosConciliacionMap = new HashMap<>();
	}

	/**
	 * Obtiene el folio de una conciliacion en base a su id del mapa, siu no esta lo
	 * consulta en bd y lo pone en el mapa, al mismo tiempo depura el mapa si ya ha
	 * alcanzado los 100 valores
	 * 
	 * @param idConciliacion
	 * @return
	 */
	public Long getFolioByIdConciliacion(Long idConciliacion) {

		if (null != idConciliacion) {
			Long folioConciliacion;
			int x = foliosConciliacionMap.size() - 99;

			if (x > 0) {
				for (Iterator<Long> iterator = foliosConciliacionMap.keySet().iterator(); iterator.hasNext();) {
					iterator.remove();
					x--;
					if (x <= 0) {
						break;
					}
				}
			}
			if (foliosConciliacionMap.containsKey(idConciliacion)) {
				return foliosConciliacionMap.get(idConciliacion);
			} else {
				folioConciliacion = conciliacionHelper.getFolioConciliacionById(idConciliacion);
				foliosConciliacionMap.put(idConciliacion, folioConciliacion);
				return folioConciliacion;
			}
		} else {
			return null;
		}

	}

}
