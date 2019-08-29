package mx.com.nmp.pagos.mimonte.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mx.com.nmp.pagos.mimonte.model.CodigoEstadoCuenta;

/**
 * Entidad que encapsula las operaciones basicas sobre la colleccion EntidadEstadoCuenta
 * @author JGALVEZ
 */
public class CodigosEdoCuentaMap {

	private Map<Long, List<String>> codigosMap;

	public CodigosEdoCuentaMap(Collection<CodigoEstadoCuenta> codigos) {
		codigosMap = new LinkedHashMap<Long, List<String>>();
		if (codigos != null) {
			for (CodigoEstadoCuenta codigo : codigos) {
				List<String> codigosList = codigosMap.get(codigo.getCategoria().getId());
				if (codigosList == null) {
					codigosList = new ArrayList<String>();
					codigosMap.put(codigo.getCategoria().getId(), codigosList);
				}
				codigosList.add(codigo.getCodigo());
			}
		}
	}

	public List<String> getByCategoria(Long idCategoria) {
		return codigosMap.get(idCategoria);
	}

	public List<String> getAll() {
		List<String> codigos = new ArrayList<String>();
		for (Map.Entry<Long, List<String>> entry : codigosMap.entrySet()) {
			codigos.addAll(entry.getValue());
		}
		return codigos;
	}

	public boolean containsClave(String clave) {
		List<String> codigos = getAll();
		return codigos != null && clave != null && codigos.contains(clave);
	}

	public boolean containsClave(List<String> codigos, String clave) {
		return codigos != null && clave != null && codigos.contains(clave);
	}

	public boolean containsClaveInCategoria(Long idCategoria, String clave) {
		List<String> codigos = getByCategoria(idCategoria);
		return codigos != null && clave != null && codigos.contains(clave);
	}

}
