package com.demo.util;

import java.util.ArrayList;
import java.util.List;

public class ConstantUtil {

	public static final String CONTEXTPATH = "/BookManagement";
	public static List<String> URL = new ArrayList<String>();
	public static List<String> TEXT = new ArrayList<String>();
	
	static{
		URL.add(ConstantUtil.CONTEXTPATH+"/s/blist");
		URL.add(ConstantUtil.CONTEXTPATH+"/s/alist");
		URL.add(ConstantUtil.CONTEXTPATH+"/s/plist");
		
		TEXT.add("ͼ��");
		TEXT.add("����");
		TEXT.add("������");
	}
}
