package temakereso.service;

import temakereso.entity.Account;

public interface MailSenderService {

    void sendMail(Account from, Account to, String subject, String body);

    void sendMail(Account to, String subject, String body);
}
