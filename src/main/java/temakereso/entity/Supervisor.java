package temakereso.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	private Boolean external = Boolean.FALSE;
	
	@Column(length = 250) 
	private Boolean confirmed = Boolean.FALSE;
	
	@Column(length = 250) 
	private Boolean deleted = Boolean.FALSE;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
	private Account account;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy="supervisor")
    private Set<Topic> topics;
	
}
