package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.AccountDto;
import temakereso.helper.StudentDto;
import temakereso.helper.SupervisorDto;
import temakereso.service.AccountService;
import temakereso.service.LoggedInUserService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class AccountController {

    private final AccountService accountService;

    private final LoggedInUserService loggedInUserService;

    // ------------------------ GET -------------------------- //

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(path = "/me")
    public AccountDto getLoggedInAccount() {
        return loggedInUserService.getLoggedInUser();
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(path = "/me/student")
    public StudentDto getLoggedInStudent() {
        return loggedInUserService.getLoggedInStudent();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(path = "/me/supervisor")
    public SupervisorDto getLoggedInSupervisor() {
        return loggedInUserService.getLoggedInSupervisor();
    }

}
