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

    // TODO
    Long getBscTopicFormId();

    // TODO
    Long getMscTopicFormId();

    // TODO
    Long getBscConsultationFormId();

    // TODO
    Long getMscConsultationFormId();

    // TODO
    void modifyForm(String identifier, MultipartFile file) throws IOException;

    // TODO
    Parameter findByIdentifier(String identifier);


}
