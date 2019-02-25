package temakereso.helper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import temakereso.entity.Attachment;
import temakereso.entity.Category;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicDto {

    private Long id;

    private String name;

    private TopicType type;

    private String description;

    private TopicStatus status;

    private Boolean archive;

    private Date creationDate;

    private SupervisorDto supervisor;

    private Category category;

    private StudentDto student;

    private Set<Attachment> attachments;

}
