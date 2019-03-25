package temakereso.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TopicFilters {

    private static TopicFilters EMPTY = new TopicFilters();

    private String name;

    private String description;

    private Long supervisor;

    private Long category;

    private TopicStatus status;

    private Boolean archive;

    private TopicType type;

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

}
