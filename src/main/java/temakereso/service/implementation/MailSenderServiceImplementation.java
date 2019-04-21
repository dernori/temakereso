package temakereso.service.implementation;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import temakereso.entity.Account;
import temakereso.helper.Constants;
import temakereso.service.MailSenderService;

import java.io.IOException;

@Slf4j
@Service
public class MailSenderServiceImplementation implements MailSenderService {

    @Value("${spring.sendgrid.api-key}")
    private String apiKey;

    @Value("${system.email}")
    private String systemEmail;

    @Override
    public void sendMail(Account fromAccount, Account toAccount, String subjectStr, String bodyStr) {
        if (toAccount.getEmail() == null) {
            log.error("Message could not be sent to {} as no email address was provided", fromAccount.getUsername(), toAccount.getUsername());
            throw new IllegalArgumentException(Constants.SENDER_ADDRESS_NOT_PROVIDED);
        }
        sendMail(fromAccount.getEmail() != null ? fromAccount.getEmail() : systemEmail, toAccount.getEmail(), subjectStr, bodyStr);
    }

    @Override
    public void sendMail(Account toAccount, String subjectStr, String bodyStr) {
        sendMail(systemEmail, toAccount.getEmail(), subjectStr, bodyStr);
    }

    private void sendMail(String fromStr, String toStr, String subjectStr, String bodyStr) {
        Email from = new Email(fromStr);
        String subject = subjectStr;
        Email to = new Email(toStr);
        Content content = new Content("text/html", bodyStr);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            log.info("Mail api request status code: {}", response.getStatusCode());
        } catch (IOException ex) {
            log.error("Mail could not be sent.");
            ExceptionUtils.printRootCauseStackTrace(ex);
            throw new IllegalArgumentException(Constants.MAIL_COULD_NOT_BE_SENT);
        }
    }

}
