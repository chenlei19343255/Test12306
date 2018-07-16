package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.BookService;
import com.demo.util.ConstantUtil;
import com.demo.vo.AddBookVO;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/s/add")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bname = request.getParameter("bname");
		String aname = request.getParameter("aname");
		String pname = request.getParameter("pname");
		String porder = request.getParameter("porder");
		String bnum = request.getParameter("bnum");
		String pdate = request.getParameter("pdate");
		String cnum = request.getParameter("cnum");
		String price = request.getParameter("price");
		String pubnum = request.getParameter("pubnum");
		
		AddBookVO vo = new AddBookVO();
		vo.setBname(bname);
		vo.setAname(aname);
		vo.setPname(pname);
		vo.setPorder(porder);
		vo.setBnum(bnum);
		vo.setPdate(pdate);
		vo.setCnum(cnum);
		vo.setPrice(price);
		vo.setPubnum(pubnum);
		
		BookService bs = new BookService();
		bs.addBook(vo);
		response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/blist");
		
	}

}
