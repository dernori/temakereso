package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Section;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Service;
import temakereso.helper.Constants;
import temakereso.helper.ConsultationForm;
import temakereso.helper.Form;
import temakereso.helper.FormLevel;
import temakereso.helper.TopicForm;
import temakereso.service.FileService;
import temakereso.service.FormFillerService;
import temakereso.service.ParameterService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormFillerServiceImplementation implements FormFillerService {

    private HWPFDocument doc;

    private final ParameterService parameterService;

    private final FileService fileService;

    @Override
    public ByteArrayOutputStream fill(Form form) {

        byte[] file = null;
        POIFSFileSystem fs = null;

        try {
            file = getFileToFill(form);
            fs = new POIFSFileSystem(new ByteArrayInputStream(file));
            doc = new HWPFDocument(fs);
            Map<String, String> values = form.convertToMap();
            for (String key : values.keySet()) {
                replaceText("$" + key.replaceAll("([A-Z])", "_$1").toUpperCase(), (values.get(key) == null ? "" : values.get(key)));
            }
            return saveWord();
        } catch (IOException e) {
            log.error("Error while filling the form:", e);
            throw new IllegalArgumentException(Constants.FORM_FILLING_ERROR);
        }
    }

    private byte[] getFileToFill(Form form) {
        Long id = null;
        if (form instanceof TopicForm) {
            id = (form.getLevel() == FormLevel.BSC_FORM ? parameterService.getBscTopicFormId() : parameterService.getMscTopicFormId());
        } else if (form instanceof ConsultationForm) {
            id = (form.getLevel() == FormLevel.BSC_FORM ? parameterService.getBscConsultationFormId() : parameterService.getMscConsultationFormId());
        } else {
            return null;
        }
        return fileService.getOneById(id).getFile();
    }

    private void replaceText(String findText, String replaceText) {
        Range r1 = doc.getRange();

        for (int i = 0; i < r1.numSections(); ++i) {
            Section s = r1.getSection(i);
            for (int x = 0; x < s.numParagraphs(); x++) {
                Paragraph p = s.getParagraph(x);
                for (int z = 0; z < p.numCharacterRuns(); z++) {
                    CharacterRun run = p.getCharacterRun(z);
                    String text = run.text();
                    if (text.contains(findText)) {
                        run.replaceText(findText, replaceText);
                    }
                }
            }
        }
    }

    private ByteArrayOutputStream saveWord() throws IOException {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            doc.write(out);
        } finally {
            doc.close();
            out.close();
        }
        return out;
    }

    @Override
    public String generateFileName(Form form) {
        return form.getFileName();
    }
}
