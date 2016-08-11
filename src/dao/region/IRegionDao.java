package dao.region;

import java.util.List;

import modelwork.Region;

public interface IRegionDao {
	long create(final Region region);

	Region read(final long id);

	boolean update(final Region region);

	boolean delete(final Region region);

	List<Region> findAll();

	List<Region> findByCountryId(final int cntrId);

	List<Region> findByName(final String cntrName);

	List<Region> findByNamePattern(final String pattern);

	List<Region> findByTag(final String cntrTag);
}
