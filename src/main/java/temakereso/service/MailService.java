package temakereso.service;

import temakereso.entity.Account;
import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.MailDto;

import java.util.List;
import java.util.Set;

public interface MailService {

    /**
     * Sends a mail to the topic's supervisor and the student when a student applied to a topic.
     *
     * @param student student who applied
     * @param topic   topic the student applied to
     */
    void studentApplied(Student student, Topic topic);

    /**
     * Sends a mail to the topic's supervisor and the student when its applications is accepted.
     *
     * @param student student who got accepted
     * @param topic   topic the student applied to
     */
    void studentAccepted(Student student, Topic topic);

    /**
     * Sends a mail to the student when its application is removed.
     *
     * @param student student who applied
     * @param topic   topic the student applied to
     */
    void applicationCleared(Student student, Topic topic);

    /**
     * Sends a mail to all students when their application is removed.
     *
     * @param students students who applied
     * @param topic    topic students applied to
     */
    void applicationCleared(Set<Student> students, Topic topic);

    /**
     * Sends a mail to administrators when a supervisor registered.
     */
    void supervisorRegistered();

    /**
     * Sends a mail with the given data.
     *
     * @param mailDto data of the mail to be sent
     */
    void sendSimpleMail(MailDto mailDto);

    /**
     * Reminds administrators.
     */
    void remindAdministrators();

    void remindAccounts(List<Account> accounts);

}
