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
     * Returns a supervisor specified by its identifier
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

    /**
     * Finds a supervisor with its identifier.
     *
     * @param supervisorId identifier of supervisor
     * @return supervisor
     */
    Supervisor findOneById(Long supervisorId);

    /**
     * Finds a supervisor with the given account identifier.
     *
     * @param accountId identifier of account
     * @return supervisor data
     */
    SupervisorDto findByAccountId(Long accountId);
}
