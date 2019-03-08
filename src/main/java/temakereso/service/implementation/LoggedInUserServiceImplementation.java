package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import temakereso.helper.AccountDetails;
import temakereso.helper.AccountDto;
import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;
import temakereso.service.AccountService;
import temakereso.service.LoggedInUserService;
import temakereso.service.StudentService;
import temakereso.service.SupervisorService;

@Service
@RequiredArgsConstructor
public class LoggedInUserServiceImplementation implements LoggedInUserService {

    private final SupervisorService supervisorService;

    private final StudentService studentService;

    private final AccountService accountService;

    @Override
    public AccountDto getLoggedInUser() {
        Long id = getLoggedInUserId();
        return accountService.findById(id);
    }

    @Override
    public StudentDto getLoggedInStudent() {
        Long id = getLoggedInUserId();
        return studentService.findByAccountId(id);
    }

    @Override
    public SupervisorDto getLoggedInSupervisor() {
        Long id = getLoggedInUserId();
        return supervisorService.findByAccountId(id);
    }

    private Long getLoggedInUserId() {
        return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }
}
