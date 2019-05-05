package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Category;
import temakereso.repository.CategoryRepository;
import temakereso.service.implementation.CategoryServiceImplementation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    private static Category category;

    @InjectMocks
    private CategoryServiceImplementation categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Test category with id");

        when(categoryRepository.exists(any(Long.class))).thenReturn(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenCategoryWithIdToCreate_thenThrowsException() {
        categoryService.createCategory(category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingCategoryToModify_thenThrowsException() {
        categoryService.modifyCategory(category);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingCategoryToFind_thenThrowsException() {
        categoryService.getOneById(0L);
    }
}
