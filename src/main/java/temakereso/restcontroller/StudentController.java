package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.entity.Student;
import temakereso.helper.StudentDto;
import temakereso.helper.StudentInputDto;
import temakereso.helper.TopicDto;
import temakereso.service.StudentService;

import java.util.List;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class StudentController {

    private final StudentService studentService;

    // ------------------------ GET -------------------------- //

    @PreAuthorize("hasRole('ROLE_STUDENT')")
    @GetMapping(path = "/students/{id}/topics/applied")
    public Set<TopicDto> getStudentAppliedTopics(@PathVariable(name = "id") Long id) {
        return studentService.getAppliedTopicsByStudentId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_SUPERVISOR', 'ROLE_STUDENT')")
    @GetMapping(path = "/students")
    public List<StudentDto> getStudents() {
        return studentService.getAll();
    }

    // ------------------------ POST ------------------------- //

    @PostMapping(path = "/students")
    public Long createStudent(@RequestBody Student student) {
        StudentDto savedStudent = studentService.createStudent(student);
        return savedStudent.getId();
    }

    // ------------------------ PUT ------------------------- //

    @PutMapping(path = "/students/{id}")
    public void modifyStudent(@PathVariable(name = "id") Long id, @RequestBody StudentInputDto student) {
        studentService.modifyStudent(id, student);
    }

}
