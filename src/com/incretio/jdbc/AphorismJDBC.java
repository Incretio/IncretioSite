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
	public static List<AphorismVo> addAphorism(final AphorismVo aphorism) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<AphorismVo> result = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/IncretioSite?user=root&password=123456789");
			stmt = conn.prepareStatement("INSERT INTO Aphorism (createdTime, text, author) VALUES (?, ?, ?)");
			stmt.setTimestamp(1, new Timestamp(aphorism.getCreatedTime().getTime()));
			stmt.setString(2, aphorism.getText());
			stmt.setString(3, aphorism.getAuthor());
			stmt.executeUpdate();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static List<AphorismVo> getAllAphorism() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<AphorismVo> result = new ArrayList<>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/IncretioSite?user=root&password=123456789");
			stmt = conn.createStatement();
			
			if (stmt.execute("SELECT * FROM Aphorism ORDER BY createdTime DESC")) {
				rs = stmt.getResultSet();
			}
			while(rs.next()) {
				int id = rs.getInt("id");
				Date createdDate = rs.getTimestamp("createdTime");
				String text = rs.getString("text");
				String author = rs.getString("author");
				result.add(new AphorismVo(id, createdDate, text, author));
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
