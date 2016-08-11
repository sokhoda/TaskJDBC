package dao.region;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modelwork.Region;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import query.QueryTool;

public class MySqlRegionDao implements IRegionDao {
	private static final Logger log = LogManager.getLogger(MySqlRegionDao.class
			.getName());
	private static final String INSERT = "INSERT INTO REGIONS VALUES(0, ";
	private static final String UPDATE = "UPDATE REGIONS SET ";
	private static final String DELETE = "DELETE FROM REGIONS ";
	private static final String SELECTALL = "SELECT * FROM REGIONS ";

	// @formatter:off
	@Override
	public long create(final Region region) {
		final String query = INSERT +
				region.getCntrId() +"," +
				"'" + region.getRegName() +"'," +
				"'" + region.getRegTag() + "')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public Region read(final long id) {
		final List<Region> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE regId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final Region region) {
		final String query = UPDATE +
				"cntrId = '" + region.getCntrId() + "',"
				+ "regName = '" + region.getRegName() + "',"
				+ "regTag = '" + region.getRegTag() + "' \n WHERE regId =" + region.getRegId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final Region region) {
		final String query = DELETE + "WHERE regId = " + region.getRegId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<Region> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<Region> findByCountryId(int cntrId) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE cntrID = "
				+ cntrId, null);
	}

	@Override
	public List<Region> findByName(final String regName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE regName = '"
				+ regName + "'", null);
	}

	@Override
	public List<Region> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE regName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<Region> findByTag(String regTag) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE regTag = '"
				+ regTag + "'", null);
	}

	// 	@formatter:off
	public static void fillEntityFields(ResultSet rs, List<Region> list) throws SQLException {
		do {
			list.add(new Region(rs.getLong(1),
					rs.getLong(2),
					rs.getString(3),
					rs.getString(4)));
		} while (rs.next());
	}
	// @formatter:on

}
