package com.incretio.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incretio.models.AphorismVo;
import com.incretio.utils.BaseAdapter;
import com.incretio.utils.ModelHelper;
import com.incretio.utils.WebHelper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.incretio.jdbc.AphorismJDBC;
import com.incretio.jdbc.BaseJDBC;

public class AphorismLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	class AphorismInfo {
		private int likeCount;
		private boolean userLiked;
		public int getLikeCount() {
			return likeCount;
		}
		public void setLikeCount(int likeCount) {
			this.likeCount = likeCount;
		}
		public boolean isUserLiked() {
			return userLiked;
		}
		public void setUserLiked(boolean userLiked) {
			this.userLiked = userLiked;
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int aphorismId = Integer.valueOf(request.getParameter("id"));		
		String userId = BaseAdapter.getUserId(request);
		
		BaseJDBC.addLike(userId, aphorismId);
		
		int likeCountLocal = BaseJDBC.getLikeCount(aphorismId);
		boolean userLikedLocal = BaseJDBC.wasLiked(userId, aphorismId);
		//System.out.println("likeCount = " + likeCount);
		//response.getWriter().write(String.valueOf(likeCount));
		AphorismInfo aphorismInfo = new AphorismInfo();
		aphorismInfo.setLikeCount(likeCountLocal);
		aphorismInfo.setUserLiked(userLikedLocal);
		response.setContentType("application/json");
		new Gson().toJson(aphorismInfo, response.getWriter());
	}

}
