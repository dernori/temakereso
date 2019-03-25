package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Category;
import temakereso.entity.Department;
import temakereso.entity.Topic;
import temakereso.helper.TopicType;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Set<Topic> findByStudentId(Long studentId);

    Set<Topic> findBySupervisorId(Long studentId);

    List<Topic> findBySupervisorDepartmentAndLastModificationDateBetween(Department department, Date startDate, Date endDate);

    List<Topic> findByCategoryAndLastModificationDateBetween(Category category, Date startDate, Date endDate);

    List<Topic> findByTypeAndLastModificationDateBetween(TopicType topicType, Date startDate, Date endDate);

    List<Topic> findByLastModificationDateBeforeAndArchiveIsFalse(Date date);
}
