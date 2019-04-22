package temakereso.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import temakereso.entity.Account;
import temakereso.service.AccountService;
import temakereso.service.MailService;
import temakereso.service.ParameterService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class LoginReminderTask {

    private final MailService mailService;

    private final ParameterService parameterService;

    private final AccountService accountService;

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void remindAccounts() {

        Integer studentLoginReminderMonths = parameterService.getStudentLoginTimeout();
        Integer supervisorLoginReminderMonths = parameterService.getSupervisorLoginTimeout();

        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, -1 * studentLoginReminderMonths);

        log.info("{}", c.getTime());

        List<Account> students = accountService.findStudentsToRemind(c.getTime());
        log.info("{}", students);
        mailService.remindAccounts(students);

        c.setTime(referenceDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, -1 * supervisorLoginReminderMonths);

        log.info("{}", c.getTime());

        List<Account> supervisors = accountService.findStudentsToRemind(c.getTime());
        log.info("{}", supervisors);
        mailService.remindAccounts(supervisors);
    }
}