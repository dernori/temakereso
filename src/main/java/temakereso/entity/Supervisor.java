package temakereso.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Supervisor {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 250, nullable = false) 
	private String name;
	
	@Column(length = 250) 
	private String title;
	
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
	private Boolean external;
	
	@Column(length = 250) 
	private Boolean confirmed;
	
	@OneToOne
    @JoinColumn(name = "account")
	private Account account;
	
	@OneToMany(fetch = FetchType.EAGER)
    private Set<Topic> topics;
	
}
