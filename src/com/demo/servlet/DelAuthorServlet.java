package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.demo.service.AuthorService;
import com.demo.util.ConstantUtil;

@WebServlet("/s/delAuthor")
public class DelAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authorid = request.getParameter("authorid");
		AuthorService as = new AuthorService();
		as.delAuthorByAuthorid(authorid);
		response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/alist");
	}

}
