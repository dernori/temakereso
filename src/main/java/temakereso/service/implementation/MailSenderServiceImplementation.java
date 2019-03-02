package temakereso.service.implementation;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import temakereso.service.MainSenderService;

import java.io.IOException;

public class MailSenderServiceImplementation implements MainSenderService {

    // TODO logging

    @Override
    public void sendMail(String fromStr, String toStr, String subjectStr, String bodyStr) {
        Email from = new Email(fromStr);
        String subject = subjectStr;
        Email to = new Email(toStr);
        Content content = new Content("text/plain", bodyStr);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.method = Method.POST;
            request.endpoint = "mail/send";
            request.body = mail.build();
            Response response = sg.api(request);
            System.out.println("Status code sending email:" + response.statusCode);
            System.out.println(response.body);
            System.out.println(response.headers);
        } catch (IOException ex) {
            System.out.println("Email was not sent." + ex.getLocalizedMessage());
        }
    }

}
