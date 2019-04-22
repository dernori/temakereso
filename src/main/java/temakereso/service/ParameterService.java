package temakereso.service;

import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.ParameterDto;
import temakereso.helper.ParameterInputDto;

import java.io.IOException;
import java.util.List;

public interface ParameterService {

    /**
     * Returns all the parameters
     *
     * @return a list of parameters
     */
    List<ParameterDto> getAllModifiable();

    /**
     * Modifies the form connected to the given type of forms.
     *
     * @param identifier identifier of form
     * @param file       file connected to form
     * @throws IOException when file could not be saved
     */
    void modifyForm(String identifier, MultipartFile file) throws IOException;

    /**
     * Finds a parameter by its identifier.
     *
     * @param identifier identifier of parameter
     * @return parameter with the given identifier
     */
    Parameter findByIdentifier(String identifier);

    /**
     * Modifies parameter value.
     *
     * @param identifier   identifier of parameter
     * @param parameterDto contains value to be modified
     */
    void modifyParameter(String identifier, ParameterInputDto parameterDto);

    Long getBscTopicFormId();

    Long getMscTopicFormId();

    Long getBscConsultationFormId();

    Long getMscConsultationFormId();

    Integer getArchiveTimeout();

    Integer getStudentLoginTimeout();

    Integer getSupervisorLoginTimeout();

    Integer getSpringTermReminderMonth();

    Integer getSpringTermReminderDay();

    Integer getSummerTermReminderMonth();

    Integer getSummerTermReminderDay();

}
