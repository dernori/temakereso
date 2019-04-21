package temakereso.service;

import temakereso.entity.Student;
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
     * Returns a collection of topics the student applied to.
     *
     * @param id id of student
     * @return collection of topics the student applied to
     */
    Set<TopicDto> getAppliedTopicsByStudentId(Long id);

    /**
     * Finds a student with the given account identifier.
     *
     * @param accountId identifier of account
     * @return student data
     */
    StudentDto findByAccountId(Long accountId);

    /**
     * Finds a student with the given identifier.
     *
     * @param studentId identifier of account
     * @return student data
     */
    Student findOneById(Long studentId);
}
