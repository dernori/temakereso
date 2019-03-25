package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.MailDto;
import temakereso.service.MailService;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class MailController {

    private final MailService mailService;

    // ------------------------ POST -------------------------- //

    @PostMapping(path = "/mail")
    public void sendMail(@RequestBody MailDto mailDto) {
        mailService.sendSimpleMail(mailDto);
    }

}
