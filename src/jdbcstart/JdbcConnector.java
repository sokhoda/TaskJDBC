package jdbcstart;

import java.nio.CharBuffer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class JdbcConnector {
	public static void main(String[] args) {
		Connection conn = getConnection();
		// String sql = "Insert into MISC VALUES (?,?,?,?,?)";
		String sql = "Insert into Countries VALUES (?,?,?)";

		// List params = Arrays
		// .asList(0, 1, 1, "Hust", "Hust is a beautiful city");
		List params = Arrays.asList(0, "Ukraine", "Native");
		int res = getInsUpdDel(conn, sql, params);
		System.out.println(res);

		// String select = "select * from MISC";
		String select = "select * from Countries";
		List result = getSelect(conn, select, null);
		ResultSet rs = (ResultSet) result.get(0);
		outputResSet(rs, -15);

		PreparedStatement ps = (PreparedStatement) result.get(1);
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
			if (conn != null) conn.close();
		} catch (SQLException e) {
			System.out.println("Failed to close connection. " + e);
		}
	}

	public static void outputResSet(ResultSet rs, int width) {
		ResultSetMetaData metaData;
		int colNum = 0;
		try {
			metaData = rs.getMetaData();
			colNum = metaData.getColumnCount();
			for (int i = 1; i <= colNum; i++) {
				System.out.printf("%" + width + "s", metaData.getColumnName(i));
			}
			System.out.println("\n"
					+ CharBuffer.allocate(colNum * Math.abs(width)).toString()
					.replace('\0', '-'));

			while (rs.next()) {
				for (int j = 1; j <= colNum; j++) {
					System.out.printf("%" + width + "s", rs.getString(j));
				}
				System.out.println();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public static Connection getConnection() {
		Connection conn = null;
		Statement stm = null;

		ResourceBundle rb = ResourceBundle.getBundle("dbConConfig", new Locale(
				"en", "UK"));

		String url = rb.getString("url");
		String driver = rb.getString("driver");
		String user = rb.getString("user");
		String password = rb.getString("password");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class not found." + e);
		} catch (SQLException e) {
			System.out.println("Can't connect to database." + e);
		}
		return conn;
	}

	public static Connection startJDBC() {
		Connection conn = null;
		Statement stm = null;

		ResourceBundle rb = ResourceBundle.getBundle("dbConConfig", new Locale(
				"en", "UK"));

		String url = rb.getString("url");
		String driver = rb.getString("driver");
		String user = rb.getString("user");
		String password = rb.getString("password");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			stm = conn.createStatement();
			// stm.executeUpdate("Insert into agents VALUES(3,null,'Olena','Prame');");
			ResultSet res = stm.executeQuery("select * from countries");
			while (res.next()) {
				System.out.println(res.getLong(1) + "\t" + res.getString(2)
						+ "\t" + res.getString(3));
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Driver Class not found." + e);
		} catch (SQLException e) {
			System.out.println("Can't connect to database." + e);
		} finally {
			try {
				stm.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static PreparedStatement getPrepStatement(Connection conn,
			String sql, List params) {
		PreparedStatement prepSt = null;

		if (conn != null) {
			try {
				prepSt = conn.prepareStatement(sql);
				if (params != null) {
					int i = 1;
					for (Object obj : params) {
						if (obj instanceof Integer) {
							prepSt.setInt(i++, (Integer) obj);
						} else if (obj instanceof Long) {
							prepSt.setLong(i++, (Long) obj);
						} else if (obj instanceof String) {
							prepSt.setString(i++, (String) obj);
						} else if (obj instanceof Double) {
							prepSt.setDouble(i++, (Double) obj);
						} else if (obj instanceof Float) {
							prepSt.setFloat(i++, (Float) obj);
						} else if (obj instanceof Boolean) {
							prepSt.setBoolean(i++, (Boolean) obj);
						} else if (obj instanceof Date) {
							prepSt.setDate(i++, (java.sql.Date) obj);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return prepSt;
	}

	public static List getSelect(Connection conn, String sql, List params) {
		ResultSet res = null;
		PreparedStatement ps = getPrepStatement(conn, sql, params);
		try {
			res = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Arrays.asList(res, ps);
	}

	public static int getInsUpdDel(Connection conn, String sql, List params) {
		int res = 0;
		try (PreparedStatement ps = getPrepStatement(conn, sql, params)) {
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static boolean isQueryUpdate(String sql) {
		sql.trim();
		String queryType = new StringTokenizer(sql, " ").nextToken()
				.toUpperCase();
		return ("INSERT".equals(queryType) || "UPDATE".equals(queryType) || "DELETE"
				.equals(queryType));
	}
}
