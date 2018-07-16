package com.demo.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.demo.util.ConstantUtil;

public class NavigatorTag extends TagSupport{
	
	private int index = 0;
	
	@Override
	public int doStartTag() throws JspException {
		JspWriter out = pageContext.getOut();
		try{
			out.println("<ul id=\"navigation\">");
			for(int i = 0; i < ConstantUtil.URL.size(); i++){
				if(index == i){
					out.println("<li><a class=\"active\" href=\""+ConstantUtil.URL.get(i)+"\">"+ConstantUtil.TEXT.get(i)+"</a></li>");
				}else{
					out.println("<li><a href=\""+ConstantUtil.URL.get(i)+"\">"+ConstantUtil.TEXT.get(i)+"</a></li>");
				}
			}
			out.println("</ul>");
			out.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SKIP_BODY;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
