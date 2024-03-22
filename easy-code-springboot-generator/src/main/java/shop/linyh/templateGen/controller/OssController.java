package shop.linyh.templateGen.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.linyh.templateGen.service.OssService;

import java.io.BufferedInputStream;
import java.io.File;

@RestController
public class OssController {

    @Autowired
    private OssService ossService;

    @GetMapping("/upload")
    public void uploadToOss(){
        BufferedInputStream inputStream = FileUtil.getInputStream("D:\\图片\\4e3550b419ee468309704070924447e0_t.gif");
        ossService.uploadImage2(inputStream);
    }
}
