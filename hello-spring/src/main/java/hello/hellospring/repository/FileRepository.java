package hello.hellospring.repository;

import hello.hellospring.domain.FileInfo;

import java.util.List;
import java.util.Optional;

public interface FileRepository {
    Optional<FileInfo> findByFileName(String name);

    Optional<FileInfo> findByFilePath(String path);

    List<FileInfo> findAllName();
}
