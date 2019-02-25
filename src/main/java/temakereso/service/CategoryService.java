package temakereso.service;

import java.util.List;

import temakereso.entity.Category;

public interface CategoryService {
	
	/**
	 * Returns all the categories
	 * 
	 * @return a list of categories
	 */
	List<Category> getAll();
	
	/**
	 * Creates a new category
	 * 
	 * @param category category to be created
	 * @return the saved category
	 */
	Category createCategory(Category category);
	
	/**
	 * Modifies a category
	 * 
	 * @param category category to be modified
	 * @return the modified category
	 */
	Category modifyCategory(Category category);

	/**
	 * Finds a category by its id
	 *
	 * @param id of a category
	 * @return category with the given id
	 */
	Category getOneById(Long id);

}
