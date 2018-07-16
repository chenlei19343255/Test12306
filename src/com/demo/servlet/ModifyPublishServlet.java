package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.service.PublishService;
import com.demo.util.ConstantUtil;
import com.demo.vo.AddPublishVO;

/**
 * Servlet implementation class ModifyPublishServlet
 */
@WebServlet("/s/modifyPublish")
public class ModifyPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String pubid = (String) session.getAttribute("pubid");
		String pname = request.getParameter("pname");
		String sname = request.getParameter("sname");
		String arname = request.getParameter("arname");
		String tel = request.getParameter("tel");
		String adress = request.getParameter("adress");
		AddPublishVO vo = new AddPublishVO();
		vo.setPubid(pubid);
		vo.setPname(pname);
		vo.setSname(sname);
		vo.setArname(arname);
		vo.setTel(tel);
		vo.setAdress(adress);
		PublishService ps = new PublishService();
		ps.updatePublishByPubid(vo);
		response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/plist");
	}

}
