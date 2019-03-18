package temakereso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import temakereso.helper.TopicStatus;
import temakereso.helper.TopicType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@ToString(exclude = {"appliedStudents"})
@EqualsAndHashCode(exclude = {"appliedStudents"})
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TopicType type;

    @Column(length = 10000, nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TopicStatus status;

    @Column(nullable = false)
    private Boolean archive;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date lastModificationDate;

    @ManyToOne
    @JoinColumn(name = "supervisor", nullable = false)
    private Supervisor supervisor;

    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "student")
    private Student student;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Attachment> attachments;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "topic_applied_students",
            joinColumns = {@JoinColumn(name = "topic_id")},
            inverseJoinColumns = {@JoinColumn(name = "applied_student_id")}
    )
    private Set<Student> appliedStudents;

    @PrePersist
    public void prePersist() {
        this.status = TopicStatus.OPEN;
        this.archive = Boolean.FALSE;
        this.creationDate = new Date();
        this.creationDate = new Date();
        this.appliedStudents = new HashSet<>();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModificationDate = new Date();
    }

}