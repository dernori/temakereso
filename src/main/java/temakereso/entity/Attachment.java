package temakereso.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(nullable = false)
    private Long fileId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date uploadDate;

    @PrePersist
    public void prePersist() {
        this.uploadDate = new Date();
    }

    public Attachment(Long fileId, String name) {
        this.fileId = fileId;
        this.name = name;
    }
}
