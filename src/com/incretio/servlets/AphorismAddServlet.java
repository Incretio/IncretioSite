package com.incretio.servlets;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.incretio.jdbc.AphorismJDBC;
import com.incretio.models.AphorismVo;
import com.incretio.utils.DateFormatExt;
import com.incretio.utils.HtmlFormatter;
import com.incretio.utils.WebHelper;

@MultipartConfig
public class AphorismAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/aphorism_add.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Date createdTime = DateFormatExt.parseDate1ElseNow(request.getParameter("createdTime"));
		String video = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("video"));
		String image = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("image"));
		String text = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("text"));
		String author = HtmlFormatter.convertISO_8859_1ToUtf8(request.getParameter("author"));
		
		String serverImagePath = getServletContext().getInitParameter("serverImagePath");
		
		String appPath = request.getServletContext().getRealPath("/");
		String targetPath = appPath.concat(serverImagePath.replaceFirst("/", ""));
		new File(targetPath).mkdirs();
		String fileName = "";
		if (!image.isEmpty()) {
			// ToDo: error if file not image extension
			// example: /static/img/content/2096675020_g17T4.jpg?s=128&g=1
			// 	https://www.gravatar.com/avatar/ba1627f54e3b9c1d5f1ecb27b44b0b07?s=32&d=identicon&r=PG
			fileName = WebHelper.saveFileToServer(image, targetPath);
		}

		/*
		 * Part part = request.getPart("user_image"); System.out.println("file name = "
		 * + part.getName()); for (String value : part.getHeaderNames()) {
		 * System.out.print("header name " + value + " = ");
		 * System.out.println(part.getHeader(value)); }
		 * https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
		 */

		AphorismVo aphorism = new AphorismVo();
		aphorism.setCreatedTime(createdTime);
		aphorism.setVideo(video);
		aphorism.setImage(serverImagePath.concat(fileName));
		aphorism.setText(text);
		aphorism.setAuthor(author);

		AphorismJDBC.addAphorism(aphorism);
		doGet(request, response);
	}

	

}
