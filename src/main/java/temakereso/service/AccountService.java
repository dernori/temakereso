package temakereso.service;

import temakereso.entity.Account;
import temakereso.entity.Topic;
import temakereso.helper.AccountDto;

public interface AccountService {

	/**
	 * Saves a new account
	 * 
	 * @param account to be saved
	 */
	void createAccount(Account account);
	
	/**
	 * Returns the account by its username
	 * 
	 * @param username
	 * @return Account
	 */
    AccountDto getByUsername(String username);

	// no one uses?
	/**
	 * TODO
	 */
	void modifyEmail(String username, String email);

	// no one uses?
    Account getOneById(Long id);
}