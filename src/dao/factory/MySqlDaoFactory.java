package dao.factory;

import dao.agent.IAgentDao;
import dao.agent.MySqlAgentDao;
import dao.cargo.ICargoDao;
import dao.cargo.MySqlCargoDao;
import dao.cargotree.ICargoTreeDao;
import dao.cargotree.MySqlCargoTreeDao;
import dao.country.ICountryDao;
import dao.country.MySqlCountryDao;
import dao.document.IDocumentDao;
import dao.document.MySqlDocumentDao;
import dao.journal.IJournalDao;
import dao.journal.MySqlJournalDao;
import dao.misc.IMiscDao;
import dao.misc.MySqlMiscDao;
import dao.region.IRegionDao;
import dao.region.MySqlRegionDao;
import dao.user.IUserDao;
import dao.user.MySqlUserDao;
import dao.usertype.IUserTypeDao;
import dao.usertype.MySqlUserTypeDao;

public class MySqlDaoFactory {

	public static IAgentDao getAgentDao() {
		return new MySqlAgentDao();
	}

	public static ICountryDao getCountryDao() {
		return new MySqlCountryDao();
	}

	public static IDocumentDao getDocumentDao() {
		return new MySqlDocumentDao();
	}

	public static IJournalDao getJournalDao() {
		return new MySqlJournalDao();
	}

	public static IMiscDao getMiscDao() {
		return new MySqlMiscDao();
	}

	public static IRegionDao getRegionDao() {
		return new MySqlRegionDao();
	}

	public static IUserDao getUserDao() {
		return new MySqlUserDao();
	}

	public static IUserTypeDao getUserTypeDao() {
		return new MySqlUserTypeDao();
	}

	public static ICargoDao getCargoDao() {
		return new MySqlCargoDao();
	}

	public static ICargoTreeDao getCargoTreeDao() {
		return new MySqlCargoTreeDao();
	}
}
