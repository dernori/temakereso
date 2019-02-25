package temakereso.service;

import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.FormType;

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
    Long getBscFormId();

    // TODO
    Long getMscFormId();

    // TODO
    void modifyForm(FormType type, MultipartFile file) throws IOException;

    // TODO
    Parameter findByIdentifier(String identifier);


}
