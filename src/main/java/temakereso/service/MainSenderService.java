package temakereso.service;

public interface MainSenderService {

    void sendMail(String from, String to, String subject, String body);
}
