package com.incretio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.incretio.jdbc.AphorismJDBC;
import com.incretio.utils.BaseAdapter;

public class AphorismGetMyLikesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = BaseAdapter.getUserId(request);
		List<String> myLikedAphorismList = AphorismJDBC.getMyLikedAphorism(userId);
		response.setContentType("application/json");
		new Gson().toJson(myLikedAphorismList, response.getWriter());
	}

}
