package com.incretio.servlets;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.incretio.jdbc.AphorismJDBC;
import com.incretio.models.AphorismVo;

public class InsertContentFromFile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			insertData(Files.readAllLines(Paths.get(getServletContext().getContextPath() + "data.txt")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void insertData(final List<String> data) throws ParseException {
		for (int i = 0; i < data.size(); i++) {
			int dataInd = i + 1;
			String line = data.get(i);
			Date date = new Date();
			String text = "";
			String author = "";
			switch(dataInd % 3){
			case 0:
				date = new SimpleDateFormat("dd.MM.yyyy").parse(line);
				break;
			case 1:
				text = line.replaceAll("^\"", "").replaceAll("<br>", "\n");
				break;
			case 2:
				author = line.trim().replace("[", "").replace("]", "");
				break;				
			}
			
			if (i % 3 == 0 && i != 0) {
				AphorismVo aphorism = new AphorismVo();
				aphorism.setCreatedTime(date);
				aphorism.setVideo("");
				aphorism.setImage("");
				aphorism.setText(text);
				aphorism.setAuthor(author);

				AphorismJDBC.addAphorism(aphorism);
			}
		}
	}
}
