package com.demo.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.po.PublisherPO;
import com.demo.service.PublishService;

@WebServlet("/s/updatePublish")
public class UpdatePublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pubid = request.getParameter("pubid");
		HttpSession session = request.getSession();
		session.setAttribute("pubid", pubid);
		PublishService ps = new PublishService();
		PublisherPO po = ps.getPublishPOByPubid(pubid);
		request.setAttribute("ppo", po);
		request.getRequestDispatcher("/manage/publisher/updatepublish.jsp").forward(request, response);
	}

}
