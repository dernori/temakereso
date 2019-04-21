package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.entity.Student;
import temakereso.helper.Constants;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.repository.StudentRepository;
import temakereso.service.RoleService;
import temakereso.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(Student student) {
        if (student.getId() != null) {
            log.error("Student has id: {}", student.getId());
            throw new IllegalArgumentException(Constants.STUDENT_ALREADY_EXISTS);
        }
        Role studentRole = roleService.findByName("STUDENT");
        student.getAccount().setRoles(Arrays.asList(studentRole != null ? studentRole : new Role("STUDENT")));
        student.getAccount().setPassword(passwordEncoder.encode(student.getAccount().getPassword()));
        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

    @Override
    public StudentDto getOneById(Long id) {
        return modelMapper.map(studentRepository.findOne(id), StudentDto.class);
    }

    @Override
    public Set<TopicDto> getAppliedTopicsByStudentId(Long id) {
        Student student = studentRepository.getOne(id);
        return student.getTopics()
                .stream()
                .filter(topic -> topic.getStudent() == null)
                .map(topic -> modelMapper.map(topic, TopicDto.class))
                .collect(Collectors.toSet());
    }

    @Override
    public StudentDto findByAccountId(Long accountId) {
        Student student = studentRepository.findByAccountId(accountId);
        return student != null ? modelMapper.map(student, StudentDto.class) : null;
    }

    @Override
    public Student findOneById(Long studentId) {
        return studentRepository.findOne(studentId);
    }

}
