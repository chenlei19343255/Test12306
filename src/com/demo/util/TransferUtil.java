package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransferUtil {
	
	/**
	 * 将String转换为java.util.Date
	 * @param str		被转换的字符串
	 * @param patter    转换的格式
	 * @return  转换后的Date对象，如果转换失败返回null
	 */
	public static java.util.Date String2UtilDate(String str, String pattern){
		if(str == null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		java.util.Date temp = null;
		try {
			temp = sdf.parse(str);
		} catch (ParseException e) {
			temp = null;
		}
		return temp;
	}
	
	/**
	 * 将String转换为java.sql.Date
	 * @param str	被转换的字符串
	 * @param pattern	转换的格式
	 * @return	转换后的Date对象，如果转换失败返回null
	 */
	public static java.sql.Date String2SqlDate(String str, String pattern){
		java.util.Date temp = String2UtilDate(str,pattern);
		java.sql.Date date = null;
		if(temp != null){
			date = new java.sql.Date(temp.getTime());
		}
		return date;
	}
	
	/**
	 * 将日期类型转换成String类型
	 * @param date	被转换的日期（可以是util.Date和sql.Date）
	 * @param pattern	转换的格式
	 * @return	转换后的String对象
	 */
	public static String Date2String(java.util.Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
