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

		/*Part part = request.getPart("user_image");
		System.out.println("file name = " + part.getName());
		for (String value : part.getHeaderNames()) {
			System.out.print("header name " + value + " = ");
			System.out.println(part.getHeader(value));
		}
		https://docs.oracle.com/javaee/6/tutorial/doc/glraq.html
		*/

		
		String postImagesPath = "/home/rodin/";
		try {
			URL website = new URL(image);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			String fileName = new File(image).getName();
			System.out.println(fileName);
			try (FileOutputStream fos = new FileOutputStream(postImagesPath + fileName)) {
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

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
