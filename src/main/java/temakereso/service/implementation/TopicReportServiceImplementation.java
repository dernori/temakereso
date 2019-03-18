package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import temakereso.entity.Category;
import temakereso.entity.Department;
import temakereso.entity.Topic;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicType;
import temakereso.repository.TopicRepository;
import temakereso.service.CategoryService;
import temakereso.service.DepartmentService;
import temakereso.service.TopicReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicReportServiceImplementation implements TopicReportService {

    private final CategoryService categoryService;

    private final DepartmentService departmentService;

    private final TopicRepository topicRepository;

    private final ModelMapper modelMapper;


    @Override
    public Map<Department, List<TopicDto>> findTopicsByDepartment(ReportFilters filters) {
        Map<Department, List<TopicDto>> topics = new HashMap<>();
        List<Department> departments = departmentService.getAll();

        for (Department department : departments) {
            List<Topic> departmentTopics = topicRepository.findBySupervisorDepartmentAndLastModificationDateBetween(department, filters.getStartDate(), filters.getEndDate());
            List<TopicDto> mappedTopics = departmentTopics
                    .stream()
                    .map(topic -> modelMapper.map(topic, TopicDto.class))
                    .collect(Collectors.toList());
            topics.put(department, mappedTopics);
        }
        return topics;
    }

    @Override
    public Map<Category, List<TopicDto>> findTopicsByCategory(ReportFilters filters) {
        Map<Category, List<TopicDto>> topics = new HashMap<>();
        List<Category> categories = categoryService.getAll();

        for (Category category : categories) {
            List<Topic> categoryTopics = topicRepository.findByCategoryAndLastModificationDateBetween(category, filters.getStartDate(), filters.getEndDate());
            List<TopicDto> mappedTopics = categoryTopics
                    .stream()
                    .map(topic -> modelMapper.map(topic, TopicDto.class))
                    .collect(Collectors.toList());
            topics.put(category, mappedTopics);
        }
        return topics;
    }

    @Override
    public Map<TopicType, List<TopicDto>> findTopicsByTopicType(ReportFilters filters) {
        Map<TopicType, List<TopicDto>> topics = new HashMap<>();

        for (TopicType topicType : TopicType.values()) {
            List<Topic> topicTypeTopics = topicRepository.findByTypeAndLastModificationDateBetween(topicType, filters.getStartDate(), filters.getEndDate());
            List<TopicDto> mappedTopics = topicTypeTopics
                    .stream()
                    .map(topic -> modelMapper.map(topic, TopicDto.class))
                    .collect(Collectors.toList());
            topics.put(topicType, mappedTopics);
        }
        return topics;
    }

}
