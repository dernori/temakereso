package temakereso.service;

import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;

import java.io.IOException;
import java.util.List;

public interface ParameterService {

    /**
     * Returns all the parameters
     *
     * @return a list of parameters
     */
    List<Parameter> getAll();

    /**
     * Returns the id of the BSc topic form
     *
     * @return id of the BSc topic form
     */
    Long getBscTopicFormId();

    /**
     * Returns the id of the MSc topic form
     *
     * @return id of the MSc topic form
     */
    Long getMscTopicFormId();

    /**
     * Returns the id of the BSc consultation form
     *
     * @return id of the BSc consultation form
     */
    Long getBscConsultationFormId();

    /**
     * Returns the id of the MSc consultation form
     *
     * @return id of the MSc consultation form
     */
    Long getMscConsultationFormId();

    /**
     * Returns the timeout of topic archiving.
     *
     * @return timeout of topic archiving
     */
    Integer getArchiveTimeout();

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
}
