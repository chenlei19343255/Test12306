package com.demo.util;

public class PageUtil {
	
	//每页最多显示记录的条目数
	public static final int PAGE_ITEMS = 5;
	
	/**
	 * 根据页码计算在数据库中查询的起始位置
	 * @param pagenum
	 * @return
	 */
	public static int getIndex(int pagenum){
		return (pagenum - 1) * PAGE_ITEMS;
	}
	
	/**
	 * 根据记录数计算页码总数（最大页码数）
	 * @param total
	 * @return
	 */
	public static int getMaxPageNum(int total){
		return total % PAGE_ITEMS == 0 ? total / PAGE_ITEMS : total / PAGE_ITEMS + 1;
	}

}
