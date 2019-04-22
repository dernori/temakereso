package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.ParameterDto;
import temakereso.helper.ParameterInputDto;
import temakereso.service.ParameterService;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParameterController {

    private final ParameterService parameterService;

    /*----------- GET ------------*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/parameters")
    public List<ParameterDto> getParameters() {
        return parameterService.getAllModifiable();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/parameters/{identifier}")
    public String getParameterValueByIdentifier(@PathVariable("identifier") String identifier) {
        Parameter parameter = parameterService.findByIdentifier(identifier);
        return parameter.getValue();
    }

    /*----------- PUT ------------*/

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/parameters/forms/{identifier}", headers = ("content-type=multipart/*"))
    public ResponseEntity<Void> modifyFormParameter(@PathVariable("identifier") String identifier, @RequestParam("file") MultipartFile file) {
        try {
            parameterService.modifyForm(identifier, file);
        } catch (IOException e) {
            log.error("Error while saving file!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/parameters/{identifier}")
    public void modifyParameter(@PathVariable("identifier") String identifier, @RequestBody ParameterInputDto parameterInputDto) {
        parameterService.modifyParameter(identifier, parameterInputDto);
    }

}
