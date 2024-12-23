package com.example.deidentificationver2.controller;

import com.example.deidentificationver2.service.SingleFileService;
import io.tus.java.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    private final SingleFileService singleFileService;
    @Autowired
    public FileController(SingleFileService singleFileService) {
        this.singleFileService = singleFileService;
    }

    @GetMapping("/upload")
    public String showPage() {
        return "upload";
    }

    @GetMapping("/multiFileupload")
    public String showMultiFileUpload() {
        return "multiFileupload";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes)
            throws ProtocolException, IOException {

        if (multipartFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("upload_message", "파일을 선택해주세요.");
            return "redirect:upload";
        }

        singleFileService.tusFileUpload(multipartFile, redirectAttributes);
        // DB 로직
        singleFileService.saveDb(multipartFile);

        return "upload";
    }

    @PostMapping("/multiFileupload")
    public String MultiFileUpload(@RequestParam("files") MultipartFile[] files,
                                  RedirectAttributes redirectAttributes)
            throws ProtocolException, IOException {
        if (files != null && files.length > 0) {
            redirectAttributes.addFlashAttribute("upload_message", "파일을 선택해주세요.");
            return "redirect:multiFileupload";
        }

        return "multiFileupload";
    }

}
