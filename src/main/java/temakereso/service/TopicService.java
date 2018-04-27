package temakereso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import temakereso.entity.Topic;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;

public interface TopicService {

	// TODO change to DTO!
	
	/**
	 * Returns a page of topics
	 * 
	 * @param pageable
	 * @return a page of topic dtos
	 */
	Page<TopicDto> getAllByPage(Pageable pageable);
	
	/**
	 * Returns a topic by id
	 * 
	 * @param id
	 * @return topic with the given id
	 */
	Topic getOneById(Long id);
	
	/**
	 * Returns a page of topics filtered by parameters
	 * 
	 * @param pageable
	 * @param filters
	 * @return a page of topic dtos
	 */
	Page<TopicDto> getFilteredOnesByPage(TopicFilters filters, Pageable pageable);
	
	/**
	 * Creates a new topic
	 * 
	 * @param supervisorId id of the supervisor
	 * @param topic
	 * @return the saved topic
	 */
	Topic createTopic(Long supervisorId, Topic topic);
	
	/**
	 * Modifies a topic
	 * 
	 * @param topic
	 * @return the modified topic
	 */
	Topic modifyTopic(Topic topic);

	/**
	 * Archives the topic selected by the given id
	 * 
	 * @param id
	 */
	void archiveTopic(Long id);

}
