package temakereso.service;

import temakereso.entity.Account;
import temakereso.helper.AccountDto;

public interface AccountService {

    /**
     * Saves a new account
     *
     * @param account account to be saved
     */
    void createAccount(Account account);

    /**
     * Finds an account by its identifier.
     *
     * @param id identifier
     * @return the account with the given identifier
     */
    Account getById(Long id);

    /**
     * Finds an account by its identifier.
     *
     * @param id identifier
     * @return the account data with the given identifier
     */
    AccountDto findById(Long id);
}