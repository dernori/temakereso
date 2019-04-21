package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.helper.AccountDto;
import temakereso.helper.Constants;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public void createAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            log.error("User already exists by username: {}", account.getUsername());
            throw new IllegalArgumentException(Constants.DUPLICATED_USERNAME);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
        log.info("Account created with username: {}", account.getUsername());
    }

    @Override
    public Account getById(Long id) {
        return accountRepository.findOne(id);
    }

    @Override
    public AccountDto findById(Long id) {
        Account account = accountRepository.findOne(id);
        return modelMapper.map(account, AccountDto.class);
    }

}
