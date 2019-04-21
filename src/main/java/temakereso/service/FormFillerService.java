package temakereso.service;

import temakereso.helper.Form;

import java.io.ByteArrayOutputStream;

public interface FormFillerService {

    /**
     * Fills the file with the given data.
     *
     * @param form data
     * @return filled file data
     */
    ByteArrayOutputStream fill(Form form);

    /**
     * Generates name for the given form data.
     *
     * @param form form data to generate name for
     * @return name for the form
     */
    String generateFileName(Form form);

}
