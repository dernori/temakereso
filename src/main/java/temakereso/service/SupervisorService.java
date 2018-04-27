package temakereso.service;

import java.util.List;

import temakereso.entity.Supervisor;

public interface SupervisorService {
	
	/**
	 * Returns all the supervisors
	 * 
	 * @return a list of supervisors
	 */
	List<Supervisor> getAll();
	
	/**
	 * Returns a supervisor specified by its id
	 * 
	 * @param id
	 * @return the selected supervisor
	 */
	Supervisor getOneById(Long id);
	
	/**
	 * Creates a new supervisor
	 * 
	 * @param supervisor
	 * @return the saved supervisor
	 */
	Supervisor createSupervisor(Supervisor supervisor);
	
	/**
	 * Modifies a supervisor
	 * 
	 * @param supervisor
	 * @return the modified supervisor
	 */
	Supervisor modifySupervisor(Supervisor supervisor);

	/**
	 * Returns a supervisor specified by its username
	 * 
	 * @param username
	 * @return the selected supervisor
	 */
	Supervisor getByUsername(String username);

	/**
	 * Returns a list of unconfirmed supervisors
	 * 
	 * @return unconfirmed supervisors
	 */
	List<Supervisor> getUnconfirmed();

	/**
	 * Confirms a supervisor
	 * @param id of the supervisor
	 */
	void confirm(Long id);

}
