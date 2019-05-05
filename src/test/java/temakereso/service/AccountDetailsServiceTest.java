package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import temakereso.repository.AccountRepository;
import temakereso.service.implementation.AccountDetailsService;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountDetailsServiceTest {

    @InjectMocks
    private AccountDetailsService accountDetailsService;

    @Mock
    private AccountRepository accountRepository;

    @Before
    public void setUp() {
        when(accountRepository.findByUsername(any(String.class))).thenReturn(null);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void givenNonExistingUsername_thenThrowsException() {
        accountDetailsService.loadUserByUsername("username");
    }

}
