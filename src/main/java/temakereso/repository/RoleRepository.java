package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
