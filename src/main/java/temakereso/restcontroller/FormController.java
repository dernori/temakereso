package temakereso.restcontroller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import temakereso.helper.ConsultationForm;
import temakereso.helper.Form;
import temakereso.helper.TopicForm;
import temakereso.service.FormFillerService;

import java.io.ByteArrayOutputStream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class FormController {

    private final FormFillerService formFillerService;

    /*----------- GET ------------*/

    @GetMapping(value = "/forms/topic", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> fillTopicForm(TopicForm form) {
        return fillForm(form);
    }

    @GetMapping(value = "/forms/consultation", produces = "application/octet-stream")
    public ResponseEntity<ByteArrayResource> fillConsultationForm(ConsultationForm form) {
        return fillForm(form);
    }

    private ResponseEntity<ByteArrayResource> fillForm(Form form) {
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
