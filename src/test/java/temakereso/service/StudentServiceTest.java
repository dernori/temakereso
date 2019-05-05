package temakereso.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import temakereso.entity.Student;
import temakereso.service.implementation.StudentServiceImplementation;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

    private static Student studentWithId;

    @InjectMocks
    private StudentServiceImplementation studentService;

    @Before
    public void setUp() {
        studentWithId = new Student();
        studentWithId.setId(0L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenStudentWithIdToCreate_thenThrowsException() {
        studentService.createStudent(studentWithId);
    }

}
