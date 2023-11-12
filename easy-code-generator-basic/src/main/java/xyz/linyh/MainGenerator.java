package xyz.linyh;

import xyz.linyh.generator.DynamicGenerator;
import xyz.linyh.generator.StaticGenerator;
import xyz.linyh.model.AcmTemplateConfig;

import java.io.File;
import java.util.Scanner;

/**
 * 将easy-generator-projects 下面的acm-project文件夹根据自己的需求创建一个保存到easy-code-generator下
 */
public class MainGenerator {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        AcmTemplateConfig acmTemplateConfig = new AcmTemplateConfig();
        System.out.print("请选择是否循环:");
        acmTemplateConfig.setLoop(scanner.nextBoolean());
        System.out.print("请选择作者名称:");
        acmTemplateConfig.setAuthorName(scanner.nextLine());
        System.out.print("请选择输出结果前缀:");
        acmTemplateConfig.setOutputText(scanner.nextLine());

        String currentPath = System.getProperty("user.dir");
//        生成静态文件
        String inputPath = new File(currentPath, "easy-generator-demo-projects"+ File.separator+"acm-template").getAbsolutePath();
        System.out.println(inputPath);
        String outputPath = currentPath;
        System.out.println(outputPath);
//        生成静态文件
        StaticGenerator.doGenerator(inputPath,outputPath);


//        获取这个动态文件要保存到的位置
        String outputPath2 = new File(outputPath, "/acm-template/src/xyz/linyh/acm").toString();
        String inputPath2 = currentPath+ "/easy-code-generator-basic/src/main/resources/template/AcmMainTemplate.java.ftl";
        DynamicGenerator.doGenerator(inputPath2,outputPath2,acmTemplateConfig);
    }
}
