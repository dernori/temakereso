package temakereso.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import temakereso.entity.Account;
import temakereso.service.AccountService;

@RestController
@RequestMapping(value = "/api")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	// ------------------------ GET -------------------------- //
	
	/**
	 * Returns an account selected by its id
	 * 
	 * @param id
	 * @return a account
	 */
	@GetMapping(path = "/accounts/{username}")
	public ResponseEntity<Account> getAccount(@PathVariable(name = "username") String username) {
		Account account = accountService.getByUsername(username);
		account.setPassword(null);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	// ------------------------ POST ------------------------- //
	
	/**
	 * Saves the given account
	 * 
	 * @param supervisorId id of the supervisor
	 * @param account to be saved
	 * @return saved account
	 */
	@PostMapping(path = "/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account) {
		accountService.createAccount(account);
		return new ResponseEntity<>(account, HttpStatus.OK);
	}
	
	// ------------------------ PUT -------------------------- //
	
	/**
	 * Modifies the given account
	 * 
	 * @param id of the account to be modified
	 * @param account to be modified
	 * @return modified account
	 */
	@PutMapping(path = "/accounts/{username}")
	public ResponseEntity<Void> modifyAccount(@PathVariable(name = "username") String username, @RequestBody String email) {
		accountService.modifyEmail(username, email);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
