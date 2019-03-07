package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import temakereso.helper.AccountDetails;
import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;
import temakereso.service.LoggedInUserService;
import temakereso.service.StudentService;
import temakereso.service.SupervisorService;

@Service
@RequiredArgsConstructor
public class LoggedInUserServiceImplementation implements LoggedInUserService {

    private final SupervisorService supervisorService;

    private final StudentService studentService;

    @Override
    public Long getLoggedInUser() {
        return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    @Override
    public StudentDto getLoggedInStudent() {
        Long id = ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return studentService.findByAccountId(id);
    }

    @Override
    public SupervisorDto getLoggedInSupervisor() {
        Long id = ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return supervisorService.findByAccountId(id);
    }
}
