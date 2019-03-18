package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import temakereso.entity.Department;
import temakereso.repository.DepartmentRepository;
import temakereso.service.DepartmentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImplementation implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    // TODO validation!

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }


    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department modifyDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getOneById(Long id) {
        return departmentRepository.findOne(id);
    }


}
