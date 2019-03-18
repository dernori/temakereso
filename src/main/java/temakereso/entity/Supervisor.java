package temakereso.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Data
@ToString(exclude = {"topics"})
@EqualsAndHashCode(exclude = {"topics"})
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 250, nullable = false)
    private String name;

    @Column(length = 250)
    private String title;

    @ManyToOne
    @JoinColumn(name = "department")
    private Department department;

    @Column(length = 250)
    private String workplace;

    @Column(length = 50)
    private String phone;

    @Column(length = 250)
    private String website;

    @Column(length = 100)
    private String room;

    @Column(length = 250)
    private String officeHours;

    @Column(length = 250)
    private Boolean external = Boolean.FALSE;

    @Column(length = 250)
    private Boolean confirmed = Boolean.FALSE;

    @Column(length = 250)
    private Boolean deleted = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private Account account;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
    private Set<Topic> topics;

}
