package com.incretio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incretio.models.AphorismVo;
import com.incretio.utils.BaseAdapter;
import com.incretio.utils.ModelHelper;
import com.incretio.utils.WebHelper;
import com.incretio.jdbc.AphorismJDBC;
import com.incretio.jdbc.BaseJDBC;

public class AphorismListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String userId = BaseAdapter.getUserId(request);
		// aphorismList
		List<AphorismVo> aphorismList = AphorismJDBC.getAllAphorism();
		aphorismList = ModelHelper.formatForHtml(aphorismList);

		// likeCount
		aphorismList.forEach(value -> value.setLikeCount(BaseJDBC.getLikeCount(value.getId())));
		// wasLiked
		aphorismList.forEach(value -> value.setWasLiked(BaseJDBC.wasLiked(userId, value.getId())));

		request.setAttribute("aphorismList", aphorismList);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/aphorism_list.jsp");
		dispatcher.forward(request, response);
	}

}
