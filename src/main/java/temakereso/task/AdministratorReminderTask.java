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
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdministratorReminderTask {

    private final MailService mailService;

    private final ParameterService parameterService;

    private final AccountService accountService;

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void remindAdministrator() {

        Integer summerMonth = parameterService.getSummerTermReminderMonth();
        Integer summerDay = parameterService.getSummerTermReminderDay();

        Integer springMonth = parameterService.getSpringTermReminderMonth();
        Integer springDay = parameterService.getSpringTermReminderDay();

        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        if ((currentMonth == summerMonth && currentDay == summerDay) || (currentMonth == springMonth && currentDay == springDay)) {
            log.info("Reminding administrators.");
            List<Account> administrators = accountService.findAdministrators();
            mailService.remindAdministrators(administrators);
        }
    }
}