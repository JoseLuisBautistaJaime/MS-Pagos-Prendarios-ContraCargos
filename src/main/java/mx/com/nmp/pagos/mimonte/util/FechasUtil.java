/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibm.icu.util.Calendar;

/**
 * @name FechasUtil
 * @description Interfaz que define metodos utiles para realizar operaciones
 *              sobre fechas
 * @author Ismael Flores iaguilar@quarksoft.net
 * @creationDate 29/07/2019 20:23 hrs.
 * @version 0.1
 */
public interface FechasUtil {

	/**
	 * Regresa un mapa con los valores de fecha inicial y final con los ajustes
	 * realizados de acuerdo a los valores recibidos previamente
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Map<String, Date> adjustDates(Date startDate, Date endDate) {
		final Map<String, Date> resultMap = new HashMap<>();
		final String start = "startDate";
		final String end = "endDate";
		resultMap.put(start, null);
		resultMap.put(end, null);
		if (null == startDate && null != endDate) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1975);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			resultMap.put(start, cal.getTime());
			resultMap.put(end, endDate);
		}
		if (null != startDate && null == endDate) {
			Calendar cal = Calendar.getInstance();
			resultMap.put(start, startDate);
			resultMap.put(end, cal.getTime());
		}
		if (null != startDate && null != endDate) {
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();
			ini.setTime(startDate);
			fin.setTime(endDate);
			ini.set(Calendar.HOUR_OF_DAY, 0);
			ini.set(Calendar.MINUTE, 0);
			ini.set(Calendar.SECOND, 0);
			ini.set(Calendar.MILLISECOND, 0);
			fin.set(Calendar.HOUR_OF_DAY, 23);
			fin.set(Calendar.MINUTE, 59);
			fin.set(Calendar.SECOND, 59);
			fin.set(Calendar.MILLISECOND, 59);
			resultMap.put(start, ini.getTime());
			resultMap.put(end, fin.getTime());
		}
		return resultMap;
	}

}
