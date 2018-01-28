package com.incretio.utils;

public class HtmlFormatter {
	public static String formatParagraph(final String text) {
		String newText = text.trim();
		newText = text.replace("\r\n", "</p><p>");
		StringBuilder result = new StringBuilder(newText);
		result.insert(0, "<p>");
		result.append("</p>");
		return result.toString();
	}
}
