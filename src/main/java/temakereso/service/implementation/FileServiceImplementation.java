package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.File;
import temakereso.helper.Constants;
import temakereso.repository.FileRepository;
import temakereso.service.FileService;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImplementation implements FileService {

    private final FileRepository fileRepository;

    @Override
    public Long saveFile(MultipartFile file) throws IOException {
        File savedFile = new File();
        savedFile.setName(file.getOriginalFilename());
        savedFile.setFile(file.getBytes());
        fileRepository.save(savedFile);
        return savedFile.getId();
    }

    @Override
    public File getOneById(Long id) {
        if (!fileRepository.exists(id)) {
            log.error("No file exists with id: {}", id);
            throw new IllegalArgumentException(Constants.FILE_NOT_EXISTS);
        }
        return fileRepository.findOne(id);
    }

}