package temakereso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import temakereso.helper.Training;

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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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
