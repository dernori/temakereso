package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.entity.Supervisor;
import temakereso.helper.Constants;
import temakereso.helper.SupervisorDto;
import temakereso.repository.SupervisorRepository;
import temakereso.service.MailService;
import temakereso.service.RoleService;
import temakereso.service.SupervisorService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupervisorServiceImplementation implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    private final MailService mailService;

    @Override
    public List<SupervisorDto> getAll() {
        return supervisorRepository.findAll()
                .stream()
                .map(supervisor -> modelMapper.map(supervisor, SupervisorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupervisorDto createSupervisor(Supervisor supervisor) {
        if (supervisor.getId() != null) {
            log.error("Supervisor has id: {}", supervisor.getId());
            throw new IllegalArgumentException(Constants.SUPERVISOR_ALREADY_EXISTS);
        }
        Role supervisorRole = roleService.findByName("SUPERVISOR");
        supervisor.getAccount().setRoles(Arrays.asList(supervisorRole != null ? supervisorRole : new Role("SUPERVISOR")));
        supervisor.getAccount().setPassword(passwordEncoder.encode(supervisor.getAccount().getPassword()));
        mailService.supervisorRegistered();
        return modelMapper.map(supervisorRepository.save(supervisor), SupervisorDto.class);
    }

    @Override
    public SupervisorDto getOneById(Long id) {
        return modelMapper.map(supervisorRepository.findOne(id), SupervisorDto.class);
    }

    @Override
    public List<SupervisorDto> getUnconfirmed() {
        return supervisorRepository.findByConfirmedFalse()
                .stream()
                .map(supervisor -> modelMapper.map(supervisor, SupervisorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void confirm(Long id) {
        if (!supervisorRepository.exists(id)) {
            log.error("No supervisor exists with id: {}", id);
            throw new IllegalArgumentException(Constants.SUPERVISOR_NOT_EXISTS);
        }
        Supervisor supervisor = supervisorRepository.findOne(id);
        supervisor.setConfirmed(Boolean.TRUE);
        supervisorRepository.save(supervisor);
    }

    @Override
    public Supervisor findOneById(Long supervisorId) {
        return supervisorRepository.findOne(supervisorId);
    }

    @Override
    public SupervisorDto findByAccountId(Long accountId) {
        Supervisor supervisor = supervisorRepository.findByAccountId(accountId);
        return supervisor != null ? modelMapper.map(supervisor, SupervisorDto.class) : null;
    }

}
