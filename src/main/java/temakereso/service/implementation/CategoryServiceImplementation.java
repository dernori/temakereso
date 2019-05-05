package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import temakereso.entity.Category;
import temakereso.helper.Constants;
import temakereso.repository.CategoryRepository;
import temakereso.service.CategoryService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category createCategory(Category category) {
        if (category.getId() != null) {
            log.error("Category already exists: {}", category.getName());
            throw new IllegalArgumentException(Constants.CATEGORY_ALREADY_EXISTS);
        }
        log.info("Category to be created: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Category modifyCategory(Category category) {
        if (!categoryRepository.exists(category.getId())) {
            log.error("Category not exists: {}", category.getName());
            throw new IllegalArgumentException(Constants.CATEGORY_NOT_EXISTS);
        }
        log.info("Category to be modified: {}", category);
        return categoryRepository.save(category);
    }

    @Override
    public Category getOneById(Long id) {
        if (!categoryRepository.exists(id)) {
            log.error("No category exists with id: {}", id);
            throw new IllegalArgumentException(Constants.CATEGORY_NOT_EXISTS);
        }
        return categoryRepository.findOne(id);
    }


}
