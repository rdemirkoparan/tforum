package org.rdemirkoparan.forum.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author recepd
 *
 */
public class DateTimeUtil {

	private DateTimeUtil () {

	}

	public static String parseDate (LocalDateTime date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ("dd.MM.yyyy");
		return null != date ? date.format (formatter) : "N/A";
	}
}
