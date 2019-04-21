package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import temakereso.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByAccountId(Long account);

}
