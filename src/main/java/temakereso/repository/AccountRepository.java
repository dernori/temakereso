package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Account;
import temakereso.entity.Role;

import java.util.Date;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    boolean existsByUsername(String username);

    List<Account> findByRolesContains(Role role);

    List<Account> findByRolesContainsAndLastSuccessfulLogin(Role role, Date date);

    Account findByToken(String token);
}
