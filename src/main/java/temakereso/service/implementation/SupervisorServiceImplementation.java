package temakereso.service.implementation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import temakereso.entity.Role;
import temakereso.entity.Supervisor;
import temakereso.repository.SupervisorRepository;
import temakereso.service.SupervisorService;

@Service
public class SupervisorServiceImplementation implements SupervisorService {
	
	@Autowired
	private SupervisorRepository supervisorRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Supervisor> getAll() {
		return supervisorRepository.findAll();
	}

	@Override
	public Supervisor createSupervisor(Supervisor supervisor) {
		supervisor.getAccount().setRoles(Arrays.asList(new Role("SUPERVISOR")));
		supervisor.getAccount().setPassword(passwordEncoder.encode(supervisor.getAccount().getPassword()));
		return supervisorRepository.save(supervisor);
	}

	@Override
	public Supervisor modifySupervisor(Supervisor supervisor) {
		return supervisorRepository.save(supervisor);
	}

	@Override
	public Supervisor getOneById(Long id) {
		return supervisorRepository.findOne(id);
	}

	@Override
	public Supervisor getByUsername(String username) {
		Supervisor supervisor = supervisorRepository.getByUsername(username);
		return supervisor;
	}

	@Override
	public List<Supervisor> getUnconfirmed() {
		return supervisorRepository.findByConfirmedFalse();
	}

	@Override
	public void confirm(Long id) {
		Supervisor supervisor = getOneById(id);
		supervisor.setConfirmed(Boolean.TRUE);
		supervisorRepository.save(supervisor);
	}


}
