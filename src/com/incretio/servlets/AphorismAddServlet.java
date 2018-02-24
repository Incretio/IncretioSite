package com.incretio.servlets;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incretio.jdbc.AphorismJDBC;
import com.incretio.models.AphorismVo;
import com.incretio.utils.DateFormatExt;
import com.incretio.utils.HtmlFormatter;

public class AphorismAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/aphorism_add.jsp");
		dispatcher.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Date createdTime = DateFormatExt.parseDate1ElseNow(request.getParameter("createdTime"));
		String video = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("video"));
		String image = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("image"));
		String text = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("text"));
		String author = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("author"));
		
		AphorismVo aphorism = new AphorismVo();
		aphorism.setCreatedTime(createdTime);		
		aphorism.setVideo(video);
		aphorism.setImage(image);
		aphorism.setText(text);
		aphorism.setAuthor(author);
		
		AphorismJDBC.addAphorism(aphorism);
		doGet(request, response);
	}
	
	

}
