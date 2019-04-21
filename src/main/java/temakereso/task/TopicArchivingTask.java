package temakereso.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import temakereso.entity.Topic;
import temakereso.service.TopicService;

import java.util.Date;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TopicArchivingTask {

    private final TopicService topicService;

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void archiveTopics() {
        List<Topic> topics = topicService.findTopicsToArchive();

        log.info("Archiving topics at {}", new Date());
        log.info(topics.toString());

        for (Topic topic : topics) {
            topicService.archiveTopic(topic.getId());
        }

    }

}