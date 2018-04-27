package temakereso.restcontroller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import temakereso.helper.FormType;
import temakereso.service.ParameterService;

@RestController
@RequestMapping("/api")
public class ParameterController {

	public static final Logger logger = LoggerFactory.getLogger(ParameterController.class);

	@Autowired
	private ParameterService parameterService;

	/*----------- GET ------------*/

	/**
		// TODO
	 */
	@GetMapping(value = "/parameters/{identifier}")
	public ResponseEntity<String> getParameterValueByIdentifier(@PathVariable("identifier") String identifier) {
		Parameter parameter = parameterService.findByIdentifier(identifier);
		return new ResponseEntity<>(parameter.getValue(), HttpStatus.OK);
	}

	
	/*----------- PUT ------------*/

	/**
		// TODO
	 */
	@PutMapping(value = "/parameters/{type}", headers=("content-type=multipart/*"))
	public ResponseEntity<Void> modifyForm(@PathVariable("type") FormType type, @RequestParam("file") MultipartFile file) {
		try {
			parameterService.modifyForm(type, file);
		} catch (IOException e) {
			logger.error("Error while saving file!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
