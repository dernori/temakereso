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
     * TODO
     */
    void modifyEmail(String username, String email);

    Account getById(Long id);

    AccountDto findById(Long id);
}