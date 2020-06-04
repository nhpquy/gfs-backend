
package com.gfs.domain.utils;

import com.gfs.domain.constant.Define;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTimeProvider {

	public static final String TYPE_1 = "MMM dd, yyyy hh:mm a";
	public static final String TYPE_2 = "dd/MM/yyyy HH:mm:ss a";
	public static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String ISO_FORMAT_MILLISECONDS = "yyyy-MM-dd'T'HH:mm:ss.S'Z'";

	public static final DateFormat DATE_FORMAT_LOG = new SimpleDateFormat("MMM dd, yyyy");

	public static final int DAY_TO_MILISECOND = 24 * 60 * 60 * 1000;
	public static final int HOUR_TO_MILISECOND = 60 * 60 * 1000;
	public static final int MINUTE_TO_MILISECOND = 60 * 1000;
	public static final int SECOND_TO_MILISECOND = 1000;

	public static final DateFormat DATE_ISO_FORMAT = new SimpleDateFormat(ISO_FORMAT);

	public static String getStringCurrentDate(String format) {
		if (!StringUtils.hasText(format)) {
			format = TYPE_1;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	public static String getStringCurrentDate() {
		DateFormat dateFormat = new SimpleDateFormat(TYPE_1);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}

	public static Date getCurrentDate(String format) {
		try {
			if (!StringUtils.hasText(format)) {
				format = TYPE_1;
			}
			DateFormat dateFormat = new SimpleDateFormat(format);
			Calendar cal = Calendar.getInstance();
			String result = dateFormat.format(cal.getTime());
			return stringToDate(result, format);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static int getDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	public static int getLastDayOfMonth() {
		return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static Date stringToDate(String strDate, String dateFormat) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat();
		formatter.applyPattern(dateFormat);
		return formatter.parse(strDate);
	}

	public static Date getDateFromDate(Date date, int last) {
		if (last != 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, last);
			return calendar.getTime();
		}
		return date;
	}

	public static String convertDatetoString(Date date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String convertDatetoString(Date date, String format, String defaultValue) {
		try {
			DateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(date);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Date getBeginOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	public static Date getEndOfDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	public static Date getEndOfYesterday(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);
		return calendar.getTime();
	}

	public static long getMilliSecondBeginOfDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.AM_PM, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTimeInMillis();
	}

	@SuppressWarnings("deprecation")
	public static String convertDateToISODate(Date date, String time) {
		String[] tmp = time.split(":");
		date.setHours(Integer.parseInt(tmp[0]));
		date.setMinutes(Integer.parseInt(tmp[1]));
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat(ISO_FORMAT);
		df.setTimeZone(tz);
		return df.format(date);
	}

	@SuppressWarnings("deprecation")
	public static String convertStringDateToISODate(String time) {
		try {
			Date date = new Date(time);
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat(ISO_FORMAT);
			df.setTimeZone(tz);
			return df.format(date);
		} catch (Exception exception) {
			return time;
		}
	}

	public static String convertDateToISODate(Date time) {
		try {
			TimeZone tz = TimeZone.getTimeZone("UTC");
			DateFormat df = new SimpleDateFormat(ISO_FORMAT);
			df.setTimeZone(tz);
			return df.format(time);
		} catch (Exception e) {
			return "";
		}
	}

	public static Date getDateUTC(Date date, String dateFormat) {
		try {
			String strDateUTC = getStringDateInTimezone(date, dateFormat, "UTC");
			return stringToDate(strDateUTC, dateFormat);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getStringDateInTimezone(Date date, String dateFormat, String timezone) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
	}

	public static String getStringDateInUTC(Date date, String dateFormat) {
		TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat(dateFormat);
		df.setTimeZone(tz);
		return df.format(date);
	}

	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static boolean isDateValidByFormat(String format, String date) {
		return isDateValidByFormat(new SimpleDateFormat(format), date);
	}

	public static boolean isDateValidByFormat(DateFormat dateFormat, String date) {
		try {
			dateFormat.setLenient(false);
			Date result = dateFormat.parse(date);
			return result != null ? true : false;
		} catch (Exception ex) {
			return false;
		}
	}

	public static Date convertStringToDate(String dateFormat, String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			return sdf.parse(date);
		} catch (ParseException e) {
			return new Date();
		}
	}

	public static Calendar getDateInfo(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static Date setDateInfo(Date date, int hour, int minute, int second) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			if (hour >= 0) {
				calendar.set(Calendar.HOUR_OF_DAY, hour);
			}
			if (minute >= 0) {
				calendar.set(Calendar.MINUTE, minute);
			}
			if (second >= 0) {
				calendar.set(Calendar.SECOND, second);
			}
			return calendar.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public static Date setDateToBeginningOfDate(Date date) {
		return setDateInfo(date, 0, 0, 0);
	}

	public static Date setDateToEndOfDate(Date date) {
		return setDateInfo(date, 23, 59, 59);
	}

	public static boolean compareDay(String sday1, String sday2, String typeFomart) {
		SimpleDateFormat sdf = new SimpleDateFormat(typeFomart);
		try {
			return sdf.parse(sday2).compareTo(sdf.parse(sday1)) > 0;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String getStringDateFollowFormat(Date date, String format) {
		if (!StringUtils.hasText(format)) {
			format = TYPE_1;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static String convertDateToStringLogin(String language, long time) {
		try {
			String result = "%s UTC";
			Date date = new Date(time);
			DateFormat format = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a");
			if (Define.LANGUAGE_VI.equals(language)) {
				format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
			}
			if (Define.LANGUAGE_ZH_CN.equals(language)) {
				result = "UTC%s";
				format = new SimpleDateFormat("时区yyyy年MM月dd日上午hh时mm分");
			}
			if (Define.LANGUAGE_KO.equals(language)) {
				format = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss a");
			}
			if (Define.LANGUAGE_ES.equals(language)) {
				format = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
			}
			format.setTimeZone(TimeZone.getTimeZone("UTC"));
			return String.format(result, format.format(date));
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToStringWithTimeZone(String language, long time, TimeZone timzone) {
		try {
			String result = "%s UTC";
			Date date = new Date(time);
			DateFormat format = new SimpleDateFormat("MMM-dd-yyyy hh:mm a");
			if (Define.LANGUAGE_VI.equals(language)) {
				format = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			}
			if (Define.LANGUAGE_ZH_CN.equals(language)) {
				result = "UTC%s";
				format = new SimpleDateFormat("时区yyyy年MM月dd日上午hh时mm分");
			}
			if (Define.LANGUAGE_KO.equals(language)) {
				format = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm a");
			}
			if (Define.LANGUAGE_ES.equals(language)) {
				format = new SimpleDateFormat("MM-dd-yyyy hh:mm a");
			}
			format.setTimeZone(timzone);
			return String.format(result, format.format(date));
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToStringWithTimeZoneEmailFormat(String language, long time, TimeZone timzone) {
		try {

			Date date = new Date(time);
			DateFormat format = new SimpleDateFormat("hh:mm a ::: MMM-dd-yyyy");
			if (Define.LANGUAGE_VI.equals(language)) {
				format = new SimpleDateFormat("hh:mm a ::: dd-MM-yyyy");
			}
			if (Define.LANGUAGE_ZH_CN.equals(language)) {
				format = new SimpleDateFormat(":::时区yyyy年MM月dd日上午hh时mm分");
			}
			if (Define.LANGUAGE_KO.equals(language)) {
				format = new SimpleDateFormat("hh:mm a ::: yyyy년 MM월 dd일");
			}
			if (Define.LANGUAGE_ES.equals(language)) {
				format = new SimpleDateFormat("hh:mm a ::: MM-dd-yyyy");
			}
			format.setTimeZone(timzone);
			return format.format(date).replace(":::", "UTC");
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToStringGMT(Date date) {
		try {
			DateFormat gmtFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
			TimeZone gmtTime = TimeZone.getTimeZone("GMT");
			gmtFormat.setTimeZone(gmtTime);
			return gmtFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertDateToString(Date date, String typeFormat, String timzone) {
		try {
			DateFormat gmtFormat = new SimpleDateFormat(typeFormat);
			TimeZone gmtTime = TimeZone.getTimeZone(timzone);
			gmtFormat.setTimeZone(gmtTime);
			return gmtFormat.format(date);
		} catch (Exception e) {
			return null;
		}
	}

	public static String convertObjectIDToStringDate(ObjectId objectID) {
		try {
			Date date = objectID.getDate();
			return getStringDateFollowFormat(date, "MM/dd/yyyy HH:mm");
		} catch (Exception e) {
			return "";
		}
	}

	public static String convertMinuteDurationToString(long durationInMiliseconds) {

		long minutes = durationInMiliseconds / (60 * 1000);

		int days = (int) (minutes / (24 * 60));
		if (days > 0) {
			minutes -= days * 24 * 60;
		}
		int hours = (int) (minutes / 60);
		if (hours > 0) {
			minutes -= hours * 60;
		}

		StringBuilder result = new StringBuilder();

		if (days > 0) {
			result.append(String.format("%s day%s ", days, days > 1 ? "s" : ""));
		}

		if (hours > 0) {
			result.append(String.format("%s hour%s ", hours, hours > 1 ? "s" : ""));
		}

		if (minutes > 0) {
			result.append(String.format("%s minute%s", minutes, minutes > 1 ? "s" : ""));
		}

		return result.toString().trim();

	}

	public static String convertTimestampToStringDate(long timestamp) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("MMM-dd-yyyy HH:mm:ss zzz");// dd/MM/yyyy
			return sdfDate.format(new Date(timestamp));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static String convertTimestampToStringDateForVNPay(long timestamp) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdfDate.format(new Date(timestamp));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static long convertVNPayStringDateToTimestamp(String stringDate) {
		try {
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdfDate.parse(stringDate).getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}

	public static void main(String[] args) {
		System.out.println(convertDateToStringWithTimeZoneEmailFormat("sms_templates/en", 1559561737987L,
				TimeZone.getTimeZone("UTC")));
	}

}
