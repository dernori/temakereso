package temakereso.service;

import temakereso.entity.Account;

public interface AccountService {

	/**
	 * Saves a new account
	 * 
	 * @param Account account to be saved
	 */
	void createAccount(Account account);
	
	/**
	 * Returns the account by its username
	 * 
	 * @param username
	 * @return Account
	 */
	Account getByUsername(String username);

	/**
	 * Modifies the given account's email address
	 * 
	 * @param Account account to be modified
	 */
	void modifyEmail(String username, String email);

}