package temakereso.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String username;

    @Column(length = 250, nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private List<Role> roles;

    @Temporal(TemporalType.DATE)
    private Date lastSuccessfulLogin = new Date();

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(nullable = false)
    private boolean deleted;

    public Account(String name, String email, String username, String password, List<Role> roles) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

}
