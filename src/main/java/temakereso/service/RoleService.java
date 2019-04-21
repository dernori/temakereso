package temakereso.service;

import temakereso.entity.Role;

public interface RoleService {

    /**
     * Finds a role by its name.
     *
     * @param name name of role
     * @return role with the given name
     */
    Role findByName(String name);

}
