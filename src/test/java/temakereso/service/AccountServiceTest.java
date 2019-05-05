package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Account;
import temakereso.helper.ForgotPasswordDto;
import temakereso.helper.PasswordResetDto;
import temakereso.repository.AccountRepository;
import temakereso.service.implementation.AccountServiceImplementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static Account account;

    @InjectMocks
    private AccountServiceImplementation accountService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        account = new Account();
        account.setId(1L);
        account.setUsername("username");

        when(accountRepository.exists(any(Long.class))).thenReturn(true);
        when(accountRepository.findByToken(any(String.class))).thenReturn(null);
        when(accountRepository.findByUsername(any(String.class))).thenReturn(null);
        when(accountRepository.existsByUsername(any(String.class))).thenReturn(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenAccountWithAlreadyExistingUsername_thenThrowsException() {
        accountService.createAccount(account);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingAccount_whenGenerateNewPassword_thenThrowsException() {
        accountService.generateForgotPasswordToken(new ForgotPasswordDto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingAccount_whenChangingPassword_thenThrowsException() {
        accountService.changePassword(new PasswordResetDto());
    }

}
