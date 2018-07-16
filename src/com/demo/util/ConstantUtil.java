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
		
		TEXT.add("图书");
		TEXT.add("作者");
		TEXT.add("出版社");
	}
}
