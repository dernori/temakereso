package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * Returns an account selected by its username
     *
     * @param username
     * @return an account
     */
    @GetMapping(path = "/accounts/{username}")
    public AccountDto getAccount(@PathVariable(name = "username") String username) {
        return accountService.getByUsername(username);
    }

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

    // TODO: DTO!!!
    //    // ------------------------ POST ------------------------- //
    //
    //    /**
    //     * Saves the given account
    //     *
    //     * @param account      to be saved
    //     * @return saved account
    //     */
    //    @PostMapping(path = "/accounts")
    //    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
    //        accountService.createAccount(account);
    //        return new ResponseEntity<>(account, HttpStatus.OK);
    //    }
    //
    //    // ------------------------ PUT -------------------------- //
    //
    //    /**
    //     * Modifies the given account
    //     *
    //     * @param id      of the account to be modified
    //     * @param account to be modified
    //     * @return modified account
    //     */
    //    @PutMapping(path = "/accounts/{username}")
    //    public ResponseEntity<Void> modifyAccount(@PathVariable(name = "username") String username, @RequestBody String email) {
    //        accountService.modifyEmail(username, email);
    //        return new ResponseEntity<>(HttpStatus.OK);
    //    }

}
