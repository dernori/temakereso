package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.FormType;
import temakereso.repository.ParameterRepository;
import temakereso.service.FileService;
import temakereso.service.ParameterService;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParameterServiceImplementation implements ParameterService {

    private final ParameterRepository parameterRepository;

    private final FileService fileService;

    @Override
    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter findByIdentifier(String identifier) {
        return parameterRepository.findByIdentifier(identifier);
    }

    @Override
    public Long getBscFormId() {
        return Long.parseLong(parameterRepository.findByIdentifier("BSC_FORM").getValue());
    }

    @Override
    public Long getMscFormId() {
        return Long.parseLong(parameterRepository.findByIdentifier("MSC_FORM").getValue());
    }

    @Override
    public void modifyForm(FormType type, MultipartFile file) throws IOException {
        Long id = fileService.saveFile(file);
        Parameter parameter = parameterRepository.findByIdentifier(type.name());
        parameter.setValue(id.toString());
        parameterRepository.save(parameter);
    }

}
