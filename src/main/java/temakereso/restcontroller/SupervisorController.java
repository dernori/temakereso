package temakereso.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import temakereso.entity.Supervisor;
import temakereso.service.SupervisorService;

@RestController
@RequestMapping(value = "/api")
public class SupervisorController {
	
	@Autowired
	private SupervisorService supervisorService;

	// ------------------------ GET -------------------------- //
	
	/**
	 * Returns supervisors <br>
	 * If filters are not given all the supervisors will be returned
	 * 
	 * @param filters
	 * @param pageable
	 * @return a page of supervisors
	 */
	@GetMapping(path = "/supervisors")
	public ResponseEntity<List<Supervisor>> getSupervisors() {
		List<Supervisor> supervisors = supervisorService.getAll();
		return new ResponseEntity<>(supervisors, HttpStatus.OK);
	}
	
	/**
	 * Returns a supervisor selected by its id
	 * 
	 * @param id
	 * @return a supervisor
	 */
	@GetMapping(path = "/supervisors/{id}")
	public ResponseEntity<Supervisor> getSupervisor(@PathVariable(name = "id") Long id) {
		Supervisor supervisor = supervisorService.getOneById(id);
		return new ResponseEntity<>(supervisor, HttpStatus.OK);
	}
	
	/**
	 * Returns the unconfirmed supervisors
	 * 
	 * @param filters
	 * @return a list of supervisors
	 */
	@GetMapping(path = "/supervisors/unconfirmed")
	public ResponseEntity<List<Supervisor>> getUnconfirmedSupervisors() {
		List<Supervisor> supervisors = supervisorService.getUnconfirmed();
		return new ResponseEntity<>(supervisors, HttpStatus.OK);
	}
	
	/**
	 * Returns an account selected by its username
	 * 
	 * @param username
	 * @return a supervisor
	 */
	@GetMapping(path = "/supervisors/byusername/{username}")
	public ResponseEntity<Supervisor> getSupervisorByUsername(@PathVariable(name = "username") String username) {
		Supervisor supervisor = supervisorService.getByUsername(username);
		return new ResponseEntity<>(supervisor, HttpStatus.OK);
	}
	
	// ------------------------ POST ------------------------- //
	
	/**
	 * Saves the given supervisor
	 * 
	 * @param supervisor to be saved
	 * @return saved supervisor
	 */
	@PostMapping(path = "/supervisors")
	public ResponseEntity<Supervisor> createSupervisor(@RequestBody Supervisor supervisor) {
		supervisorService.createSupervisor(supervisor);
		return new ResponseEntity<>(supervisor, HttpStatus.OK);
	}
	
	// ------------------------ PUT -------------------------- //
	
	/**
	 * Modifies the given supervisor
	 * 
	 * @param supervisor to be modified
	 * @return modified supervisor
	 */
	@PutMapping(path = "/supervisors")
	public ResponseEntity<Supervisor> modifySupervisor(@RequestBody Supervisor supervisor) {
		supervisorService.modifySupervisor(supervisor);
		return new ResponseEntity<>(supervisor, HttpStatus.OK);
	}
	
	/**
	 * Comfirms the supervisor selected by its id
	 * 
	 * @param id
	 */
	@PutMapping(path = "/supervisors/{id}/confirm")
	public ResponseEntity<Void> confirmSupervisor(@PathVariable(name = "id") Long id) {
		supervisorService.confirm(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
