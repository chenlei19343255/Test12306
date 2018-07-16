package com.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TransferUtil {
	
	/**
	 * ��Stringת��Ϊjava.util.Date
	 * @param str		��ת�����ַ���
	 * @param patter    ת���ĸ�ʽ
	 * @return  ת�����Date�������ת��ʧ�ܷ���null
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
	 * ��Stringת��Ϊjava.sql.Date
	 * @param str	��ת�����ַ���
	 * @param pattern	ת���ĸ�ʽ
	 * @return	ת�����Date�������ת��ʧ�ܷ���null
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
	 * ����������ת����String����
	 * @param date	��ת�������ڣ�������util.Date��sql.Date��
	 * @param pattern	ת���ĸ�ʽ
	 * @return	ת�����String����
	 */
	public static String Date2String(java.util.Date date, String pattern){
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

}
