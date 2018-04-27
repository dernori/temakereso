package temakereso.service.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import temakereso.entity.Supervisor;
import temakereso.entity.Topic;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.repository.TopicRepository;
import temakereso.service.SupervisorService;
import temakereso.service.TopicService;

@Service
public class TopicServiceImplementation implements TopicService {
	
	@Autowired
	private SupervisorService supervisorService;
	
	@Autowired
	private TopicRepository topicRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	// TODO validation!

	@Override
	public Page<TopicDto> getAllByPage(Pageable pageable) {
		String sql = "select new temakereso.helper.TopicDto(t.id, t.name, t.supervisor.name, t.category.name, t.status) from Topic t ";
		
		if(pageable.getSort() != null) {
			if(pageable.getSort().getOrderFor("name") != null) {
				 sql += " order by t.name " + pageable.getSort().getOrderFor("name").getDirection().name();
			} else if(pageable.getSort().getOrderFor("supervisor") != null) {
				 sql += " order by t.supervisor.name " + pageable.getSort().getOrderFor("supervisor").getDirection().name();
			} else if(pageable.getSort().getOrderFor("category") != null) {
				 sql += " order by t.category.name " + pageable.getSort().getOrderFor("category").getDirection().name();
			} else if(pageable.getSort().getOrderFor("status") != null) {
				 sql += " order by t.status.name " + pageable.getSort().getOrderFor("status").getDirection().name();
			}
		}
		
		Query query = entityManager.createQuery(sql, TopicDto.class);
		query.getResultList();
		int totalRows = query.getResultList().size();

	    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
	    query.setMaxResults(pageable.getPageSize());
	    
	    @SuppressWarnings("unchecked")
		List<TopicDto> results = query.getResultList();

		return new PageImpl<TopicDto>(results, pageable, totalRows);
	}

	@Override
	public Topic getOneById(Long id) {
		return topicRepository.findOne(id);
	}

	@Override
	public Page<TopicDto> getFilteredOnesByPage(TopicFilters filters, Pageable pageable) {
		String sql = "select new temakereso.helper.TopicDto(t.id, t.name, t.supervisor.name, t.category.name, t.status) from Topic t where 1=1 ";

		if(filters.getName() != null) 			sql += "AND t.name like :name ";
		if(filters.getDescription() != null) 	sql += "AND t.description like :description ";
		if(filters.getSupervisor() != null) 	sql += "AND t.supervisor.id = :supervisor ";
		if(filters.getCategory() != null)		sql += "AND t.category.id = :category ";
		if(filters.getStatus() != null) 		sql += "AND t.status = :status ";
		if(filters.getType() != null) 			sql += "AND t.type = :type ";
		
		if(pageable.getSort() != null) {
			if(pageable.getSort().getOrderFor("name") != null) {
				 sql += " order by t.name " + pageable.getSort().getOrderFor("name").getDirection().name();
			} else if(pageable.getSort().getOrderFor("supervisor") != null) {
				 sql += " order by t.supervisor.name " + pageable.getSort().getOrderFor("supervisor").getDirection().name();
			} else if(pageable.getSort().getOrderFor("category") != null) {
				 sql += " order by t.category.name " + pageable.getSort().getOrderFor("category").getDirection().name();
			} else if(pageable.getSort().getOrderFor("status") != null) {
				 sql += " order by t.status.name " + pageable.getSort().getOrderFor("status").getDirection().name();
			}
		}
		
		Query query = entityManager.createQuery(sql);
		
		if(filters.getName() != null) 			query.setParameter("name", "%" + filters.getName() + "%");
		if(filters.getDescription() != null) 	query.setParameter("description", "%" + filters.getDescription() + "%");
		if(filters.getSupervisor() != null) 	query.setParameter("supervisor", filters.getSupervisor());
		if(filters.getCategory() != null) 		query.setParameter("category", filters.getCategory());	
		if(filters.getStatus() != null) 		query.setParameter("status", filters.getStatus());
		if(filters.getType() != null)			query.setParameter("type", filters.getType());
		
		int totalRows = query.getResultList().size();

	    query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
	    query.setMaxResults(pageable.getPageSize());
	    
	    @SuppressWarnings("unchecked")
		List<TopicDto> results = query.getResultList();

		return new PageImpl<TopicDto>(results, pageable, totalRows);
	}

	@Override
	public Topic createTopic(Long supervisorId, Topic topic) {
		Supervisor supervisor = supervisorService.getOneById(supervisorId);
		topic.setSupervisor(supervisor);
		return topicRepository.save(topic);
	}

	@Override
	public Topic modifyTopic(Topic topic) {
		return topicRepository.save(topic);
	}

	@Override
	public void archiveTopic(Long id) {
		Topic topic = topicRepository.findOne(id);
		topic.setArchive(true);
		topicRepository.save(topic);
	}

}
