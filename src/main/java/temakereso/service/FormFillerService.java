package temakereso.service;

import java.io.ByteArrayOutputStream;

import temakereso.helper.Form;

public interface FormFillerService {

    ByteArrayOutputStream fill(Form form);

    String generateFileName(Form form);

}
