package com.incretio.utils;

import javax.servlet.http.HttpServletRequest;

public class BaseAdapter {
	public static String getUserId(final HttpServletRequest request) {
		return WebHelper.getUserIdFromCookies(request);
	}
}
