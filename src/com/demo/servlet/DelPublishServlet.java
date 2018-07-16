package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.demo.service.PublishService;
import com.demo.util.ConstantUtil;

/**
 * Servlet implementation class DelPublishServlet
 */
@WebServlet("/s/delPublish")
public class DelPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pubid = request.getParameter("pubid");
		PublishService ps = new PublishService();
		ps.delPublishByPubid(pubid);
		response.sendRedirect(ConstantUtil.CONTEXTPATH+"/s/plist");
	}

}
