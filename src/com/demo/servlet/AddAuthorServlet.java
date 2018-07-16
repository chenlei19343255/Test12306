package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.AuthorService;
import com.demo.util.ConstantUtil;
import com.demo.vo.AddAuthorVO;

@WebServlet("/s/addauthor")
public class AddAuthorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String aname = request.getParameter("aname");
		String bname = request.getParameter("bname");
		String arname = request.getParameter("arname");
		String sex = request.getParameter("sex");
		String birthday = request.getParameter("birthday");
		String phone = request.getParameter("phone");
		String adress = request.getParameter("adress");
		AddAuthorVO vo = new AddAuthorVO();
		vo.setAname(aname);
		vo.setBname(bname);
		vo.setArname(arname);
		vo.setSex(sex);
		vo.setBirthday(birthday);
		vo.setPhone(phone);
		vo.setAdress(adress);
		
		AuthorService as = new AuthorService();
		as.addAuthor(vo);
		response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/alist");
	}

}
