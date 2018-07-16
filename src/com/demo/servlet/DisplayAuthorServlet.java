package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.po.Book_Author;
import com.demo.service.AuthorService;

@WebServlet("/s/displayAuthor")
public class DisplayAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authorid = request.getParameter("authorid");
		
		AuthorService as = new AuthorService();
		Book_Author po = as.getBook_AuthorPOByAuthorID(authorid);
		List<String> list = as.getBnameListByAuthorid(authorid);
		
		
		request.setAttribute("apo", po);
		request.setAttribute("bnamelist", list);
		request.getRequestDispatcher("/manage/author/authorinfo.jsp").forward(request, response);
	}

}
