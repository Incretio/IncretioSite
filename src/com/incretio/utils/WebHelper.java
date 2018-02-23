package com.incretio.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebHelper {
	public static String getIpFromRequest(final HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if ((ip == null) || (ip.length() == 0) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if ((ip != null) && ip.contains(",")) {
			String[] ips = ip.split(",");
			ip = ips[ips.length - 1];
		}
		if (ip != null) {
			ip = ip.trim();
		}
		return ip;
	}

	public static String getUserIdFromCookies(final HttpServletRequest request) {
		return getValueFromCookies(request, "userId");
	}
		
	public static String getYmUidFromCookies(final HttpServletRequest request) {
		return getValueFromCookies(request, "_ym_uid");
	}
	
	private static String getValueFromCookies(final HttpServletRequest request, final String key) {	
		String defaultValue = "";
		if (request == null) {
			return defaultValue;
		}
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return defaultValue;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase(key)) {
				return cookie.getValue();
			}
		}
		
		return defaultValue;		
	}
	
}
