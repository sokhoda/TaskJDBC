package dao.agent;

import java.util.List;

import modelwork.Agent;

public interface IAgentDao {
	long create(final Agent agent);

	Agent read(final long id);

	boolean update(final Agent agent);

	boolean delete(final Agent agent);

	List<Agent> findAll();

	List<Agent> findByName(final String agName);

	List<Agent> findByNamePattern(final String pattern);

	List<Agent> findByType(final int agType);
}
