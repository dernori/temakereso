package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.entity.Student;
import temakereso.helper.StudentDto;
import temakereso.helper.TopicDto;
import temakereso.repository.StudentRepository;
import temakereso.service.StudentService;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImplementation implements StudentService {

    private final StudentRepository studentRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAll() {
        return studentRepository.findAll()
                .stream()
                .map(student -> modelMapper.map(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(Student student) {
        student.getAccount().setRoles(Arrays.asList(new Role("STUDENT")));
        student.getAccount().setPassword(passwordEncoder.encode(student.getAccount().getPassword()));
        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

    @Override
    public StudentDto modifyStudent(Student student) {
        return modelMapper.map(studentRepository.save(student), StudentDto.class);
    }

    @Override
    public StudentDto getOneById(Long id) {
        return modelMapper.map(studentRepository.findOne(id), StudentDto.class);
    }

    @Override
    public StudentDto getByUsername(String username) {
        return modelMapper.map(studentRepository.getByUsername(username), StudentDto.class);
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

    // DO NOT FORGET csak belső használatra, mapper nélkül!!
    @Override
    public Student findOneById(Long studentId) {
        return studentRepository.findOne(studentId);
    }

}
