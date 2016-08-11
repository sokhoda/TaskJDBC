package dao.cargotree;

import java.util.List;

import modelwork.CargoTree;

public interface ICargoTreeDao {
	long create(final CargoTree cargoTree);

	CargoTree read(final long id);

	boolean update(final CargoTree cargoTree);

	boolean delete(final CargoTree cargoTree);

	List<CargoTree> findAll();

	List<CargoTree> findByCargoId(final Long cargId);

}
