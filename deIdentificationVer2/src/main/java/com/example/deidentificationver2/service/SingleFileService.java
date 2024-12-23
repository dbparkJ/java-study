package com.example.deidentificationver2.service;

import com.example.deidentificationver2.entity.FileEntity;
import com.example.deidentificationver2.repository.FileRepository;
import io.tus.java.client.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class SingleFileService {

    private final FileRepository fileRepository;

    @Value("${tus.filePath}")
    private URL tusURL;
    @Getter
    private String filetype;

    public String setFiletype(String filename) {
        String[] parts = filename.split("\\.");
        if (parts.length > 1) {
            this.filetype = parts[parts.length - 1];
        } else {
            this.filetype = "";
        }
        return filetype;
    }

    @Autowired
    public SingleFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public Map<String, String> FileMetaData(MultipartFile multipartFile) {

        String filename = multipartFile.getOriginalFilename();
        String filetype = setFiletype(Objects.requireNonNull(filename));
        Long filesize = multipartFile.getSize();

        Map<String, String> metaData = new HashMap<>();
        metaData.put("filename",filename);
        metaData.put("filetype",filetype);
        metaData.put("filesize",String.valueOf(filesize));

        return metaData;
    }
    public FileEntity saveDb(MultipartFile multipartFile) {
        FileEntity file = new FileEntity();
        file.setFileName(multipartFile.getOriginalFilename());
        file.setUploadTime(LocalDate.now());
        file.setFilePath(String.valueOf(tusURL));
        return fileRepository.save(file);
    }

    public void tusFileUpload(MultipartFile multipartFile, RedirectAttributes redirectAttributes)
            throws IOException, io.tus.java.client.ProtocolException {
        TusClient client = new TusClient();
        client.setUploadCreationURL(tusURL);
        client.enableResuming(new TusURLMemoryStore());

        InputStream inputStream = multipartFile.getInputStream();
        long totalBytes = multipartFile.getSize();

        final TusUpload upload = new TusUpload(); //멀티파트 파일 사용자로 부터 입력 받은거 넣어야함
        upload.setInputStream(inputStream);
        upload.setSize(totalBytes);
        upload.setFingerprint(multipartFile.getName());
        upload.setMetadata(FileMetaData(multipartFile));

        TusExecutor executor = new TusExecutor() {
            @Override
            protected void makeAttempt() throws ProtocolException, IOException, io.tus.java.client.ProtocolException {
                TusUploader uploader = client.resumeOrCreateUpload(upload);

                uploader.setChunkSize(1024 * 1024);
                while(uploader.uploadChunk() > -1){};
                // Allow the HTTP connection to be closed and cleaned up
                uploader.finish();
                redirectAttributes.addFlashAttribute("upload_message", "File name uploaded successfully : " + multipartFile.getName());
            }
        };
        executor.makeAttempts();
    }
}
