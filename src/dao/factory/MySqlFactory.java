package dao.factory;

import dao.agent.IAgentDao;
import dao.agent.MySqlAgentDao;

public class MySqlFactory extends BaseDaoFactory {

	@Override
	public IAgentDao getAgentDao() {
		return new MySqlAgentDao();
	}

	// @Override
	// public ICountryDao getCountryDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IDocumentDao getDocumentDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IJournalDao getJournalDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IMiscDao getMiscDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IRegionDao getRegionDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IUserDao getUserDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }
	//
	// @Override
	// public IUserTypeDao getUserTypeDao() {
	// // TODO Auto-generated method stub
	// return null;
	// }

}
