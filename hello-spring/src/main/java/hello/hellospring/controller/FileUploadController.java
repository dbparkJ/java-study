package hello.hellospring.controller;

import hello.hellospring.domain.FileInfo;
import hello.hellospring.repository.FileRepository;
import hello.hellospring.repository.MemoryFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.stream.Location;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final FileRepository fileRepository;

    @Autowired
    public FileUploadController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostMapping("upload_2")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("upload_message", "파일을 선택해주세요.");
            return "redirect:/uploads";
        }

        try {
            // 업로드할 폴더 경로 설정
            String uploadDir = "C:\\Users\\JM\\Desktop\\test";

            // 파일 저장
            String fileName = file.getOriginalFilename();
            File dest = new File(uploadDir + File.separator + fileName);
            file.transferTo(dest);

            redirectAttributes.addFlashAttribute("message", "파일 업로드 성공: " + fileName);
            // Flask API 엔드포인트
            String flaskEndpoint = "http://111.111.111.79:5000/receiveFilePath";

            // Flask로 폴더 경로 데이터를 POST 요청으로 보내기
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForEntity(flaskEndpoint, uploadDir, String.class);

        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "파일 업로드 실패: " + file.getOriginalFilename());
        }

        return "redirect:uploads";
    }

    @GetMapping("uploads")
    public String listUploadedFiles(Model model) {
        String uploadDir = "/home/geon_lab/AI_PARK/web/upload";
        File uploadFolder = new File(uploadDir);
        File[] uploadedFiles = uploadFolder.listFiles();

        if (uploadedFiles != null) {
            List<FileInfo> fileInfoList = Arrays.stream(uploadedFiles)
                    .map(file -> {
                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setName(file.getName());
                        fileInfo.setPath(file.getPath());
                        return fileInfo;
                    })
                    .collect(Collectors.toList());
            model.addAttribute("fileInfoList", fileInfoList);
        }

        return "file/upload_2";
    }

}
