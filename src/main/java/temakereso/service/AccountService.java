package temakereso.service;

import temakereso.entity.Account;
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

    /**
     * TODO
     */
    void modifyEmail(String username, String email);

    AccountDto findById(Long id);
}