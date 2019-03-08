package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.helper.AccountDto;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;

@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public void createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountRepository.save(account);
    }

    @Override
    public AccountDto getByUsername(String username) {
        return modelMapper.map(accountRepository.findByUsername(username), AccountDto.class);
    }

    @Override
    public void modifyEmail(String username, String email) {
        Account savedAccount = accountRepository.findByUsername(username);
        savedAccount.setEmail(email);
        accountRepository.save(savedAccount);
    }

    @Override
    public AccountDto findById(Long id) {
        Account account = accountRepository.findOne(id);
        return account != null ? modelMapper.map(account, AccountDto.class) : null;
    }

}
