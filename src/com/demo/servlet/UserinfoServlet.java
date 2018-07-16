package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.po.UserinfoPO;
import com.demo.service.BookService;
import com.demo.util.ConstantUtil;
import com.demo.util.PageUtil;
import com.demo.vo.UserSearchVO;

@WebServlet("/s/userinfo")
public class UserinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String pagenum = request.getParameter("pagenum");
		pagenum = (pagenum == null || pagenum.isEmpty()) ? "1" : pagenum;
		UserSearchVO vo = new UserSearchVO();
		vo.setUname(uname);
		vo.setPagenum(pagenum);
		HttpSession session = request.getSession();
		UserinfoPO po = (UserinfoPO) session.getAttribute("online");
		BookService bs = new BookService();
		List<UserinfoPO> list = bs.getUserList(vo);
		
		int total = bs.getUserTotalCount(vo);
		int maxpage = PageUtil.getMaxPageNum(total);
		if(po.getPower()==1){
			request.setAttribute("uvo", vo);
			request.setAttribute("ulist", list);
			request.setAttribute("maxpage", maxpage);
			request.getRequestDispatcher("/manage/book/userlist.jsp").forward(request, response);
		}else{
			response.sendRedirect(ConstantUtil.CONTEXTPATH+"/manage/book/userinfo.jsp");
		}
	}

}
