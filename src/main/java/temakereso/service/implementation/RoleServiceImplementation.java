package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.repository.RoleRepository;
import temakereso.service.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
