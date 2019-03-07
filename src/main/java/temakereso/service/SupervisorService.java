package temakereso.service;

import temakereso.entity.Supervisor;
import temakereso.helper.SupervisorDto;

import java.util.List;

public interface SupervisorService {

    /**
     * Returns all the supervisors
     *
     * @return a list of supervisors
     */
    List<SupervisorDto> getAll();

    /**
     * Returns a supervisor specified by its id
     *
     * @param id
     * @return the selected supervisor
     */
    SupervisorDto getOneById(Long id);

    /**
     * Creates a new supervisor
     *
     * @param supervisor
     * @return the saved supervisor
     */
    SupervisorDto createSupervisor(Supervisor supervisor);

    /**
     * Modifies a supervisor
     *
     * @param supervisor
     * @return the modified supervisor
     */
    SupervisorDto modifySupervisor(Supervisor supervisor);

    /**
     * Returns a supervisor specified by its username
     *
     * @param username
     * @return the selected supervisor
     */
    SupervisorDto getByUsername(String username);

    /**
     * Returns a list of unconfirmed supervisors
     *
     * @return unconfirmed supervisors
     */
    List<SupervisorDto> getUnconfirmed();

    /**
     * Confirms a supervisor
     *
     * @param id of the supervisor
     */
    void confirm(Long id);

    Supervisor findOneById(Long supervisorId);

    SupervisorDto findByAccountId(Long accountId);
}
