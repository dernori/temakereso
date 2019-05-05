package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Super;
import temakereso.entity.Student;
import temakereso.entity.Supervisor;
import temakereso.entity.Topic;
import temakereso.helper.AttachmentDto;
import temakereso.helper.SupervisorDto;
import temakereso.helper.TopicInputDto;
import temakereso.helper.TopicStatus;
import temakereso.repository.ParameterRepository;
import temakereso.repository.TopicRepository;
import temakereso.service.implementation.TopicServiceImplementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TopicServiceTest {

    private static Topic topic;
    private static Supervisor supervisor;
    private static SupervisorDto supervisorDto;

    @InjectMocks
    private TopicServiceImplementation topicServiceImplementation;

    @Mock
    private LoggedInUserService loggedInUserService;

    @Mock
    private TopicRepository topicRepository;

    @Before
    public void setUp() {
        supervisorDto = new SupervisorDto();
        supervisorDto.setId(1L);

        supervisor = new Supervisor();

        topic = new Topic();
        topic.setId(1L);
        topic.setSupervisor(supervisor);
        topic.setStatus(TopicStatus.OPEN);

        when(loggedInUserService.getLoggedInSupervisor()).thenReturn(supervisorDto);

        when(topicRepository.exists(any(Long.class))).thenReturn(true);
        when(topicRepository.findOne(any(Long.class))).thenReturn(topic);
    }

    @Test(expected = IllegalStateException.class)
    public void givenOpenTopic_thenThrowsException() {
        supervisor.setId(1L);
        topic.setStudent(new Student());
        topicServiceImplementation.setTopicDone(0L);
    }

    @Test(expected = IllegalStateException.class)
    public void givenTopicWithoutStudent_thenThrowsException() {
        supervisor.setId(1L);
        topic.setStudent(null);
        topicServiceImplementation.setTopicDone(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenTopicToSetStatusDone_whenNotItsSupervisorIsLoggedIn_thenThrowsException() {
        supervisor.setId(2L);
        topicServiceImplementation.setTopicDone(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenTopicAndStudentToAcceptApplication_whenNotItsSupervisorIsLoggedIn_thenThrowsException() {
        supervisor.setId(2L);
        topicServiceImplementation.acceptApplication(0L, 0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenTopicToRemoveAttachment_whenNotItsSupervisorIsLoggedIn_thenThrowsException() {
        supervisor.setId(2L);
        topicServiceImplementation.removeAttachment(0L, 1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenTopicToAddAttachment_whenNotItsSupervisorIsLoggedIn_thenThrowsException() {
        supervisor.setId(2L);
        topicServiceImplementation.addAttachment(0L, new AttachmentDto());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenTopicToModify_whenNotItsSupervisorIsLoggedIn_thenThrowsException() {
        supervisor.setId(2L);
        topicServiceImplementation.modifyTopic(0L, new TopicInputDto());
    }

}
