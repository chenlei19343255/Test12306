package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.po.AreasPO;
import com.demo.po.AuthorPO;
import com.demo.service.AuthorService;
import com.demo.util.PageUtil;
import com.demo.vo.AuthorSearchVO;

@WebServlet("/s/alist")
public class AuthorListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String aname = request.getParameter("aname");
		String pagenum = request.getParameter("pagenum");
		pagenum = (pagenum == null || pagenum.isEmpty() )? "1" : pagenum;
		
		AuthorSearchVO vo = new AuthorSearchVO();
		vo.setAname(aname);
		vo.setPagenum(pagenum);
		
		AuthorService as = new AuthorService();
		List<AuthorPO> list = as.getAuthorList(vo);
		List<AreasPO>  arlist = as.getAreasPOList();
		
		HttpSession session = request.getSession();
		session.setAttribute("arlist", arlist);
		int total = as.getAuthorListTotalCount(vo);
		int maxpage = PageUtil.getMaxPageNum(total);
		
		request.setAttribute("avo", vo);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("alist", list);
		
		request.getRequestDispatcher("/manage/author/authorlist.jsp").forward(request, response);
		
	}

}
