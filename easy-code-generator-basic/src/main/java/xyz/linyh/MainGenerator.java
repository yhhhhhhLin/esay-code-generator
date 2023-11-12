package xyz.linyh;

import xyz.linyh.generator.DynamicGenerator;
import xyz.linyh.generator.StaticGenerator;
import xyz.linyh.model.AcmTemplateConfig;

import java.io.File;

public class MainGenerator {

    public static void main(String[] args) throws Exception{
        String currentPath = System.getProperty("user.dir");
//        生成静态文件
        String inputPath = new File(currentPath, "easy-generator-demo-projects/acm-template").getAbsolutePath();
        System.out.println(inputPath);
        String outputPath = currentPath;
        System.out.println(outputPath);
//        生成静态文件
        StaticGenerator.doGenerator(inputPath,outputPath);


//        生成动态文件 todo
        AcmTemplateConfig acmTemplateConfig = new AcmTemplateConfig();
        acmTemplateConfig.setLoop(false);
        acmTemplateConfig.setAuthorName("");
        acmTemplateConfig.setOutputText("");
        acmTemplateConfig.setPackageName("");


//        DynamicGenerator.doGenerator(inputPath,outputPath,acmTemplateConfig);
    }
}
