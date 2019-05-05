package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Department;
import temakereso.repository.DepartmentRepository;
import temakereso.service.implementation.DepartmentServiceImplementation;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {

    private static Department department;

    @InjectMocks
    private DepartmentServiceImplementation departmentService;

    @Mock
    private DepartmentRepository departmentRepository;

    @Before
    public void setUp() {
        department = new Department();
        department.setId(1L);
        department.setName("Test department with id");

        when(departmentRepository.exists(any(Long.class))).thenReturn(false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenDepartmentWithIdToCreate_thenThrowsException() {
        departmentService.createDepartment(department);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingDepartmentToModify_thenThrowsException() {
        departmentService.modifyDepartment(department);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenNonExistingDepartmentToFind_thenThrowsException() {
        departmentService.getOneById(0L);
    }
}
