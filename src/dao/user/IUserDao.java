package dao.user;

import java.util.List;

import modelwork.User;

public interface IUserDao {
	long create(final User user);

	User read(final long id);

	boolean update(final User user);

	boolean delete(final User user);

	List<User> findAll();

	List<User> findByUTypeId(final Long uTypeId);

	List<User> findByUTypeName(final String uTypeName);

	List<User> findByLogin(final String userLogin);

	List<User> findByLoginPattern(final String pattern);

	List<User> findByPassword(final String userPass);

	List<User> findByPasswordPattern(final String pattern);
}
