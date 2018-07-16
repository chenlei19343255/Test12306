package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.po.BookAuthorTypePO;
import com.demo.po.Book_Author;
import com.demo.service.BookService;

@WebServlet("/s/display")
public class DisplayBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookid = request.getParameter("bookid");
		BookService bs = new BookService();
		Book_Author po = bs.getBookAuthorPOById(bookid);
		List<BookAuthorTypePO> list = bs.getBookAuthorTypeListByBookid(bookid);
		
		request.setAttribute("bpo", po);
		request.setAttribute("batlist", list);
		request.getRequestDispatcher("/manage/book/bookinfo.jsp").forward(request, response);
	}

}
