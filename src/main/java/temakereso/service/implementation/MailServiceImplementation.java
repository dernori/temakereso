package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.MailDto;
import temakereso.service.AccountService;
import temakereso.service.LoggedInUserService;
import temakereso.service.MailSenderService;
import temakereso.service.MailService;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImplementation implements MailService {

    private final MailSenderService mailSenderService;

    private final LoggedInUserService loggedInUserService;

    private final AccountService accountService;

    @Override
    public void studentApplied(Student student, Topic topic) {
        String subject = "Témára jelentkezés történt hallgató által";
        String body = new StringBuilder()
                .append("<p>A témára való jelentkezés sikeresen megtörtént az alábbiaknak megfelelően:</p>")
                .append("<br/>")
                .append("<p><strong>Hallgató:</strong> " + student.getName() + "</p>")
                .append("<p><strong>Téma:</strong> " + topic.getName() + "</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        mailSenderService.sendMail(student.getAccount(), topic.getSupervisor().getAccount(), subject, body);
        mailSenderService.sendMail(topic.getSupervisor().getAccount(), student.getAccount(), subject, body);
    }

    @Override
    public void studentAccepted(Student student, Topic topic) {
        String subject = "Témára való jelentkezését elfogadták";
        String body = new StringBuilder()
                .append("<p>A témára való jelentkezését a témavezető jóváhagyta.</p>")
                .append("<br/>")
                .append("<p><strong>Téma:</strong> " + topic.getName() + "</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        mailSenderService.sendMail(topic.getSupervisor().getAccount(), student.getAccount(), subject, body);
    }

    @Override
    public void applicationCleared(Student student, Topic topic) {
        String subject = "Témára való jelentkezése törlésre került";
        String body = new StringBuilder()
                .append("<p>A témára való jelentkezése törlésre került.</p>")
                .append("<br/>")
                .append("<p><strong>Téma:</strong> " + topic.getName() + "</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        mailSenderService.sendMail(student.getAccount(), subject, body);
    }

    @Override
    public void applicationCleared(Set<Student> students, Topic topic) {
        for (Student student : students) {
            applicationCleared(student, topic);
        }
    }

    @Override
    public void sendSimpleMail(MailDto mailDto) {
        mailSenderService.sendMail(
                accountService.getById(loggedInUserService.getLoggedInUser().getId()),
                accountService.getById(mailDto.getTo()),
                mailDto.getSubject(),
                mailDto.getBody());
    }

}
