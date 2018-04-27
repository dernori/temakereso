package temakereso.entity;

import java.util.Date;
import java.util.Set;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;
import temakereso.helper.TopicStatus;
import temakereso.helper.TopicType;

@Data
@Entity
@NoArgsConstructor
public class Topic {
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    
    @ManyToOne
    @JoinColumn(name = "supervisor", nullable = false)
    private Supervisor supervisor;
    
    @ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private Category category;
    
    @ManyToOne
	@JoinColumn(name = "student")
    private Account student;    
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Attachment> attachments; 
    
    @PrePersist
	public void prePersist() {
    	this.status = TopicStatus.OPEN;
    	this.archive = Boolean.FALSE;
		this.creationDate = new Date();
	}

}