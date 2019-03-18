package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
