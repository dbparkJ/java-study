package hello.hellospring.service;

import hello.hellospring.domain.FileInfo;
import hello.hellospring.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileService {
    private final FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<FileInfo> findFiles() {
        return fileRepository.findAllName();
    }

    public Optional<FileInfo> findPath(String path) {
        return fileRepository.findByFilePath(path);
    }
}
