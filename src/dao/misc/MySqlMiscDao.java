package dao.misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import modelwork.Misc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import query.QueryTool;

public class MySqlMiscDao implements IMiscDao {
	private static final Logger log = LogManager.getLogger(MySqlMiscDao.class
			.getName());
	private static final String INSERT = "INSERT INTO MISC VALUES(0, ";
	private static final String UPDATE = "UPDATE MISC SET ";
	private static final String DELETE = "DELETE FROM MISC ";
	private static final String SELECTALL = "SELECT * FROM MISC ";

	// @formatter:off
	@Override
	public long create(final Misc misc) {
		final String query = INSERT +
				misc.getRegId() + "," +
				misc.getMscNo() + "," +
				"'" + misc.getMscName() +"'," +
				"'" + misc.getMscTag() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Misc read(final long id) {
		final List<Misc> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE mscId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Misc misc) {
		final String query = UPDATE +
				"regId = '" + misc.getRegId() + "',"
				+ "mscNo = '" + misc.getMscNo() + "',"
				+ "mscName = '" + misc.getMscName() + "',"
				+ "mscTag = '" + misc.getMscTag() + "' \n WHERE mscId =" + misc.getMscId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final Misc misc) {
		final String query = DELETE + "WHERE mscId = " + misc.getMscId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Misc> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Misc> findByCountryId(Long cntrId) {
		return QueryTool
				.getSelect(
						this,
						SELECTALL
								+ " WHERE regId IN (SELECT regId FROM Regions WHERE cntrId = ?)",
						Arrays.asList(cntrId));
	}

	@Override
	public List<Misc> findByRegionId(Long regId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE regId = " + regId,
				null);
	}

	@Override
	public List<Misc> findByName(final String mscName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mscName = '"
				+ mscName + "'", null);
	}

	@Override
	public List<Misc> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mscName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Misc> findByTag(String mscTag) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE mscTag = '"
				+ mscTag + "'", null);
	}

	// 	@formatter:off
	public static void fillEntityFields(ResultSet rs, List<Misc> list) throws SQLException {
		do {
			list.add(new Misc(rs.getLong(1),
					rs.getLong(2),
					rs.getInt(3),
					rs.getString(4),
					rs.getString(5)));
		} while (rs.next());
	}
	// @formatter:on

}
