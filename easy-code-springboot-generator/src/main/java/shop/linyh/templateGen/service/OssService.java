package shop.linyh.templateGen.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface OssService {
    public String uploadImage(MultipartFile file);

    public String getImage(String filePath);

    public String delImage(String filePath);
}
