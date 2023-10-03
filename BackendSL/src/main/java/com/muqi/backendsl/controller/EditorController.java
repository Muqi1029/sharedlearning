package com.muqi.backendsl.controller;

import com.muqi.backendsl.model.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.muqi.backendsl.constant.EditorConstant.RETURN_PATH;
import static com.muqi.backendsl.constant.EditorConstant.UPLOAD_PATH;

@RestController
@RequestMapping("/editor")
public class EditorController {

    @PostMapping("/img")
    public ResultVO<?> uploadImgHandler(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResultVO.fail("未选择上传文件");
        }

        try {
            String fileName = file.getName();
            String filePath = UPLOAD_PATH + "image" +  File.separator + fileName;
            File dest = new File(filePath);
            if (!dest.exists()) {
                boolean result = dest.mkdirs();
                System.out.println(result);
            }
            file.transferTo(dest);
            return ResultVO.ok(RETURN_PATH + "/" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultVO.fail();
        }
    }

}
