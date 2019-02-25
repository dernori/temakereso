package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temakereso.entity.Category;
import temakereso.repository.CategoryRepository;
import temakereso.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final CategoryRepository categoryRepository;

    // TODO validation!

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }


    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category modifyCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getOneById(Long id) {
        return categoryRepository.findOne(id);
    }


}
