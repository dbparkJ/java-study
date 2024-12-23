package com.example.deidentificationver2.service;

import com.example.deidentificationver2.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;

@Service
public class MultiFileService {
    private final FileRepository fileRepository;

    @Value("${tus.filePath}")
    private URL tusURL;

    @Autowired
    public MultiFileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }


    public void handleFileUpload(MultipartFile[] files) {
        for (MultipartFile file : files) {
            System.out.println(file.getOriginalFilename());
        }
    }

}
