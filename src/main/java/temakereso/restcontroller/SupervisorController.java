package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(path = "/supervisors")
    public List<SupervisorDto> getSupervisors() {
        return supervisorService.getAll();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/supervisors/unconfirmed")
    public List<SupervisorDto> getUnconfirmedSupervisors() {
        return supervisorService.getUnconfirmed();
    }

    // ------------------------ POST ------------------------- //

    @PostMapping(path = "/supervisors")
    public SupervisorDto createSupervisor(@RequestBody Supervisor supervisor) {
        return supervisorService.createSupervisor(supervisor);
    }

    // ------------------------ PUT -------------------------- //

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/supervisors/{id}/confirm")
    public void confirmSupervisor(@PathVariable(name = "id") Long id) {
        supervisorService.confirm(id);
    }

}
