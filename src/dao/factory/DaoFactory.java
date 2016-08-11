package dao.factory;

public class DaoFactory {
	public static BaseDaoFactory getFactory(DriverTypes type) {
		BaseDaoFactory res = null;
		switch (type) {
		case MYSQL:
			res = new MySqlFactory();
		case ORACLE:
			// res = new OracleFactory();
			res = null;
		}
		return res;
	}
}
