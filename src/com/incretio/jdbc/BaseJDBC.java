package com.incretio.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseJDBC {
	public static int getLikeCount(final int aphorism_id) {
		int result = -1;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/IncretioSite?user=root&password=123456789");
					PreparedStatement stmt = conn.prepareStatement(
							"SELECT COUNT(*) as likeCount FROM AphorismLike WHERE aphorism_id = ?");) {
				stmt.setInt(1, aphorism_id);
				ResultSet rs = stmt.executeQuery();
				rs.next();
				result = rs.getInt("likeCount");
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static boolean wasLiked(final String user_id, final int aphorism_id) {
		boolean result = false;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/IncretioSite?user=root&password=123456789");
					PreparedStatement stmt = conn.prepareStatement(
							"SELECT COUNT(*) as likeCount FROM AphorismLike WHERE user_id = ? AND aphorism_id = ?");) {
				stmt.setString(1, user_id);
				stmt.setInt(2, aphorism_id);				
				ResultSet rs = stmt.executeQuery();				
				rs.next();
				result = rs.getInt("likeCount") > 0;
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void addLike(final String user_id, final int aphorism_id) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			try (Connection conn = DriverManager
					.getConnection("jdbc:mysql://localhost/IncretioSite?user=root&password=123456789")) {
				PreparedStatement pstmt = conn
						.prepareStatement("DELETE FROM AphorismLike WHERE aphorism_id = ? AND user_id = ?");
				pstmt.setInt(1, aphorism_id);
				pstmt.setString(2, user_id);
				int deletedCount = pstmt.executeUpdate();
				System.out.println(String.format("deletedCount: %s; aphorism_id = %s, user_id = %s", deletedCount, aphorism_id, user_id));

				if (deletedCount == 0) {
					PreparedStatement stmt = conn.prepareStatement(
							"INSERT INTO AphorismLike (aphorism_id, user_id, type) " + "VALUES(?, ?, 0)");
					stmt.setInt(1, aphorism_id);
					stmt.setString(2, user_id);
					int insertedCount = stmt.executeUpdate();
					System.out.println(String.format("insertedCount: %s; aphorism_id = %s, user_id = %s", insertedCount, aphorism_id, user_id));
				}
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
