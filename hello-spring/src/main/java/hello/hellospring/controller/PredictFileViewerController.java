package hello.hellospring.controller;

import hello.hellospring.domain.FileInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PredictFileViewerController {

    @GetMapping("/predictView")
    public String predictFile(Model model) {
        String predictViewDir = "C:\\Users\\JM\\Desktop\\test";
        File predictFile = new File(predictViewDir);
        File[] predictList = predictFile.listFiles();

        if (predictList != null) {
            List<FileInfo> fileInfoList = Arrays.stream(predictList)
                    .map(file -> {
                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setName(file.getName());
                        fileInfo.setPath(file.getPath());
                        return fileInfo;
                    })
                    .collect(Collectors.toList());
            model.addAttribute("fileInfoList", fileInfoList);
        }


        return "file/predictView";
    }

}
