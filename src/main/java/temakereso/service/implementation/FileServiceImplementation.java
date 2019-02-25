package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.File;
import temakereso.repository.FileRepository;
import temakereso.service.FileService;

import java.io.IOException;

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
        return fileRepository.findOne(id);
    }

}