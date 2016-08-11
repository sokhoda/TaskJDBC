package dao.factory;

import dao.agent.IAgentDao;

public abstract class BaseDaoFactory {
	public abstract IAgentDao getAgentDao();

	// public abstract ICountryDao getCountryDao();

	// public abstract IDocumentDao getDocumentDao();
	//
	// public abstract IJournalDao getJournalDao();
	//
	// public abstract IMiscDao getMiscDao();
	//
	// public abstract IRegionDao getRegionDao();
	//
	// public abstract IUserDao getUserDao();
	//
	// public abstract IUserTypeDao getUserTypeDao();
}
