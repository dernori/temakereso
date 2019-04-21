package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import temakereso.entity.Department;
import temakereso.helper.Constants;
import temakereso.repository.DepartmentRepository;
import temakereso.service.DepartmentService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }


    @Override
    public Department createDepartment(Department department) {
        if (department.getId() != null) {
            log.error("Department already exists: {}", department.getName());
            throw new IllegalArgumentException(Constants.DEPARTMENT_ALREADY_EXISTS);
        }
        log.info("Department to be created: {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Department modifyDepartment(Department department) {
        if (!departmentRepository.exists(department.getId())) {
            log.error("Department already exists: {}", department.getName());
            throw new IllegalArgumentException(Constants.DEPARTMENT_NOT_EXISTS);
        }
        log.info("Department to be modified: {}", department);
        return departmentRepository.save(department);
    }

    @Override
    public Department getOneById(Long id) {
        if (!departmentRepository.exists(id)) {
            log.error("No department exists with id: {}", id);
            throw new IllegalArgumentException(Constants.DEPARTMENT_NOT_EXISTS);
        }
        return departmentRepository.findOne(id);
    }


}
