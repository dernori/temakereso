package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.MailDto;
import temakereso.service.AccountService;
import temakereso.service.LoggedInUserService;
import temakereso.service.MailSenderService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class MailController {

    private final MailSenderService mailSenderService;

    private final AccountService accountService;

    private final LoggedInUserService loggedInUserService;

    // ------------------------ POST -------------------------- //

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @PostMapping(path = "/mail")
    public void sendMail(@RequestBody MailDto mailDto) {
        mailSenderService.sendMail(
                accountService.getById(loggedInUserService.getLoggedInUser().getId()),
                accountService.getById(mailDto.getTo()),
                mailDto.getSubject(),
                mailDto.getBody());
    }

}
