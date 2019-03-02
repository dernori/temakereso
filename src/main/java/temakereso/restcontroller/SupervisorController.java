package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.entity.Supervisor;
import temakereso.helper.SupervisorDto;
import temakereso.service.SupervisorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class SupervisorController {

    private final SupervisorService supervisorService;

    // ------------------------ GET -------------------------- //

    /**
     * Returns supervisors <br>
     * If filters are not given all the supervisors will be returned
     *
     * @return a page of supervisors
     */
    @GetMapping(path = "/supervisors")
    public List<SupervisorDto> getSupervisors() {
        return supervisorService.getAll();
    }

    /**
     * Returns a supervisor selected by its id
     *
     * @param id
     * @return a supervisor
     */
    @GetMapping(path = "/supervisors/{id}")
    public SupervisorDto getSupervisor(@PathVariable(name = "id") Long id) {
        return supervisorService.getOneById(id);
    }

    /**
     * Returns the unconfirmed supervisors
     *
     * @return a list of supervisors
     */
    @GetMapping(path = "/supervisors/unconfirmed")
    public List<SupervisorDto> getUnconfirmedSupervisors() {
        return supervisorService.getUnconfirmed();
    }

    /**
     * Returns an account selected by its username
     * TODO: change to accound ID!!
     *
     * @param username
     * @return a supervisor
     */
    @GetMapping(path = "/supervisors/byusername/{username}")
    public SupervisorDto getSupervisorByUsername(@PathVariable(name = "username") String username) {
        return supervisorService.getByUsername(username);
    }

    // ------------------------ POST ------------------------- //

    /**
     * Saves the given supervisor
     *
     * @param supervisor to be saved
     * @return saved supervisor
     */
    @PostMapping(path = "/supervisors")
    public SupervisorDto createSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }

    // ------------------------ PUT -------------------------- //

    /**
     * Modifies the given supervisor
     *
     * @param supervisor to be modified
     * @return modified supervisor
     */
    @PutMapping(path = "/supervisors")
    public SupervisorDto modifySupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.modifySupervisor(supervisor);
    }

    /**
     * Comfirms the supervisor selected by its id
     *
     * @param id
     */
    @PutMapping(path = "/supervisors/{id}/confirm")
    public void confirmSupervisor(@PathVariable(name = "id") Long id) {
        supervisorService.confirm(id);
    }

}
