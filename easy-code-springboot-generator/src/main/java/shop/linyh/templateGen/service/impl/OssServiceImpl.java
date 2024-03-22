package shop.linyh.templateGen.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.linyh.templateGen.config.OssConfig;
import shop.linyh.templateGen.service.OssService;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Service("ossService")
public class OssServiceImpl implements OssService {

    @Override
    public String uploadImage(MultipartFile file) {
        String endpoint = OssConfig.END_POINT;
        String accessKeyId = OssConfig.ACCESS_KEY_ID;
        String accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
        String bucketName = OssConfig.BUCKET_NAME;
        try {
            //创建oss实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件的输入流
            InputStream fileInputStream = file.getInputStream();
            //获取原文件名
            String filename = file.getOriginalFilename();
            if (StrUtil.isBlank(filename)) {
                return null;
            }
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            filename = currentTime + filename;
            //上传到指定目录的指定文件
            filename = "oss/upload/" + filename;
            /*
             * 调用OSS实现文件的上传
             * 第一个参数:Bucket名称
             * 第二个参数:上传到oss的指定路径和文件名称, oss/image/xx.png
             * 第三个参数:上传文件输入流
             */
            ossClient.putObject(bucketName, filename, fileInputStream);
            //关闭OSSClient
            ossClient.shutdown();
            //返回上传到OSS的文件的URL，这里使用自定义域名，因此自己手动拼接路径
            return "https://oss域名前缀/" + filename;

        } catch (Exception e) {
            log.error("文件上传失败", e);
            return null;
        }
    }

    @Override
    public String uploadImage2(InputStream fileInputStream) {
        String endpoint = OssConfig.END_POINT;
        String accessKeyId = OssConfig.ACCESS_KEY_ID;
        String accessKeySecret = OssConfig.ACCESS_KEY_SECRET;
        String bucketName = OssConfig.BUCKET_NAME;
        //获取原文件名

        try {
            String filename = "test1";

            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
            filename = currentTime + filename;
            //上传到指定目录的指定文件
            filename = "oss/upload/" + filename;

            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            /*
             * 调用OSS实现文件的上传
             * 第一个参数:Bucket名称
             * 第二个参数:上传到oss的指定路径和文件名称, oss/image/xx.png
             * 第三个参数:上传文件输入流
             */
            ossClient.putObject(bucketName, filename, fileInputStream);
            //关闭OSSClient
            ossClient.shutdown();
            //返回上传到OSS的文件的URL，这里使用自定义域名，因此自己手动拼接路径
            return "https://oss域名前缀/" + filename;

        } catch (Exception e) {
            log.error("文件上传失败", e);
            return null;
        }
    }
}