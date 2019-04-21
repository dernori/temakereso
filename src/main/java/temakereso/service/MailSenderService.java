package temakereso.service;

import temakereso.entity.Account;

public interface MailSenderService {

    /**
     * Sends a mail with the given data.
     *
     * @param from    sender of mail
     * @param to      addressee of mail
     * @param subject subject of mail
     * @param body    body of mail
     */
    void sendMail(Account from, Account to, String subject, String body);

    /**
     * Sends a mail with the given data. Sender address is the system email address.
     *
     * @param to      addressee of mail
     * @param subject subject of mail
     * @param body    body of mail
     */
    void sendMail(Account to, String subject, String body);
}
