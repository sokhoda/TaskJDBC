package dao.usertype;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import modelwork.UserType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import query.QueryTool;

public class MySqlUserTypeDao implements IUserTypeDao {
	private static final Logger log = LogManager
			.getLogger(MySqlUserTypeDao.class.getName());
	private static final String INSERT = "INSERT INTO USER_TYPES VALUES(0, ";
	private static final String UPDATE = "UPDATE USER_TYPES SET ";
	private static final String DELETE = "DELETE FROM USER_TYPES ";
	private static final String SELECTALL = "SELECT * FROM USER_TYPES ";

	// @formatter:off
	@Override
	public long create(final UserType uType) {
		final String query = INSERT +
				uType.getUTypeCode() + "," +
				"'" + uType.getUTypeName() +"')";
		return QueryTool.simpleQueryInsert(query);
	}
	// @formatter:on

	@Override
	public UserType read(final long id) {
		final List<UserType> list = QueryTool.getSelect(this, SELECTALL
				+ "WHERE uTypeId = " + id, null);
		return list != null ? list.get(0) : null;
	}

	// @formatter:off
	@Override
	public boolean update(final UserType uType) {
		final String query = UPDATE +
				"uTypeCode = '" + uType.getUTypeCode() + "',"
				+ "uTypeName = '" + uType.getUTypeName() + "'"
				+ " \n WHERE uTypeId = " + uType.getUTypeId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}
	// @formatter:on
	@Override
	public boolean delete(final UserType uType) {
		final String query = DELETE + "WHERE uTypeId = " + uType.getUTypeId();
		return QueryTool.getUpdateDelete(query, null) != 0 ? true : false;
	}

	@Override
	public List<UserType> findAll() {
		return QueryTool.getSelect(this, SELECTALL, null);
	}

	@Override
	public List<UserType> findByName(final String uTypeName) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE uTypeName = '"
				+ uTypeName + "'", null);
	}

	@Override
	public List<UserType> findByNamePattern(final String pattern) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE uTypeName LIKE '"
				+ pattern + "'", null);
	}

	@Override
	public List<UserType> findByCode(int uTypeCode) {
		return QueryTool.getSelect(this, SELECTALL + " WHERE uTypeCode = "
				+ uTypeCode, null);
	}

	// 	@formatter:off
	public static void fillEntityFields(ResultSet rs, List<UserType> list) throws SQLException {
		do {
			list.add(new UserType(rs.getLong(1),
					rs.getInt(2),
					rs.getString(3)));
		} while (rs.next());
	}
	// @formatter:on

}
