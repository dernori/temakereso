package temakereso.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import temakereso.entity.Account;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;

@Service
public class AccountServiceImplementation implements AccountService {
	
	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

    @Override
	public void createAccount(Account account){
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

	@Override
	public Account getByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public void modifyEmail(String username, String email) {
		Account savedAccount = accountRepository.findByUsername(username);
		savedAccount.setEmail(email);
		accountRepository.save(savedAccount);
	}
    
}
