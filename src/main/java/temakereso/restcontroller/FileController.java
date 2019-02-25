package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Attachment;
import temakereso.entity.File;
import temakereso.service.FileService;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {

    private final FileService fileService;

    /*----------- GET ------------*/

    /**
     * Get a single file by id
     *
     * @param id of the file
     * @return ByteArrayResource file as a response
     */
    @GetMapping(value = "/files/{id}")
    public ResponseEntity<ByteArrayResource> getFile(@PathVariable("id") Long id) {
        File file = fileService.getOneById(id);
        ByteArrayResource resource = new ByteArrayResource(file.getFile());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.getFile().length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
    }

    /*----------- POST ------------*/

    /**
     * Uploads a new file to the database
     *
     * @param file to upload
     * @return id of the uploaded file and name in a simple Attachment object
     */
    @PostMapping(value = "/files", headers = ("content-type=multipart/*"))
    public ResponseEntity<Attachment> uploadFile(@RequestParam("file") MultipartFile file) {
        Long id = null;
        try {
            id = fileService.saveFile(file);
        } catch (IOException e) {
            log.error("Error while saving file!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new Attachment(id, file.getOriginalFilename()), HttpStatus.OK);

    }
}
