package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.MailDto;
import temakereso.service.AccountService;
import temakereso.service.LoggedInUserService;
import temakereso.service.MailSenderService;
import temakereso.service.MailService;

import java.util.List;
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
    public void supervisorRegistered() {
        List<Account> administrators = accountService.findAdministrators();

        String subject = "Új felhasználó regisztrált témavezetőként";
        String body = new StringBuilder()
                .append("<p>Az új témavezetőt hitelesíteni szükséges.</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        for (Account administrator : administrators) {
            mailSenderService.sendMail(administrator, subject, body);
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

    @Override
    public void remindAdministrators() {
        List<Account> administrators = accountService.findAdministrators();

        String subject = "Emlékeztető a témakereső rendszer frissítésére";
        String body = new StringBuilder()
                .append("<p>A következő időszakra felkészülendő, a rendszer adatait átnézni szükséges.</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        for (Account administrator : administrators) {
            mailSenderService.sendMail(administrator, subject, body);
        }
    }

    @Override
    public void remindAccounts(List<Account> accounts) {
        String subject = "Emlékeztető a témakereső rendszer használatára";
        String body = new StringBuilder()
                .append("<p>A rendszerbe már rég nem léptél be. Ha egy hónapig nem lépsz még be újra, akkor a felhasználói fiókod törlődik a rendszerből.</p>")
                .append("<br/>")
                .append("<p><small>küldve a témakereső rendszerből</small></p>")
                .toString();
        for (Account account : accounts) {
            mailSenderService.sendMail(account, subject, body);
        }
    }

}
