package temakereso.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import temakereso.entity.Topic;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.service.TopicService;

@RestController
@RequestMapping(value = "/api")
public class TopicController {
	
	@Autowired
	private TopicService topicService;

	// ------------------------ GET -------------------------- //
	
	/**
	 * Returns topics <br>
	 * If filters are not given all the topics will be returned
	 * 
	 * @param filters
	 * @param pageable
	 * @return a page of topics
	 */
	@GetMapping(path = "/topics")
	public ResponseEntity<Page<TopicDto>> getTopics(TopicFilters filters, Pageable pageable) {
		Page<TopicDto> topics = null;
		if(!filters.isEmpty()) {
			topics = topicService.getFilteredOnesByPage(filters, pageable);
		} else {
			topics = topicService.getAllByPage(pageable);
		}
		return new ResponseEntity<>(topics, HttpStatus.OK);
	}
	
	/**
	 * Returns a topic selected by its id
	 * 
	 * @param id
	 * @return a topic
	 */
	@GetMapping(path = "/topics/{id}")
	public ResponseEntity<Topic> getTopic(@PathVariable(name = "id") Long id) {
		Topic topic = topicService.getOneById(id);
		return new ResponseEntity<>(topic, HttpStatus.OK);
	}
	
	// ------------------------ POST ------------------------- //
	
	/**
	 * Saves the given topic
	 * 
	 * @param supervisorId id of the supervisor
	 * @param topic to be saved
	 * @return saved topic
	 */
	@PostMapping(path = "/supervisors/{id}/topics")
	public ResponseEntity<Topic> createTopic(@PathVariable(name = "id") Long supervisorId, @RequestBody Topic topic) {
		topicService.createTopic(supervisorId, topic);
		return new ResponseEntity<>(topic, HttpStatus.OK);
	}
	
	// ------------------------ PUT -------------------------- //
	
	/**
	 * Modifies the given topic
	 * 
	 * @param id of the topic to be modified
	 * @param topic to be modified
	 * @return modified topic
	 */
	@PutMapping(path = "/topics/{id}")
	public ResponseEntity<Topic> modifyTopic(@PathVariable(name = "id") Long id, @RequestBody Topic topic) {
		topicService.modifyTopic(topic);
		return new ResponseEntity<>(topic, HttpStatus.OK);
	}
	
	// ------------------------ DELETE ----------------------- //
	
	/**
	 * Archives the given topic
	 * 
	 * @param id of the topic to be archived
	 */
	@DeleteMapping(path = "/topics/{id}")
	public ResponseEntity<Void> archiveTopic(@PathVariable(name = "id") Long id) {
		topicService.archiveTopic(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
