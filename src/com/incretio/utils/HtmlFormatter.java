package com.incretio.utils;

import java.nio.charset.StandardCharsets;

public class HtmlFormatter {
	public static String formatParagraph(final String text) {
		String newText = text.trim();
		newText = text.replace("\r\n", "</p><p>");
		newText = newText.replace("\r", "</p><p>");
		newText = newText.replace("\n", "</p><p>");
		StringBuilder result = new StringBuilder(newText);
		result.insert(0, "<p>");
		result.append("</p>");
		return result.toString();
	}
	
	public static String convertISO_8859_1ToUtf8(final String text) {
		byte[] bytes = text.getBytes(StandardCharsets.ISO_8859_1);
		return new String(bytes, StandardCharsets.UTF_8);
	}
}
