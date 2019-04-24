package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.AccountDto;
import temakereso.helper.AccountInputDto;
import temakereso.helper.ForgotPasswordDto;
import temakereso.helper.PasswordChangeDto;
import temakereso.helper.PasswordResetDto;
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

    @GetMapping(path = "/me")
    public AccountDto getLoggedInAccount() {
        return loggedInUserService.getLoggedInUser();
    }

    @GetMapping(path = "/me/student")
    public StudentDto getLoggedInStudent() {
        return loggedInUserService.getLoggedInStudent();
    }

    @GetMapping(path = "/me/supervisor")
    public SupervisorDto getLoggedInSupervisor() {
        return loggedInUserService.getLoggedInSupervisor();
    }

    // ------------------------ POST -------------------------- //

    @PostMapping("/forgot")
    public void forgotPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        accountService.generateForgotPasswordToken(forgotPasswordDto);
    }

    // ------------------------ PUT -------------------------- //

    @PutMapping("/reset")
    public void resetPassword(@RequestBody PasswordResetDto passwordResetDto) {
        accountService.changePassword(passwordResetDto);
    }

    @PutMapping("/accounts/{id}")
    public void modifyAccount(@PathVariable(name = "id") Long id, @RequestBody AccountInputDto accountInputDto) {
        accountService.modifyAccount(id, accountInputDto);
    }

    @PutMapping("/accounts/{id}/password")
    public void changePassword(@PathVariable(name = "id") Long id, @RequestBody PasswordChangeDto passwordChangeDto) {
        accountService.changePassword(id, passwordChangeDto);
    }

}
