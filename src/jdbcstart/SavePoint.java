package jdbcstart;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SavePoint {
	private static final int NUM = 5;

	public static void rollbackEx() {
		Connection cn = null;
		Statement st = null;
		try {
			cn = ConnectionFactory.getInstance().getConnection();
			st = cn.createStatement();
			cn.setAutoCommit(false);
			try {
				st.executeUpdate(
						"INSERT INTO DEPARTMENTS VALUES (34,'Modeling')");
				Savepoint svpt = cn.setSavepoint("MySavePoint1");
				st.executeUpdate(
						"INSERT INTO DEPARTMENTS VALUES (35,'Managing')");
				cn.rollback(svpt);

				cn.commit();
				System.out.println("committed");
			} catch (SQLException SQLe) {
				SQLe.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
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
