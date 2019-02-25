package temakereso.service;

import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;

import java.util.List;
import java.util.Set;

public interface StudentService {

    /**
     * Returns all the students
     *
     * @return a list of students
     */
    List<StudentDto> getAll();

    /**
     * Returns a student specified by its id
     *
     * @param id
     * @return the selected student
     */
    StudentDto getOneById(Long id);

    /**
     * Creates a new student
     *
     * @param student
     * @return the saved student
     */
    StudentDto createStudent(Student student);

    /**
     * Modifies a student
     *
     * @param student
     * @return the modified student
     */
    StudentDto modifyStudent(Student student);

    /**
     * Returns a student specified by its username
     *
     * @param username
     * @return the selected student
     */
    StudentDto getByUsername(String username);

    Set<TopicDto> getAppliedTopicsByStudentId(Long id);

    StudentDto findByAccountId(Long accountId);

    Student findOneById(Long studentId);
}
