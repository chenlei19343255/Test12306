package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.po.UserinfoPO;
import com.demo.service.BookService;
import com.demo.util.ConstantUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lname = request.getParameter("username");
		String lpass = request.getParameter("password");
		String vcode = request.getParameter("vcode");
		HttpSession session = request.getSession();
		String ocode = (String)session.getAttribute("ocode");
		 
		
		if(vcode.equalsIgnoreCase(ocode)){
			BookService bs = new BookService();
			UserinfoPO po = bs.login(lname, lpass);
			if(po != null){
				session.setAttribute("online", po);
				response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/blist");
			}else{
				response.sendRedirect(ConstantUtil.CONTEXTPATH+"/login.jsp");
			}
		}else{
			response.sendRedirect(ConstantUtil.CONTEXTPATH+"/login.jsp");
		}
		
	}

}
