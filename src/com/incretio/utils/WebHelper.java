package com.incretio.utils;

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
}
