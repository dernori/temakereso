package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicInputDto {

    private Long id;

    private String name;

    private TopicType type;

    private String description;

    private Long categoryId;

}
