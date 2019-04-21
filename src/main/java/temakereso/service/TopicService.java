package temakereso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import temakereso.entity.Topic;
import temakereso.helper.AttachmentDto;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.helper.TopicInputDto;
import temakereso.helper.TopicListerDto;

import java.util.List;
import java.util.Set;

public interface TopicService {

    /**
     * Returns a topic by identifier
     *
     * @param id identifier of topic
     * @return topic with the given identifier
     */
    TopicDto getOneById(Long id);

    /**
     * Returns a page of topics filtered by parameters
     *
     * @param pageable page data
     * @param filters  filters
     * @return a page of topic dtos
     */
    Page<TopicListerDto> getFilteredOnesByPage(TopicFilters filters, Pageable pageable);

    /**
     * Creates a new topic
     *
     * @param supervisorId identifier of the supervisor
     * @param topic        topic to be saved
     * @return the saved topic
     */
    Long createTopic(Long supervisorId, TopicInputDto topic);

    /**
     * Modifies a topic
     *
     * @param id       identifier of topic
     * @param topicDto topic to be modified
     * @return the modified topic
     */
    Long modifyTopic(Long id, TopicInputDto topicDto);

    /**
     * Archives the topic selected by the given id
     *
     * @param id identifier of topic
     */
    void archiveTopic(Long id);

    /**
     * Unarchives the topic selected by the given id
     *
     * @param id identifier of topic
     */
    void unarchiveTopic(Long id);

    /**
     * Adds an attachment to the topic selected by its identifier.
     *
     * @param topicId       identifier of topic
     * @param attachmentDto attachment data
     */
    void addAttachment(Long topicId, AttachmentDto attachmentDto);

    /**
     * Removes the selected attachment to the topic selected by its identifier.
     *
     * @param topicId      identifier of topic
     * @param attachmentId id of attachment
     */
    void removeAttachment(Long topicId, Long attachmentId);

    /**
     * Saves a student's application.
     *
     * @param topicId   identifier of topic the student applied to
     * @param studentId identifier of student
     */
    void applyStudent(Long topicId, Long studentId);

    /**
     * Removes a student's application.
     *
     * @param topicId   identifier of topic the student applied to
     * @param studentId identifier of student
     */
    void removeApplication(Long topicId, Long studentId);

    /**
     * Finds students applied to the topic.
     *
     * @param topicId identifier of topic
     * @return collection of student data
     */
    Set<StudentDto> getAppliedStudents(Long topicId);

    /**
     * Finds topics the student applied to.
     *
     * @param studentId identifier of student
     * @return collection of topic data
     */
    Set<TopicDto> getTopicsAssignedToStudent(Long studentId);

    /**
     * Finds the supervisor's topics.
     *
     * @param supervisorId identifier of supervisor
     * @return collection of topic data
     */
    List<TopicDto> getSupervisorTopics(Long supervisorId);

    /**
     * Supervisor accepts application of student.
     *
     * @param topicId   identifier of topic
     * @param studentId identifier of student
     */
    void acceptApplication(Long topicId, Long studentId);

    /**
     * Sets topic status done.
     *
     * @param topicId identifier of topic
     */
    void setTopicDone(Long topicId);

    /**
     * Finds topics.
     *
     * @param filters filters
     * @return a collection of topics
     */
    List<Topic> getFilteredOnes(TopicFilters filters);

    /**
     * Finds topics to archive.
     *
     * @return a collection of topics
     */
    List<Topic> findTopicsToArchive();

}
