package com.tool;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public static String getCurrentTime(String format) {
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String now = sdf.format(today);
		return now;
	}

	public static long parseTime2Long(String str, String timeType) {
		DateFormat df = new SimpleDateFormat(timeType);
		long date = 0;
		try {
			date = df.parse(str).getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (date != 0) {
			date = date / 1000;
		}
		return date;
	}

	public static Integer getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}

		return age;
	}

	public static Date parseDatetime(String datetime, String pattern)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.parse(datetime);
	}

	public static String parseDatetime2String(String datetime, String pattern)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date = format.parse(datetime);
		return format.format(date);
	}
	
	public static void main(String [] arg){
		String time="2014-04-09 15:02:14";
	
		String timestr=time.substring(0, 10);
		String str="";
		for(int i=0;i<timestr.split("-").length;i++){
			str+=timestr.split("-")[i];
		}
		
		System.out.println(str);
	}

}
