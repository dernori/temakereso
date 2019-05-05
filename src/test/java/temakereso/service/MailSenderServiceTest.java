package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Account;
import temakereso.service.implementation.MailSenderServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class MailSenderServiceTest {

    private static Account fromAccount;
    private static Account toAccount;

    @InjectMocks
    private MailSenderServiceImplementation mailSenderService;

    @Before
    public void setUp() {
        fromAccount = new Account();
        fromAccount.setUsername("from username");

        toAccount = new Account();
        toAccount.setUsername("to username");
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingUsername_thenThrowsException() {
        mailSenderService.sendMail(fromAccount, toAccount, "subject", "body");
    }

}
