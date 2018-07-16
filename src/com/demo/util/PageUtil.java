package com.demo.util;

public class PageUtil {
	
	//ÿҳ�����ʾ��¼����Ŀ��
	public static final int PAGE_ITEMS = 5;
	
	/**
	 * ����ҳ����������ݿ��в�ѯ����ʼλ��
	 * @param pagenum
	 * @return
	 */
	public static int getIndex(int pagenum){
		return (pagenum - 1) * PAGE_ITEMS;
	}
	
	/**
	 * ���ݼ�¼������ҳ�����������ҳ������
	 * @param total
	 * @return
	 */
	public static int getMaxPageNum(int total){
		return total % PAGE_ITEMS == 0 ? total / PAGE_ITEMS : total / PAGE_ITEMS + 1;
	}

}
