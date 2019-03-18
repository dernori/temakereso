package temakereso.service;

import temakereso.entity.Category;
import temakereso.entity.Department;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicType;

import java.util.List;
import java.util.Map;

public interface TopicReportService {

    Map<Department, List<TopicDto>> findTopicsByDepartment(ReportFilters filters);

    Map<Category, List<TopicDto>> findTopicsByCategory(ReportFilters filters);

    Map<TopicType, List<TopicDto>> findTopicsByTopicType(ReportFilters filters);
}
