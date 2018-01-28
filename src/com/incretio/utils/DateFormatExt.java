package com.incretio.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatExt {
	/**
	 * yyyy-MM-dd'T'HH:mm
	 */
	public static String formatDate1(final Date date) {
		return null;
	}

	/**
	 * yyyy-MM-dd'T'HH:mm
	 * @throws ParseException 
	 */
	public static Date parseDate1(final String date) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		return dateFormat.parse(date);
	}
	
	/**
	 * yyyy-MM-dd'T'HH:mm
	 * If parse exception then return now date.
	 */
	public static Date parseDate1ElseNow(final String date) {		
		try {
			return parseDate1(date);
		} catch (ParseException e) {
			return new Date();
		}
	}
}
