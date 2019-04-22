package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temakereso.entity.Category;
import temakereso.entity.Department;
import temakereso.entity.Topic;
import temakereso.helper.HeaderData;
import temakereso.helper.ReportData;
import temakereso.helper.ReportFilters;
import temakereso.helper.TopicDto;
import temakereso.helper.TopicFilters;
import temakereso.helper.TopicType;
import temakereso.service.ReportService;
import temakereso.service.TopicReportService;
import temakereso.service.TopicService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportServiceImplementation implements ReportService {

    private final TopicService topicService;

    private final TopicReportService topicReportService;

    private static final String PATTERN = "yyyy. MM. dd.";

    @Override
    public ReportData findTopics(TopicFilters filters) {
        HeaderData headerData = createTopicSheetHeaderData();

        List<List<Object>> data = new ArrayList<>();
        List<Topic> topics = topicService.getFilteredOnes(filters);
        for (Topic topic : topics) {
            data.add(Arrays.asList(
                    topic.getId(),
                    topic.getName(),
                    topic.getType().getName(),
                    topic.getStatus().getName(),
                    topic.getCategory().getName(),
                    topic.getSupervisor().getName(),
                    topic.getSupervisor().getDepartment() != null ? topic.getSupervisor().getDepartment().getName() : "",
                    topic.getSupervisor().getWorkplace(),
                    topic.getStudent() != null ? topic.getStudent().getName() : "",
                    topic.getStudent() != null ? topic.getStudent().getCode() : "",
                    topic.getCreationDate() != null ? formatDate(topic.getCreationDate()) : "",
                    topic.getLastModificationDate() != null ? formatDate(topic.getLastModificationDate()) : ""
            ));
        }
        return new ReportData(Arrays.asList(headerData), Arrays.asList(data));
    }

    @Override
    public ReportData findTopicsByDepartment(ReportFilters filters) {
        List<String> summaryColumnNames = Arrays.asList("Tanszék", "Darabszám");
        HeaderData summaryHeaderData = new HeaderData("Összesítő", summaryColumnNames);

        HeaderData headerData = createTopicSheetHeaderData();

        Map<Department, List<TopicDto>> topics = topicReportService.findTopicsByDepartment(filters);

        List<List<Object>> summaryData = new ArrayList<>();
        for (Department department : topics.keySet()) {
            summaryData.add(Arrays.asList(
                    department.getName(),
                    topics.get(department).size()
            ));
        }

        List<List<Object>> data = new ArrayList<>();
        for (Department department : topics.keySet()) {
            if (topics.get(department).isEmpty()) continue;
            for (TopicDto topic : topics.get(department)) {
                addTopic(data, topic);
            }
            data.add(Collections.emptyList());
        }

        return new ReportData(Arrays.asList(summaryHeaderData, headerData), Arrays.asList(summaryData, data));
    }

    @Override
    public ReportData findTopicsByCategory(ReportFilters filters) {
        List<String> summaryColumnNames = Arrays.asList("Kategória", "Darabszám");
        HeaderData summaryHeaderData = new HeaderData("Összesítő", summaryColumnNames);

        HeaderData headerData = createTopicSheetHeaderData();

        Map<Category, List<TopicDto>> topics = topicReportService.findTopicsByCategory(filters);

        List<List<Object>> summaryData = new ArrayList<>();
        for (Category category : topics.keySet()) {
            summaryData.add(Arrays.asList(
                    category.getName(),
                    topics.get(category).size()
            ));
        }

        List<List<Object>> data = new ArrayList<>();
        for (Category category : topics.keySet()) {
            if (topics.get(category).isEmpty()) continue;
            for (TopicDto topic : topics.get(category)) {
                addTopic(data, topic);
            }
            data.add(Collections.emptyList());
        }

        return new ReportData(Arrays.asList(summaryHeaderData, headerData), Arrays.asList(summaryData, data));
    }

    @Override
    public ReportData findTopicsByType(ReportFilters filters) {
        List<String> summaryColumnNames = Arrays.asList("Típus", "Darabszám");
        HeaderData summaryHeaderData = new HeaderData("Összesítő", summaryColumnNames);

        HeaderData headerData = createTopicSheetHeaderData();

        Map<TopicType, List<TopicDto>> topics = topicReportService.findTopicsByTopicType(filters);

        List<List<Object>> summaryData = new ArrayList<>();
        for (TopicType topicType : topics.keySet()) {
            summaryData.add(Arrays.asList(
                    topicType.getName(),
                    topics.get(topicType).size()
            ));
        }

        List<List<Object>> data = new ArrayList<>();
        for (TopicType topicType : topics.keySet()) {
            if (topics.get(topicType).isEmpty()) continue;
            for (TopicDto topic : topics.get(topicType)) {
                addTopic(data, topic);
            }
            data.add(Collections.emptyList());
        }

        return new ReportData(Arrays.asList(summaryHeaderData, headerData), Arrays.asList(summaryData, data));
    }

    private String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat(PATTERN);
        return df.format(date);
    }

    private void addTopic(List<List<Object>> data, TopicDto topic) {
        data.add(Arrays.asList(
                Long.toString(topic.getId()),
                topic.getName(),
                topic.getType().getName(),
                topic.getStatus().getName(),
                topic.getCategory().getName(),
                topic.getSupervisor().getName(),
                topic.getSupervisor().getDepartment() != null ? topic.getSupervisor().getDepartment().getName() : "",
                topic.getSupervisor().getWorkplace(),
                topic.getStudent() != null ? topic.getStudent().getName() : "",
                topic.getStudent() != null ? topic.getStudent().getCode() : "",
                formatDate(topic.getCreationDate()),
                formatDate(topic.getLastModificationDate())
        ));
    }

    private HeaderData createTopicSheetHeaderData() {
        List<String> columnNames = Arrays.asList(
                "Azonosító",
                "Cím",
                "Típus",
                "Állapot",
                "Kategória",
                "Témavezező neve",
                "Témavezető tanszéke",
                "Témavezető munkahelye",
                "Hallgató neve",
                "Hallgató Neptun kódja",
                "Létrehozás dátuma",
                "Módosítás dátuma");
        return new HeaderData("Adatok", columnNames);
    }

}