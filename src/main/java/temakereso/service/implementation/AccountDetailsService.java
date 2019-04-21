package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.helper.AccountDetails;
import temakereso.repository.AccountRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            log.error("No user by username: {}", username);
            throw new UsernameNotFoundException(username);
        }
        log.info("user loaded: {}", account);
        return new AccountDetails(account);
    }

}
