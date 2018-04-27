package temakereso.service.implementation;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import temakereso.entity.File;
import temakereso.repository.FileRepository;
import temakereso.service.FileService;

@Service
public class FileServiceImplementation implements FileService {

	@Autowired
	private FileRepository fileRepository;

	@Override
	public Long saveFile(MultipartFile file) throws IOException {
		File savedFile = new File();
		savedFile.setName(file.getOriginalFilename());
		savedFile.setFile(file.getBytes());
		fileRepository.save(savedFile);
		return savedFile.getId();
	}

	@Override
	public File getById(Long id) {
		return fileRepository.findOne(id);
	}	

}