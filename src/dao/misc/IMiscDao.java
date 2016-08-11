package dao.misc;

import java.util.List;

import modelwork.Misc;

public interface IMiscDao {
	long create(final Misc misc);

	Misc read(final long id);

	boolean update(final Misc misc);

	boolean delete(final Misc misc);

	List<Misc> findAll();

	List<Misc> findByCountryId(final Long cntrId);

	List<Misc> findByRegionId(final Long regId);

	List<Misc> findByName(final String cntrName);

	List<Misc> findByNamePattern(final String pattern);

	List<Misc> findByTag(final String cntrTag);
}
