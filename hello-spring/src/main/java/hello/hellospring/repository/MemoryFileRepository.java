package hello.hellospring.repository;

import hello.hellospring.domain.FileInfo;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryFileRepository implements FileRepository{
    private static Map<String, FileInfo> file = new HashMap<>();

    @Override
    public Optional<FileInfo> findByFileName(String name) {
        return Optional.ofNullable(file.get(name));
    }

    @Override
    public Optional<FileInfo> findByFilePath(String path) {
        return Optional.ofNullable(file.get(path));
    }

    @Override
    public List<FileInfo> findAllName() {
        return new ArrayList<>(file.values());
    }
}
