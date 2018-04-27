package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

}
