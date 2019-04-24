package temakereso.service;

import temakereso.entity.Account;
import temakereso.helper.AccountDto;
import temakereso.helper.AccountInputDto;
import temakereso.helper.ForgotPasswordDto;
import temakereso.helper.PasswordChangeDto;
import temakereso.helper.PasswordResetDto;

import java.util.Date;
import java.util.List;

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

    /**
     * After a successful login, last successful login date should be changed.
     *
     * @param id id of account
     */
    void setSuccessfulLoginById(Long id);

    /**
     * Finds the accounts with administrator roles.
     *
     * @return list of accounts
     */
    List<Account> findAdministrators();

    List<Account> findStudentsToRemind(Date time);

    List<Account> findSupervisorsToRemind(Date time);

    void archiveAccount(Account account);

    void generateForgotPasswordToken(ForgotPasswordDto forgotPasswordDto);

    void changePassword(PasswordResetDto passwordResetDto);

    void changePassword(Long id, PasswordChangeDto passwordChangeDto);

    void modifyAccount(Long id, AccountInputDto accountInputDto);
}