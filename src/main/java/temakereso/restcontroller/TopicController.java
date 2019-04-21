package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.ApplicationDto;
import temakereso.helper.AttachmentDto;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.helper.TopicInputDto;
import temakereso.helper.TopicListerDto;
import temakereso.service.TopicService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class TopicController {

    private final TopicService topicService;

    // ------------------------ GET -------------------------- //

    @GetMapping(path = "/topics")
    public Page<TopicListerDto> getTopics(TopicFilters filters, Pageable pageable) {
        return topicService.getFilteredOnesByPage(filters, pageable);
    }

    @GetMapping(path = "/topics/{id}")
    public TopicDto getTopic(@PathVariable(name = "id") Long id) {
        return topicService.getOneById(id);
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @GetMapping(path = "/topics/{id}/students")
    public Set<StudentDto> getAppliedStudents(@PathVariable(name = "id") Long topicId) {
        return topicService.getAppliedStudents(topicId);
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(path = "/students/{id}/topics/assigned")
    public Set<TopicDto> getTopicsAssignedToStudent(@PathVariable(name = "id") Long id) {
        return topicService.getTopicsAssignedToStudent(id);
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @GetMapping(path = "/supervisors/{id}/topics")
    public List<TopicDto> getSupervisorTopics(@PathVariable(name = "id") Long supervisorId) {
        return topicService.getSupervisorTopics(supervisorId);
    }

    // ------------------------ POST ------------------------- //

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @PostMapping(path = "/supervisors/{id}/topics")
    public Long createTopic(@PathVariable(name = "id") Long supervisorId, @RequestBody TopicInputDto topic) {
        return topicService.createTopic(supervisorId, topic);
    }

    @PreAuthorize("hasRole('ROLE_SUPERVISOR')")
    @PostMapping(path = "/topics/{id}/attachments")
    public void addAttachment(@PathVariable(name = "id") Long topicId, @RequestBody AttachmentDto attachmentDto) {
        topicService.addAttachment(topicId, attachmentDto);
    }

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @PostMapping(path = "/topics/{id}/students")
    public void applyStudent(@PathVariable(name = "id") Long topicId, @RequestBody ApplicationDto application) {
        topicService.applyStudent(topicId, application.getStudentId());
    }

    // ------------------------ PUT -------------------------- //

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @PutMapping(path = "/topics/{id}")
    public Long modifyTopic(@PathVariable(name = "id") Long id, @RequestBody TopicInputDto topic) {
        return topicService.modifyTopic(id, topic);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @PutMapping(path = "/topics/{id}/student")
    public void acceptApplication(@PathVariable(name = "id") Long topicId, @RequestBody ApplicationDto application) {
        topicService.acceptApplication(topicId, application.getStudentId());
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @PutMapping(path = "/topics/{id}/done")
    public void setTopicDone(@PathVariable(name = "id") Long topicId) {
        topicService.setTopicDone(topicId);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @PutMapping(path = "/topics/{id}/unarchive")
    public void unarchiveTopic(@PathVariable(name = "id") Long id) {
        topicService.unarchiveTopic(id);
    }

    // ------------------------ DELETE ----------------------- //

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @DeleteMapping(path = "/topics/{id}")
    public void archiveTopic(@PathVariable(name = "id") Long id) {
        topicService.archiveTopic(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR')")
    @DeleteMapping(path = "/topics/{topicId}/attachments/{attachmentId}")
    public void removeAttachment(@PathVariable(name = "topicId") Long topicId, @PathVariable(name = "attachmentId") Long attachmentId) {
        topicService.removeAttachment(topicId, attachmentId);
    }

    @PreAuthorize("hasAnyRole('ROLE_STUDENT')")
    @DeleteMapping(path = "/topics/{topicId}/students/{studentId}")
    public void removeApplication(@PathVariable(name = "topicId") Long topicId, @PathVariable(name = "studentId") Long studentId) {
        topicService.removeApplication(topicId, studentId);
    }

}
