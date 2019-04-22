package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.Parameter;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Parameter findByIdentifier(String identifier);

    boolean existsByIdentifier(String identifier);

    List<Parameter> findByModifiableTrue();

}
