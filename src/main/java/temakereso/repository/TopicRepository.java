package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.Topic;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
	
}
