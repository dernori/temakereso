package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.service.StudentService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class StudentController {

    private final StudentService studentService;

    // ------------------------ GET -------------------------- //

    /**
     * Returns a student selected by its id
     *
     * @param id
     * @return a student
     */
    @GetMapping(path = "/students/{id}")
    public StudentDto getStudent(@PathVariable(name = "id") Long id) {
        return studentService.getOneById(id);
    }

    @GetMapping(path = "/students/{id}/topics/applied")
    public Set<TopicDto> getStudentAppliedTopics(@PathVariable(name = "id") Long id) {
        return studentService.getAppliedTopicsByStudentId(id);
    }

    @GetMapping(path = "/students")
    public StudentDto getStudentByAccountId(@RequestParam(name = "accountId") Long accountId) {
        return studentService.findByAccountId(accountId);
    }

    // ------------------------ POST ------------------------- //

    /**
     * Saves the given student
     *
     * @param student to be saved
     * @return saved student
     */
    @PostMapping(path = "/students")
    public Long createStudent(@RequestBody Student student) {
        StudentDto savedStudent = studentService.createStudent(student);
        return savedStudent.getId();
    }

    // ------------------------ PUT -------------------------- //

//    /**
//     * Modifies the given student
//     *
//     * @param student to be modified
//     * @return modified student
//     */
//    @PutMapping(path = "/students")
//    public ResponseEntity<Student> modifyStudent(@RequestBody Student student) {
//        studentService.modifyStudent(student);
//        return new ResponseEntity<>(student, HttpStatus.OK);
//    }

}
