package com.demo.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



@WebServlet("/getImage")
public class GetImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String str = getRandomText();
		HttpSession session = request.getSession();
		session.setAttribute("ocode", str);
		
		BufferedImage img = new BufferedImage(120, 30, BufferedImage.TYPE_INT_RGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(102, 51, 204));
		g.fillRect(0, 0, 120, 30);
		
		g.setColor(new Color(255, 204, 51));
		g.setFont(new Font("Arial", Font.PLAIN, 25));
		g.drawString(str, 10, 25);
		
		ImageIO.write(img, "jpeg", response.getOutputStream());
		
	}
	
	public  String getRandomText(){
		Random random = new Random();
		String str = "";
		for(int i = 0;i < 4;i++){
			int temp = random.nextInt(3);
			if(temp==0){
				int n = random.nextInt(10);
				str += n;
			}else if(temp==1){
				int n = 65 + random.nextInt(26);
				char c = (char)n;
				str += c;
			}else{
				int n = 97 + random.nextInt(26);
				char c = (char)n;
				str += c;
			}
		}
		return str;
	}
}
