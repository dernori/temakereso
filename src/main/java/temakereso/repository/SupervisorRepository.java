package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Supervisor;

import java.util.List;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {

    List<Supervisor> findByConfirmedFalse();

    Supervisor findByAccountId(Long id);

}
