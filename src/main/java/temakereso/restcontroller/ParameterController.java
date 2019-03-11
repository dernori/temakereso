package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.service.ParameterService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ParameterController {

    private final ParameterService parameterService;

    /*----------- GET ------------*/

    /**
     * // TODO
     */
    @GetMapping(value = "/parameters/{identifier}")
    public String getParameterValueByIdentifier(@PathVariable("identifier") String identifier) {
        Parameter parameter = parameterService.findByIdentifier(identifier);
        return parameter.getValue();
    }

    /*----------- PUT ------------*/

    /**
     * // TODO
     */
    @PutMapping(value = "/parameters/{identifier}", headers = ("content-type=multipart/*"))
    public ResponseEntity<Void> modifyForm(@PathVariable("identifier") String identifier, @RequestParam("file") MultipartFile file) {
        try {
            parameterService.modifyForm(identifier, file);
        } catch (IOException e) {
            log.error("Error while saving file!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
