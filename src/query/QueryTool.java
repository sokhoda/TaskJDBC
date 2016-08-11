package query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbcstart.ConnectionFactory;
import modelwork.Agent;
import modelwork.Cargo;
import modelwork.CargoTree;
import modelwork.Country;
import modelwork.Document;
import modelwork.Journal;
import modelwork.Misc;
import modelwork.Region;
import modelwork.User;
import modelwork.UserType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.agent.MySqlAgentDao;
import dao.cargo.MySqlCargoDao;
import dao.cargotree.MySqlCargoTreeDao;
import dao.country.MySqlCountryDao;
import dao.document.MySqlDocumentDao;
import dao.journal.MySqlJournalDao;
import dao.misc.MySqlMiscDao;
import dao.region.MySqlRegionDao;
import dao.user.MySqlUserDao;
import dao.usertype.MySqlUserTypeDao;

public class QueryTool {
	private static final Logger log = LogManager.getLogger(QueryTool.class
			.getName());

	// @formatter:off
	public static <T> List<T> getSelect(Object dao, final String query, final List params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<T> list = null;
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			ps = QueryTool.getPrepStatement(conn, query, params);
			rs = ps.executeQuery();
			if (rs.next()) {
				list = new ArrayList<>();
				if (dao instanceof MySqlDocumentDao) {
					MySqlDocumentDao.fillEntityFields(rs, (List<Document>)list);
				}else if(dao instanceof MySqlJournalDao) {
					MySqlJournalDao.fillEntityFields(rs, (List<Journal>)list);
				}else if(dao instanceof MySqlCargoDao) {
					MySqlCargoDao.fillEntityFields(rs, ((List<Cargo>)list));
				}else if(dao instanceof MySqlCargoTreeDao) {
					MySqlCargoTreeDao.fillEntityFields(rs, ((List<CargoTree>)list));
				}else if(dao instanceof MySqlAgentDao) {
					MySqlAgentDao.fillEntityFields(rs, ((List<Agent>)list));
				}else if(dao instanceof MySqlMiscDao) {
					MySqlMiscDao.fillEntityFields(rs, ((List<Misc>)list));
				}else if(dao instanceof MySqlRegionDao) {
					MySqlRegionDao.fillEntityFields(rs, ((List<Region>)list));
				}else if(dao instanceof MySqlCountryDao) {
					MySqlCountryDao.fillEntityFields(rs, ((List<Country>)list));
				}else if(dao instanceof MySqlUserDao) {
					MySqlUserDao.fillEntityFields(rs, ((List<User>)list));
				}else if(dao instanceof MySqlUserTypeDao) {
					MySqlUserTypeDao.fillEntityFields(rs, ((List<UserType>)list));
				}else {
					log.warn("getSelect::DaoType not defined!");
				}

			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return list;
	}
	// @formatter:on

	public static long simpleQueryInsert(String query) {
		Connection conn = null;
		Statement stm = null;
		long id = -1;
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			stm = conn.createStatement();
			System.out.println(query);

			stm.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return id;
	}

	public static int getUpdateDelete(String query, List params) {
		int res = 0;
		System.out.println(query);
		try (Connection conn = ConnectionFactory.getInstance().getConnection();
				PreparedStatement ps = QueryTool.getPrepStatement(conn, query,
						params);) {
			res = ps.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage());
		}
		return res;
	}

	public static PreparedStatement getPrepStatement(Connection conn,
			String sql, List params) {
		PreparedStatement ps = null;

		if (conn != null) {
			try {
				ps = conn.prepareStatement(sql);
				if (params != null) {
					int i = 1;
					for (Object obj : params) {
						if (obj instanceof Integer) {
							ps.setInt(i++, (Integer) obj);
						} else if (obj instanceof Long) {
							ps.setLong(i++, (Long) obj);
						} else if (obj instanceof String) {
							ps.setString(i++, (String) obj);
						} else if (obj instanceof Double) {
							ps.setDouble(i++, (Double) obj);
						} else if (obj instanceof Float) {
							ps.setFloat(i++, (Float) obj);
						} else if (obj instanceof Boolean) {
							ps.setBoolean(i++, (Boolean) obj);
						} else if (obj instanceof Date) {
							ps.setDate(i++,
									new java.sql.Date(((Date) obj).getTime()));
						}
					}
				}
			} catch (SQLException e) {
				log.error(e.getMessage());
			}
		}
		return ps;
	}

	public static String getSQLDate(Date date) {
		String res = null;
		if (date != null) {
			res = "'" + new java.sql.Date(date.getTime()) + "'";
		}
		return res;
	}
}
