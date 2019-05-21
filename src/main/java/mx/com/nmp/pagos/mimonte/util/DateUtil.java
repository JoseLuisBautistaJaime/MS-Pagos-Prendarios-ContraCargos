package mx.com.nmp.pagos.mimonte.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

}
