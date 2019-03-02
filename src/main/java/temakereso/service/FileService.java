package temakereso.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import temakereso.entity.File;

public interface FileService {

    /**
     * Saves file to the database
     * @param file to be saved
     * @return if of the saved file
     * @throws IOException when file could not be saved
     */
    Long saveFile(MultipartFile file) throws IOException;

    /**
     * Returns a file by its id
     * @param id of file
     * @return file
     */
    File getOneById(Long id);

}