package temakereso.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import temakereso.entity.Category;
import temakereso.repository.CategoryRepository;
import temakereso.service.CategoryService;

@Service
public class CategoryServiceImplementation implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
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


}
