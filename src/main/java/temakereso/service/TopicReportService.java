package temakereso.service;

import temakereso.entity.Category;
import temakereso.entity.Department;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicType;

import java.util.List;
import java.util.Map;

public interface TopicReportService {

    /**
     * Returns data for the department specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    Map<Department, List<TopicDto>> findTopicsByDepartment(ReportFilters filters);

    /**
     * Returns data for the topic specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    Map<Category, List<TopicDto>> findTopicsByCategory(ReportFilters filters);

    /**
     * Returns data for the topic type specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    Map<TopicType, List<TopicDto>> findTopicsByTopicType(ReportFilters filters);
}
