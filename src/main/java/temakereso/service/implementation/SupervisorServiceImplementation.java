package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.entity.Supervisor;
import temakereso.helper.AccountDetails;
import temakereso.helper.Constants;
import temakereso.helper.SupervisorDto;
import temakereso.helper.SupervisorInputDto;
import temakereso.repository.SupervisorRepository;
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

    @Override
    public void modifyNameByAccountId(Long accountId, String name) {
        Supervisor supervisor = supervisorRepository.findByAccountId(accountId);
        supervisor.setName(name);
        supervisorRepository.save(supervisor);
    }

    @Override
    public void modifySupervisor(Long id, SupervisorInputDto supervisorInputDto) {
        Supervisor supervisor = findOneById(id);
        if (getLoggedInUserId() != supervisor.getAccount().getId()) {
            log.error("Account ids are not the same");
            throw new IllegalArgumentException();
        }
        supervisor.setTitle(supervisorInputDto.getTitle());
        supervisor.setDepartment(supervisorInputDto.getDepartment());
        supervisor.setWorkplace(supervisorInputDto.getWorkplace());
        supervisor.setPhone(supervisorInputDto.getPhone());
        supervisor.setWebsite(supervisorInputDto.getWebsite());
        supervisor.setRoom(supervisorInputDto.getRoom());
        supervisor.setOfficeHours(supervisorInputDto.getOfficeHours());
        supervisor.setExternal(supervisorInputDto.getExternal());
        supervisorRepository.save(supervisor);
    }

    private Long getLoggedInUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            return ((AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        }
        return null;
    }

}
