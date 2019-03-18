package temakereso.service;

import temakereso.entity.Department;

import java.util.List;

public interface DepartmentService {

    /**
     * Returns all the departments
     *
     * @return a list of categories
     */
    List<Department> getAll();

    /**
     * Creates a new department
     *
     * @param department department to be created
     * @return the saved department
     */
    Department createDepartment(Department department);

    /**
     * Modifies a department
     *
     * @param department department to be modified
     * @return the modified department
     */
    Department modifyDepartment(Department department);

    /**
     * Finds a department by its id
     *
     * @param id of a department
     * @return department with the given id
     */
    Department getOneById(Long id);

}
