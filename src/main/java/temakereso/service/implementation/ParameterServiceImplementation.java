package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.Constants;
import temakereso.helper.ParameterDto;
import temakereso.helper.ParameterInputDto;
import temakereso.repository.ParameterRepository;
import temakereso.service.FileService;
import temakereso.service.ParameterService;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterServiceImplementation implements ParameterService {

    private final ParameterRepository parameterRepository;

    private final FileService fileService;

    private final ModelMapper modelMapper;

    @Override
    public List<ParameterDto> getAllModifiable() {
        List<Parameter> parameters = parameterRepository.findByModifiableTrue();
        return parameters
                .stream()
                .sorted(Comparator.comparing((Parameter p) -> p.getIdentifier()))
                .map(parameter -> modelMapper.map(parameter, ParameterDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Parameter findByIdentifier(String identifier) {
        if (!parameterRepository.existsByIdentifier(identifier)) {
            log.error("No parameter exists with identier: {}", identifier);
            throw new IllegalArgumentException(Constants.PARAMETER_NOT_EXISTS);
        }
        return parameterRepository.findByIdentifier(identifier);
    }

    @Override
    public void modifyParameter(String identifier, ParameterInputDto parameterDto) {
        Parameter parameter = parameterRepository.findByIdentifier(identifier);
        parameter.setValue(parameterDto.getValue());
        parameterRepository.save(parameter);
    }

    @Override
    public Long getBscTopicFormId() {
        return Long.parseLong(findByIdentifier("BSC_TOPIC_FORM").getValue());
    }

    @Override
    public Long getMscTopicFormId() {
        return Long.parseLong(findByIdentifier("MSC_TOPIC_FORM").getValue());
    }

    @Override
    public Long getBscConsultationFormId() {
        return Long.parseLong(findByIdentifier("BSC_CONSULTATION_FORM").getValue());
    }

    @Override
    public Long getMscConsultationFormId() {
        return Long.parseLong(findByIdentifier("MSC_CONSULTATION_FORM").getValue());
    }

    @Override
    public Integer getArchiveTimeout() {
        return Integer.parseInt(findByIdentifier("ARCHIVE_TIMEOUT_IN_MONTHS").getValue());
    }

    @Override
    public Integer getStudentLoginTimeout() {
        return Integer.parseInt(findByIdentifier("STUDENT_LOGIN_TIMEOUT_IN_MONTH").getValue());
    }

    @Override
    public Integer getSupervisorLoginTimeout() {
        return Integer.parseInt(findByIdentifier("SUPERVISOR_LOGIN_TIMEOUT_IN_MONTH").getValue());
    }

    @Override
    public Integer getSpringTermReminderMonth() {
        return Integer.parseInt(findByIdentifier("SPRING_TERM_REMINDER_MONTH").getValue());
    }

    @Override
    public Integer getSpringTermReminderDay() {
        return Integer.parseInt(findByIdentifier("SPRING_TERM_REMINDER_DAY").getValue());
    }

    @Override
    public Integer getSummerTermReminderMonth() {
        return Integer.parseInt(findByIdentifier("SUMMER_TERM_REMINDER_MONTH").getValue());
    }

    @Override
    public Integer getSummerTermReminderDay() {
        return Integer.parseInt(findByIdentifier("SUMMER_TERM_REMINDER_DAY").getValue());
    }

    @Override
    public void modifyForm(String identifier, MultipartFile file) throws IOException {
        Long id = fileService.saveFile(file);
        Parameter parameter = parameterRepository.findByIdentifier(identifier);
        parameter.setValue(id.toString());
        parameterRepository.save(parameter);
    }

}
