package temakereso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import temakereso.helper.Training;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"topics"})
@EqualsAndHashCode(exclude = {"topics"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 250)
    private String code;

    @Column(length = 250)
    @Enumerated(EnumType.STRING)
    private Training training;

    @Column(length = 250)
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;

    @ManyToMany(mappedBy = "appliedStudents", fetch = FetchType.LAZY)
    private Set<Topic> topics = new HashSet<>();

}
