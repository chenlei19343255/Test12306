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
import com.demo.po.PublisherPO;
import com.demo.service.AuthorService;
import com.demo.service.PublishService;
import com.demo.util.PageUtil;
import com.demo.vo.PublishSerachVO;

@WebServlet("/s/plist")
public class PubListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pname = request.getParameter("pname");
		String pagenum = request.getParameter("pagenum");
		pagenum = (pagenum == null || pagenum.isEmpty()) ? "1" : pagenum;
		
		PublishSerachVO vo = new PublishSerachVO();
		vo.setPname(pname);
		vo.setPagenum(pagenum);
		
		AuthorService as = new AuthorService();
		List<AreasPO> arlist = as.getAreasPOList();
		HttpSession session = request.getSession();
		session.setAttribute("arlist", arlist);
		
		
		PublishService ps = new PublishService();
		List<PublisherPO> list = ps.getPublishList(vo);
		
		
		int total = ps.getPublishListTotalCount(vo);
		
		int maxpage = PageUtil.getMaxPageNum(total);
		
		request.setAttribute("pvo", vo);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("plist", list);
		request.getRequestDispatcher("/manage/publisher/publist.jsp").forward(request, response);
	}

}
