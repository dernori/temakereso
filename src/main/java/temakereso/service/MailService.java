package temakereso.service;

import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.MailDto;

import java.util.List;
import java.util.Set;

public interface MailService {

    void studentApplied(Student student, Topic topic);

    void studentAccepted(Student student, Topic topic);

    void applicationCleared(Student student, Topic topic);

    void applicationCleared(Set<Student> student, Topic topic);

    void topicsArchived(List<Topic> topics);

    void sendSimpleMail(MailDto mailDto);
}
