package temakereso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import temakereso.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
