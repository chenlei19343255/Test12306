package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.demo.po.Book_Author;
import com.demo.service.AuthorService;

@WebServlet("/updateAuthor")
public class UpdateAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authorid = request.getParameter("authorid");
		HttpSession session =request.getSession();
		session.setAttribute("authorid", authorid);
		
		AuthorService as = new AuthorService();
		Book_Author po = as.getBook_AuthorPOByAuthorID(authorid);
		request.setAttribute("apo", po);
		request.getRequestDispatcher("manage/author/updateauthor.jsp").forward(request, response);
	}

}
