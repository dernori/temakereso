package temakereso.restcontroller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import temakereso.entity.Attachment;
import temakereso.entity.File;
import temakereso.service.FileService;

@RestController
@RequestMapping("/api")
public class FileController {

	public static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@Autowired
	private FileService fileService;

	/*----------- GET ------------*/

	/**
	 * Get a single file by id
	 * @param Long id - id of the file
	 * @return ByteArrayResource file as a response
	 */
	@GetMapping(value = "/files/{id}")
	public ResponseEntity<ByteArrayResource> getFile(@PathVariable("id") Long id) {
        File file = fileService.getById(id);
        ByteArrayResource resource = new ByteArrayResource(file.getFile());
        HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" +file.getName());
        return ResponseEntity.ok()
        		.headers(headers)
                .contentLength(file.getFile().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

	/*----------- POST ------------*/

	/**
	 * Uploads a new file to the database
	 * @param MultipartFile file
	 * @return id of the uploaded file and name in a simple Attachment object
	 */
	@PostMapping(value = "/files", headers=("content-type=multipart/*"))
	public ResponseEntity<Attachment> uploadFile(@RequestParam("file") MultipartFile file) {
        Long id = null;
		try {
			id = fileService.saveFile(file);
		} catch (IOException e) {
			logger.error("Error while saving file!");
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
        return new ResponseEntity<>(new Attachment(id, file.getOriginalFilename()), HttpStatus.OK);

    }
}
