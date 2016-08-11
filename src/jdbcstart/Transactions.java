package jdbcstart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Transactions {
	private static final int NUM = 5;

	public static void rollbackEx() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = ConnectionFactory.getInstance().getConnection();
			st = cn.createStatement();
			cn.setAutoCommit(false);
			boolean bError = false;
			try {
				for (int i = 1; i < NUM; i++) {
					if (bError) {
						break;
					}
					st.executeUpdate("INSERT INTO DEPARTMENTS VALUES ("
							+ (i + 1) + ",'testing" + i + "')");
				}
				if (bError) {
					cn.rollback();
					System.out.println("rolled back1");
				} else {
					cn.commit();
					System.out.println("committed");
				}
			} catch (SQLException SQLe) {
				cn.rollback();
				System.out.println("rolled back2");
			} catch (Exception e) {
				cn.rollback();
				System.out.println("rolled back3");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
				cn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		rollbackEx();
	}
}
