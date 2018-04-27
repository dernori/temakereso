package temakereso.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import temakereso.entity.Account;
import temakereso.helper.AccountDetails;
import temakereso.repository.AccountRepository;

@Service
public class AccountDetailsService implements UserDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDetailsService.class);
	
	@Autowired
    private AccountRepository accountRepository;	
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        LOGGER.debug(account.toString());
        return new AccountDetails(account);
    }

}
