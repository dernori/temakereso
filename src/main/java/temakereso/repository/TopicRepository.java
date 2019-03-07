package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Topic;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Set<Topic> findByStudentId(Long studentId);

    Set<Topic> findBySupervisorId(Long sudentId);

}
