package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.entity.Role;
import temakereso.helper.AccountDetails;
import temakereso.helper.AccountDto;
import temakereso.helper.AccountInputDto;
import temakereso.helper.Constants;
import temakereso.helper.ForgotPasswordDto;
import temakereso.helper.PasswordChangeDto;
import temakereso.helper.PasswordResetDto;
import temakereso.repository.AccountRepository;
import temakereso.service.AccountService;
import temakereso.service.MailService;
import temakereso.service.RoleService;
import temakereso.service.StudentService;
import temakereso.service.SupervisorService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImplementation implements AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    private final MailService mailService;

    private final StudentService studentService;

    private final SupervisorService supervisorService;

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
    public void archiveAccount(Account account) {
        account.setUsername("archive-" + account.getUsername());
        account.setDeleted(true);
        accountRepository.save(account);
    }

    @Override
    public void generateForgotPasswordToken(ForgotPasswordDto forgotPasswordDto) {
        Account account = accountRepository.findByUsername(forgotPasswordDto.getUsername());
        if (account == null) {
            log.error("User does not exist by username: {}", forgotPasswordDto.getUsername());
            throw new IllegalArgumentException(Constants.ACCOUNT_NOT_EXISTS);
        }
        String token = UUID.randomUUID().toString();
        account.setToken(token);
        accountRepository.save(account);
        mailService.sendResetToken(account, token);
    }

    @Override
    public void changePassword(PasswordResetDto passwordResetDto) {
        Account account = accountRepository.findByToken(passwordResetDto.getToken());
        if (account == null) {
            log.error("Account could not be found by token: {}", passwordResetDto.getToken());
            throw new IllegalArgumentException(Constants.ACCOUNT_NOT_EXISTS);
        }
        account.setToken(null);
        account.setPassword(passwordEncoder.encode(passwordResetDto.getPassword()));
        accountRepository.save(account);
        log.info("Password reset for account: {}", account.getUsername());
    }

    @Override
    public void changePassword(Long id, PasswordChangeDto passwordChangeDto) {
        if (getLoggedInUserId() != id) {
            log.error("Account ids are not the same");
            throw new IllegalArgumentException();
        }
        Account account = getById(id);
        //        if (account.getPassword() != passwordEncoder.encode(passwordChangeDto.getCurrent())) {
        //            log.error("Current password is different");
        //            throw new IllegalArgumentException(Constants.CURRENT_PASSWORD_BAD);
        //        }
        account.setPassword(passwordEncoder.encode(passwordChangeDto.getPassword()));
        accountRepository.save(account);
        log.info("Password changed for account: {}", account.getUsername());
    }

    @Override
    public void modifyAccount(Long id, AccountInputDto accountInputDto) {
        if (getLoggedInUserId() != id) {
            log.error("Account ids are not the same");
            throw new IllegalArgumentException();
        }
        Account account = getById(id);
        account.setEmail(accountInputDto.getEmail());
        account.setName(accountInputDto.getName());
        if (account.getRoles().stream().anyMatch(role -> role.getName() == "STUDENT")) {
            studentService.modifyNameByAccountId(id, accountInputDto.getName());
        }
        if (account.getRoles().stream().anyMatch(role -> role.getName() == "SUPERVISOR")) {
            supervisorService.modifyNameByAccountId(id, accountInputDto.getName());
        }
        accountRepository.save(account);
    }

    private Long getLoggedInUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }
        return null;
    }

}
