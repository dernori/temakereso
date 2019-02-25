package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.FormType;
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
    @PutMapping(value = "/parameters/{type}", headers = ("content-type=multipart/*"))
    public ResponseEntity<Void> modifyForm(@PathVariable("type") FormType type, @RequestParam("file") MultipartFile file) {
        try {
            parameterService.modifyForm(type, file);
        } catch (IOException e) {
            log.error("Error while saving file!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
