package temakereso.service;

public interface MailSenderService {

    void sendMail(String from, String to, String subject, String body);
}
