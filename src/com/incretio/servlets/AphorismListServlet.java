package com.incretio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incretio.models.AphorismVo;
import com.incretio.utils.ModelHelper;

import com.incretio.jdbc.AphorismJDBC;
import com.incretio.jdbc.BaseJDBC;

public class AphorismListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// aphorismList
		List<AphorismVo> aphorismList = AphorismJDBC.getAllAphorism();
		aphorismList = ModelHelper.formatForHtml(aphorismList);	
		request.setAttribute("aphorismList", aphorismList);
		
		// likeCount
		int likeCount = BaseJDBC.getLikeCount();
		request.setAttribute("likeCount", likeCount);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/aphorism_list.jsp");
		dispatcher.forward(request, response);
	}

}
