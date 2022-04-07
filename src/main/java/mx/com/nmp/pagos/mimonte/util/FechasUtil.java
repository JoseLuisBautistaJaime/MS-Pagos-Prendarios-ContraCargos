/*
 * Proyecto:        NMP - MI MONTE FASE 2 - CONCILIACION.
 * Quarksoft S.A.P.I. de C.V. – Todos los derechos reservados. Para uso exclusivo de Nacional Monte de Piedad.
 */
package mx.com.nmp.pagos.mimonte.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
		// Objetos requeridos
		final Map<String, Date> resultMap = new HashMap<>();
		final String start = "startDate";
		final String end = "endDate";
		resultMap.put(start, null);
		resultMap.put(end, null);

		// LA FECHA INICIAL ES NULA Y LA FINAL NO
		if (null == startDate && null != endDate) {
			// Se crean las instancias de fecha actual (para fecha inicio y fin)
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();

			// Se setea el valor de fecha final recibido ya que no es nulo
			fin.setTime(endDate);

			// Se realiza el ajuste para la fecha inicial y final
			adjustTimeAndDate(ini, true, false);
			adjustTimeAndDate(fin, false, false);

			// Se setea el resultado en el mapa de respuesta
			resultMap.put(start, ini.getTime());
			resultMap.put(end, fin.getTime());
		}

		// LA FECHA INICIAL NO ES NULA Y LA FINAL SI LO ES
		else if (null != startDate && null == endDate) {
			// Se crean las instancias de fecha actual (para fecha inicio y fin)
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();

			// Se setea el valor de fecha inicial recibido ya que no es nulo
			ini.setTime(startDate);

			// Se realiza el ajuste para la fecha inicial y final
			adjustTimeAndDate(ini, true, true);
			adjustTimeAndDate(fin, false, false);

			// Se setea el resultado en el mapa de respuesta
			resultMap.put(start, ini.getTime());
			resultMap.put(end, fin.getTime());

		}

		// AMBAS FECHAS SON NULAS
		else if (null == startDate && null == endDate) {
			// Se crean las instancias de fecha actual (para fecha inicio y fin)
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();

			// Se realiza el ajuste para la fecha inicial y final
			adjustTimeAndDate(ini, true, false);
			adjustTimeAndDate(fin, false, false);

			// Se setea el resultado en el mapa de respuesta
			resultMap.put(start, ini.getTime());
			resultMap.put(end, fin.getTime());
		}

		// NINGUNA FECHA ES NULA
		else if (null != startDate && null != endDate) {
			// Se crean las instancias de fecha actual (para fecha inicio y fin)
			Calendar ini = Calendar.getInstance();
			Calendar fin = Calendar.getInstance();

			// Se setean los valores de fecha inicial y final recibidos ya que no son nulos
			ini.setTime(startDate);
			fin.setTime(endDate);

			// Se realiza el ajuste para la fecha inicial y final
			adjustTimeAndDate(ini, true, true);
			adjustTimeAndDate(fin, false, false);

			// Se setea el resultado en el mapa de respuesta
			resultMap.put(start, ini.getTime());
			resultMap.put(end, fin.getTime());
		}
		return resultMap;
	}

	/**
	 * Realiza el ajuste de la fecha especificada, setea una hora por default si se
	 * requiere y una fecha si tambien es requerido
	 * 
	 * @param date
	 * @param flagInitial
	 * @param flagTimeOnly
	 */
	public static void adjustTimeAndDate(Calendar date, Boolean flagInitial, Boolean flagTimeOnly) {
		if (flagInitial) {
			date.set(Calendar.HOUR_OF_DAY, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
			if (!flagTimeOnly) {
				date.set(Calendar.YEAR, 1975);
				date.set(Calendar.MONTH, 1);
				date.set(Calendar.DAY_OF_MONTH, 1);
			}
		} else {
			date.set(Calendar.HOUR_OF_DAY, 23);
			date.set(Calendar.MINUTE, 59);
			date.set(Calendar.SECOND, 59);
			date.set(Calendar.MILLISECOND, 59);
		}
	}

	/**
	 * Método que obtiene la fecha actual de una determinada Zona Horaria.
	 *
	 */
	public  static Date obtenerFechaZonaHorario(String nombreZonaHoraria) {
		if(nombreZonaHoraria == null || nombreZonaHoraria.isEmpty() ){
			nombreZonaHoraria="America/Mexico_City";
		}
		ZoneId zonaHorario = ZoneId.of(nombreZonaHoraria);
		ZonedDateTime fechaZonaHorario = ZonedDateTime.now(zonaHorario);
		Calendar calendar = Calendar.getInstance();
		calendar.set(fechaZonaHorario.getYear(), fechaZonaHorario.getMonthValue()-1, fechaZonaHorario.getDayOfMonth(), fechaZonaHorario.getHour(), fechaZonaHorario.getMinute(),fechaZonaHorario.getSecond());
		return calendar.getTime();
	}
}
