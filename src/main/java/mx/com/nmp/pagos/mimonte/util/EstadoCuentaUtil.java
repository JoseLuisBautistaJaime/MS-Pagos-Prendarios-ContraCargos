package mx.com.nmp.pagos.mimonte.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class EstadoCuentaUtil {

	public static String substring(String string, int start, int lenght) {
		String substring = "";
		if (string != null && string.length() >= (start + lenght)) {
			substring = string.substring(start, start + lenght);
		}
		return substring;
	}

	/**
	 * Convierte una cadena numerica (sin separador decimal) en decimal usando las ultimas posiciones como decimal 
	 * @param importeCeros
	 * @param posiciones Numero de posiciones decimales a considerar
	 * @return
	 */
	public static BigDecimal getDecimalFromString(String importeCeros, int posiciones) {
		BigDecimal importe = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
		if (importeCeros != null & importeCeros.length() >= posiciones) {
			String parteEntera = importeCeros.substring(0, importeCeros.length() - posiciones);
			String parteDecimal = importeCeros.substring(importeCeros.length() - posiciones);
			importe = new BigDecimal(parteEntera + "." + parteDecimal).setScale(2, RoundingMode.HALF_UP);
		}
		return importe;
	}

}
