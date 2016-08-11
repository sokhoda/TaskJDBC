package dao.agent;

import java.util.Calendar;

import modelwork.Agent;
import dao.factory.MySqlDaoFactory;

public class DaoAgentRunner {
	public static void main(String[] args) {

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

		Calendar cal = Calendar.getInstance();
		cal.set(2011, 11, 2);
		Agent agent = new Agent(13L, "Petro1", 2, "my address",
				"091 112 45 77", "custom@gmail.com", "XX-112-554",
				"agent.com.ua", "Lithuania", cal.getTime(), "myTag");
		// BaseDaoFactory baseFactory =
		// DaoFactory.getFactory(DriverTypes.MYSQL);
		// IAgentDao agentDao = baseFactory.getAgentDao();
		IAgentDao atDao = MySqlDaoFactory.getAgentDao();

		// System.out.println(atDao.create(agent));

		System.out.println(atDao.read(10));
		atDao.update(agent);
		atDao.delete(agent);
		System.out.println(atDao.findAll());
		System.out.println(atDao.findByNamePattern("%pet%"));
		System.out.println(atDao.findByName("myko"));
		System.out.println(atDao.findByType(2));

	}
}
