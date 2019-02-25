package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import temakereso.entity.Topic;
import temakereso.helper.TopicListerDto;

import java.util.Set;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {

    Set<Topic> findByStudentId(Long studentId);
	
}
