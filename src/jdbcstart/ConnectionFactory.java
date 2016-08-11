package jdbcstart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectionFactory {
	private static ConnectionFactory connectionFactory = null;

	ResourceBundle rb = ResourceBundle.getBundle("dbConConfig", new Locale(
			"en", "UK"));

	String driverClassName = rb.getString("driver");
	String connectionUrl = rb.getString("url");
	String dbUser = rb.getString("user");
	String dbPwd = rb.getString("password");

	private ConnectionFactory() {

		try {
			Class.forName(driverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() throws SQLException {
		Connection conn = null;
		conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
		return conn;
	}

	public static ConnectionFactory getInstance() {
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}
		return connectionFactory;
	}
}
