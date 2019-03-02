package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import temakereso.entity.Attachment;
import temakereso.entity.Student;
import temakereso.entity.Supervisor;
import temakereso.entity.Topic;
import temakereso.helper.AttachmentDto;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.helper.TopicInputDto;
import temakereso.helper.TopicListerDto;
import temakereso.helper.TopicStatus;
import temakereso.repository.TopicRepository;
import temakereso.service.CategoryService;
import temakereso.service.StudentService;
import temakereso.service.SupervisorService;
import temakereso.service.TopicService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicServiceImplementation implements TopicService {

    private final StudentService studentService;

    private final SupervisorService supervisorService;

    private final CategoryService categoryService;

    private final TopicRepository topicRepository;

    private final EntityManager entityManager;

    private final ModelMapper modelMapper;

    @Override
    public TopicDto getOneById(Long id) {
        return modelMapper.map(topicRepository.findOne(id), TopicDto.class);
    }

    @Override
    public Page<TopicListerDto> getFilteredOnesByPage(TopicFilters filters, Pageable pageable) {
        String sql = "select new temakereso.helper.TopicListerDto(t.id, t.name, t.supervisor.name, t.category.name, t.type, t.status) from Topic t where 1=1 ";

        if (filters.getName() != null) sql += "AND t.name like :name ";
        if (filters.getDescription() != null) sql += "AND t.description like :description ";
        if (filters.getSupervisor() != null) sql += "AND t.supervisor.id = :supervisor ";
        if (filters.getCategory() != null) sql += "AND t.category.id = :category ";
        if (filters.getStatus() != null) sql += "AND t.status = :status ";
        if (filters.getType() != null) sql += "AND t.type = :type ";

        if (pageable.getSort() != null) {
            if (pageable.getSort().getOrderFor("name") != null) {
                sql += " order by t.name " + pageable.getSort().getOrderFor("name").getDirection().name();
            } else if (pageable.getSort().getOrderFor("supervisor") != null) {
                sql += " order by t.supervisor.name " + pageable.getSort().getOrderFor("supervisor").getDirection().name();
            } else if (pageable.getSort().getOrderFor("category") != null) {
                sql += " order by t.category.name " + pageable.getSort().getOrderFor("category").getDirection().name();
            } else if (pageable.getSort().getOrderFor("status") != null) {
                sql += " order by t.status.name " + pageable.getSort().getOrderFor("status").getDirection().name();
            } else if (pageable.getSort().getOrderFor("type") != null) {
                sql += " order by t.type.name " + pageable.getSort().getOrderFor("type").getDirection().name();
            }
        }

        Query query = entityManager.createQuery(sql, TopicListerDto.class);

        if (filters.getName() != null) query.setParameter("name", "%" + filters.getName() + "%");
        if (filters.getDescription() != null) query.setParameter("description", "%" + filters.getDescription() + "%");
        if (filters.getSupervisor() != null) query.setParameter("supervisor", filters.getSupervisor());
        if (filters.getCategory() != null) query.setParameter("category", filters.getCategory());
        if (filters.getStatus() != null) query.setParameter("status", filters.getStatus());
        if (filters.getType() != null) query.setParameter("type", filters.getType());

        int totalRows = query.getResultList().size();

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        @SuppressWarnings("unchecked")
        List<TopicListerDto> results = query.getResultList();

        return new PageImpl<>(results, pageable, totalRows);
    }

    @Override
    public Long createTopic(Long supervisorId, TopicInputDto topicDto) {

        Topic topic = new Topic();
        topic.setId(topicDto.getId());
        topic.setName(topicDto.getName());
        topic.setType(topicDto.getType());
        topic.setDescription(topicDto.getDescription());
        topic.setCategory(categoryService.getOneById(topicDto.getCategoryId()));

        Supervisor supervisor = supervisorService.findOneById(supervisorId);
        topic.setSupervisor(modelMapper.map(supervisor, Supervisor.class));
        return topicRepository.save(topic).getId();
    }

    @Override
    public Long modifyTopic(Long id, TopicInputDto topicDto) {
        Topic topic = topicRepository.findOne(id);

        topic.setName(topicDto.getName());
        topic.setType(topicDto.getType());
        topic.setDescription(topicDto.getDescription());
        topic.setCategory(categoryService.getOneById(topicDto.getCategoryId()));

        topicRepository.save(topic);
        return id;
    }

    @Override
    public void archiveTopic(Long id) {
        Topic topic = topicRepository.findOne(id);
        topic.setArchive(true);
        topicRepository.save(topic);
    }

    @Override
    public void addAttachment(Long topicId, AttachmentDto attachmentDto) {
        Attachment attachment = new Attachment(attachmentDto.getFileId(), attachmentDto.getName());
        Topic topic = topicRepository.findOne(topicId);
        topic.getAttachments().add(attachment);
        topicRepository.save(topic);
    }

    @Override
    public void removeAttachment(Long topicId, Long attachmentId) {
        Topic topic = topicRepository.findOne(topicId);
        topic.getAttachments().removeIf(a -> a.getId() == attachmentId);
        topicRepository.save(topic);
    }

    @Override
    public void applyStudent(Long topicId, Long studentId) {
        Topic topic = topicRepository.findOne(topicId);
        Student student = studentService.findOneById(studentId);
        topic.getAppliedStudents().add(student);
        topicRepository.save(topic);
    }

    @Override
    public void removeApplication(Long topicId, Long studentId) {
        Topic topic = topicRepository.findOne(topicId);
        Student student = studentService.findOneById(studentId);
        topic.getAppliedStudents().remove(student);
        topicRepository.save(topic);
    }

    @Override
    public Set<StudentDto> getAppliedStudents(Long topicId) {
        Topic topic = topicRepository.findOne(topicId);
        return topic.getAppliedStudents()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<TopicDto> getTopicsAssignedToStudent(Long id) {
        return topicRepository.findByStudentId(id)
                .stream()
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void acceptApplication(Long topicId, Long studentId) {
        Topic topic = topicRepository.findOne(topicId);
        Student student = studentService.findOneById(studentId);
        topic.setStudent(student);
        topic.setStatus(TopicStatus.RESERVED);
        topic.getAppliedStudents().clear();
        topicRepository.save(topic);
    }

    @Override
    public void setTopicDone(Long topicId) {
        Topic topic = topicRepository.findOne(topicId);
        if (topic.getStatus() != TopicStatus.RESERVED || topic.getStudent() == null) return;
        topic.setStatus(TopicStatus.DONE);
        topicRepository.save(topic);
    }

}
