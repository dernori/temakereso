package temakereso;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import temakereso.helper.AccountDetails;
import temakereso.service.AccountService;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginListener {

    private final AccountService accountService;

    @EventListener
    public void loginSuccessfulEvent(InteractiveAuthenticationSuccessEvent event) {
        AccountDetails accountDetails = (AccountDetails) event.getAuthentication().getPrincipal();
        log.info("Login successful by: {}", accountDetails.getUsername());
        accountService.setSuccessfulLoginById(accountDetails.getId());
    }

}