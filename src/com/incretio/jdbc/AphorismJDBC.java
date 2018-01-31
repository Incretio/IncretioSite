package com.incretio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.incretio.models.AphorismVo;

public class AphorismJDBC {
	public static void addAphorism(final AphorismVo aphorism) {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/IncretioSite?user=incretio&password=IncreKantra27&characterEncoding=utf8");
					PreparedStatement stmt = conn
							.prepareStatement("INSERT INTO Aphorism (createdTime, text, author) VALUES (?, ?, ?)");) {
				stmt.setTimestamp(1, new Timestamp(aphorism.getCreatedTime().getTime()));
				stmt.setString(2, aphorism.getText());
				stmt.setString(3, aphorism.getAuthor());
				stmt.executeUpdate();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<AphorismVo> getAllAphorism() {
		List<AphorismVo> result = new ArrayList<>();

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/IncretioSite?user=incretio&password=IncreKantra27&characterEncoding=utf8");
					Statement stmt = conn.createStatement();) {
				ResultSet rs = null;
				if (stmt.execute("SELECT * FROM Aphorism ORDER BY createdTime DESC")) {
					rs = stmt.getResultSet();
				}
				while (rs.next()) {
					int id = rs.getInt("id");
					Date createdDate = rs.getTimestamp("createdTime");
					String text = rs.getString("text");
					String author = rs.getString("author");
					result.add(new AphorismVo(id, createdDate, text, author));
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
