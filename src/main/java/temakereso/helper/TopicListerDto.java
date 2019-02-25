package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicListerDto {

    private Long id;

    private String name;

    private String supervisor;

    private String category;

    private TopicType type;

    private TopicStatus status;

}
