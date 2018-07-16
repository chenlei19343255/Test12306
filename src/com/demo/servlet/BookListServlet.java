package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.po.AuthorPO;
import com.demo.po.Book_Author;
import com.demo.po.PublisherPO;
import com.demo.service.BookService;
import com.demo.util.PageUtil;
import com.demo.vo.BookSearchVO;

@WebServlet("/s/blist")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String bname = request.getParameter("bname");
		String aname = request.getParameter("aname");
		String pname = request.getParameter("pname");
		String pagenum = request.getParameter("pagenum");
		pagenum = (pagenum == null || pagenum.isEmpty() )? "1" : pagenum;
		
		BookSearchVO vo = new BookSearchVO();
		vo.setBname(bname);
		vo.setAname(aname);
		vo.setPname(pname);
		vo.setPagenum(pagenum);
		
		
		BookService bs = new BookService();
		List<Book_Author> list = bs.getBookList(vo);
		List<PublisherPO> plist = bs.getPList();
		List<AuthorPO> alist = bs.getAlist();
		int total = bs.getBookListTotalCount(vo);
		int maxpage = PageUtil.getMaxPageNum(total);
		
		HttpSession session = request.getSession();
		session.setAttribute("plist", plist);
		session.setAttribute("alist", alist);
		
		request.setAttribute("bvo", vo);
		request.setAttribute("blist", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/manage/book/booklist.jsp").forward(request, response);
		
	}

}
