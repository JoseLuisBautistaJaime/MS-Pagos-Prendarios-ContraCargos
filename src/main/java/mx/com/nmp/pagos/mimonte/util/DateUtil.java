package mx.com.nmp.pagos.mimonte.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateUtil {

	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static String formatDate(Date date, String format, Locale locale) {
		return new SimpleDateFormat(format, locale).format(date);
	}

	public static Date getDate(String dateStr, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static boolean isSameDay(Date fecha1, Date fecha2) {
		boolean sameDay = false;
		try {
			LocalDate localDate1 = fecha1.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			LocalDate localDate2 = fecha2.toInstant()
					.atZone(ZoneId.systemDefault())
					.toLocalDate();
			sameDay = localDate1.isEqual(localDate2);			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		return sameDay;
	}

}
