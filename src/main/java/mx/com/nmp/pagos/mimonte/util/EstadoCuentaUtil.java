package mx.com.nmp.pagos.mimonte.util;

public class EstadoCuentaUtil {

	public static String substring(String string, int start, int lenght) {
		String substring = "";
		if (string != null && string.length() >= (start + lenght)) {
			substring = string.substring(start, start + lenght);
		}
		return substring;
	}

}
