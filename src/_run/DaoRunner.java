package _run;

import dao.agent.IAgentDao;
import dao.factory.BaseDaoFactory;
import dao.factory.DaoFactory;
import dao.factory.DriverTypes;

public class DaoRunner {
	public static void main(String[] args) {

		// Calendar cal = Calendar.getInstance();
		// cal.set(2016, 10, 2);
		// Agent agent = new Agent("my address", "Ukraine", cal.getTime(),
		// "custom@gmail.com", "Alex", "XX-112-554", "095 124 55 77",
		// "myTag", 1, "agent.com.ua");
		BaseDaoFactory baseFactory = DaoFactory.getFactory(DriverTypes.MYSQL);
		IAgentDao agentDao = baseFactory.getAgentDao();
		// System.out.println(agentDao.create(agent));

		System.out.println(agentDao.read(1));

	}

	// public static void main(String[] args) {

	// Connection con = null;
	// try {
	// con = ConnectionFactory.getInstance().getConnection();
	// Statement st = con.createStatement();
	// String query =
	// "INSERT INTO COUNTRIES(cntrName, cntrTag) VALUES ('Ukraine','1')";
	// st.executeUpdate(query);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } finally {
	// if (con != null) {
	// try {
	// con.close();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }
	// }

	// }

}
