package temakereso.service;

import temakereso.helper.ReportData;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicFilters;

public interface ReportService {

    ReportData findTopics(TopicFilters filters);

    ReportData findTopicsByDepartment(ReportFilters filters);

    ReportData findTopicsByCategory(ReportFilters filters);

    ReportData findTopicsByType(ReportFilters filters);

}