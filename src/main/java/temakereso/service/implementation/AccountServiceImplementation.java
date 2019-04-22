package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.entity.Role;
import temakereso.helper.AccountDto;
import temakereso.helper.Constants;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;
import temakereso.service.RoleService;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    @Override
    public void createAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            log.error("User already exists by username: {}", account.getUsername());
            throw new IllegalArgumentException(Constants.DUPLICATED_USERNAME);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setCreationDate(new Date());
        account.setDeleted(false);
        accountRepository.save(account);
        log.info("Account created with username: {}", account.getUsername());
    }

    @Override
    public void modifyAccount(Account account) {
        accountRepository.save(account);
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

    @Override
    public void setSuccessfulLoginById(Long id) {
        Account account = getById(id);
        account.setLastSuccessfulLogin(new Date());
        accountRepository.save(account);
    }

    @Override
    public List<Account> findAdministrators() {
        Role adminRole = roleService.findByName("ADMIN");
        return accountRepository.findByRolesContains(adminRole);
    }

    @Override
    public List<Account> findStudentsToRemind(Date time) {
        Role studentRole = roleService.findByName("STUDENT");
        return accountRepository.findByRolesContainsAndLastSuccessfulLogin(studentRole, time);
    }

    @Override
    public List<Account> findSupervisorsToRemind(Date time) {
        Role supervisorRole = roleService.findByName("SUPERVISOR");
        return accountRepository.findByRolesContainsAndLastSuccessfulLogin(supervisorRole, time);
    }

    @Override
    public void archiveAccount(Account account) {
        account.setUsername("archive-" + account.getUsername());
        account.setDeleted(true);
        accountRepository.save(account);
    }

}
