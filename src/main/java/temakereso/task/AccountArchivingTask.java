package temakereso.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import temakereso.entity.Account;
import temakereso.service.AccountService;
import temakereso.service.ParameterService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AccountArchivingTask {

    private final ParameterService parameterService;

    private final AccountService accountService;

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void remindAdministrator() {

        Integer studentAccountArchivingMonths = parameterService.getStudentLoginTimeout() + 1;
        Integer supervisorAccountArchivingMonths = parameterService.getSupervisorLoginTimeout() + 1;

        Date referenceDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(referenceDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, -1 * studentAccountArchivingMonths);

        log.info("{}", c.getTime());

        List<Account> students = accountService.findStudentsToRemind(c.getTime());
        log.info("{}", students);
        for (Account account : students) {
            accountService.archiveAccount(account);
        }

        c.setTime(referenceDate);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MONTH, -1 * supervisorAccountArchivingMonths);

        log.info("{}", c.getTime());

        List<Account> supervisors = accountService.findStudentsToRemind(c.getTime());
        log.info("{}", supervisors);
        for (Account account : supervisors) {
            accountService.archiveAccount(account);
        }
    }
}