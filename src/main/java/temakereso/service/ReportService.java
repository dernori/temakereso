package temakereso.service;

import temakereso.helper.ReportData;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicFilters;

public interface ReportService {

    /**
     * Returns data for the general report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    ReportData findTopics(TopicFilters filters);

    /**
     * Returns data for the department specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    ReportData findTopicsByDepartment(ReportFilters filters);

    /**
     * Returns data for the category specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    ReportData findTopicsByCategory(ReportFilters filters);

    /**
     * Returns data for the topic type specific report file with the given filters.
     *
     * @param filters filters for the report
     * @return report data for the file
     */
    ReportData findTopicsByType(ReportFilters filters);

}