package temakereso.restcontroller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import temakereso.helper.Form;
import temakereso.service.FormFillerService;

@RestController
@RequestMapping("/api")
public class FormController {

	public static final Logger logger = LoggerFactory.getLogger(FormController.class);
	
	@Autowired
	private FormFillerService formFillerService;
	
	/*----------- GET ------------*/
	
	/**
		TODO
	 */
	@GetMapping(value = "/fillform", produces="application/octet-stream")
    public ResponseEntity<ByteArrayResource> fillForm(Form form) throws IOException {
		
		ByteArrayOutputStream stream = formFillerService.fill(form);
		byte[] file = stream.toByteArray();
		ByteArrayResource resource = new ByteArrayResource(file);

	    HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + formFillerService.generateFileName(form));
	    return ResponseEntity.ok()
	    		.headers(headers)
	            .contentLength(file.length)
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(resource);
	}
}
