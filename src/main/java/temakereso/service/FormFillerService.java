package temakereso.service;

import temakereso.helper.Form;

import java.io.ByteArrayOutputStream;

public interface FormFillerService {

    ByteArrayOutputStream fill(Form form);

    String generateFileName(Form form);

}
