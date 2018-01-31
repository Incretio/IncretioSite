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

public class AphorismLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int aphorism_id = Integer.valueOf(request.getParameter("id"));		
		String user_id = request.getSession().getId();
		
		BaseJDBC.addLike(user_id, aphorism_id);
		
		int likeCount = BaseJDBC.getLikeCount(aphorism_id);
		System.out.println("likeCount = " + likeCount);
		response.getWriter().write(String.valueOf(likeCount));
	}

}
