package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.po.PublisherPO;
import com.demo.service.PublishService;

@WebServlet("/s/displayPublish")
public class DisplayPublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pubid = request.getParameter("pubid");
		PublishService ps = new PublishService();
		PublisherPO po = ps.getPublishPOByPubid(pubid);
		List<String> bnamelist = ps.getBnameListByPubid(pubid);
		
		request.setAttribute("ppo", po);
		request.setAttribute("bnamelist", bnamelist);
		
		request.getRequestDispatcher("/manage/publisher/publishinfo.jsp").forward(request, response);
	}

}
