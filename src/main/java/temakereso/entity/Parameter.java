package temakereso.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Parameter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false, unique = true) 
	private String identifier;
	
	@Column(length = 100, nullable = false) 
	private String value;
	
	@Column(length = 250, nullable = false) 
	private String descpription;

}
