package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.Parameter;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

	Parameter findByIdentifier(String identifier);

}
