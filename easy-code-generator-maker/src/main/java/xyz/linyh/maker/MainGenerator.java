package xyz.linyh.maker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import xyz.linyh.maker.generator.file.DynamicFileGenerator;
import xyz.linyh.maker.generator.file.StaticFileGenerator;
import xyz.linyh.maker.meta.DataModel;
import xyz.linyh.maker.meta.Meta;
import xyz.linyh.maker.meta.MetaManager;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * 将easy-generator-projects 下面的acm-project文件夹根据自己的需求创建一个保存到easy-code-generator下
 */
public class MainGenerator {

    public static void main(String[] args) throws Exception{

        Scanner scanner = new Scanner(System.in);
        DataModel acmTemplateConfig = new DataModel();
        System.out.print("请选择是否循环(true/false):");
        acmTemplateConfig.setLoop(scanner.nextBoolean());
        System.out.print("请选择作者名称(字符串):");
        acmTemplateConfig.setAuthorName(scanner.next());
        System.out.print("请选择输出结果前缀(字符串):");
        acmTemplateConfig.setOutputText(scanner.next());

        String currentPath = System.getProperty("user.dir");
//        生成静态文件
        String inputPath = new File(currentPath, "easy-generator-demo-projects"+ File.separator+"acm-templates").getAbsolutePath();
        System.out.println(inputPath);
        String outputPath = currentPath;
        System.out.println(outputPath);
//        生成静态文件(递归复制input目录下的所有文件)
        StaticFileGenerator.doGenerate(inputPath,outputPath);


//        获取这个动态文件要保存到的位置
        String outputPath2 = new File(outputPath, "/acm-templates/src/xyz/linyh/acm").toString();
//        获取模板文件的位置
        String inputPath2 = currentPath+ "/easy-code-generator-basic/src/main/resources/templates/AcmMainTemplate.java.ftl";
        DynamicFileGenerator.doGenerate(inputPath2,outputPath2,acmTemplateConfig);
    }

//    public static void main(String[] args) throws Exception {
//////        获取配置文件数据
//        Meta meta = MetaManager.getMeta();
//        System.out.println(meta);
//        String userDir = System.getProperty("user.dir");
////        TODO 增加判断文件夹是否存在
////        TODO 都应该是读取meta文件获取这些魔法值
//        String inputPath = new File(userDir, "easy-code-generator-maker/src/main/resources/templates/java/model/DataModel.java.ftl").getAbsolutePath();
//        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
//        configuration.setDirectoryForTemplateLoading(new File(inputPath).getParentFile());
//        configuration.setDefaultEncoding("UTF-8");
//        Template template = configuration.getTemplate(new File(inputPath).getName());
//
//        template.process(meta,new FileWriter(new File(userDir, "easy-code-generator-maker/src/main/java/xyz/linyh/maker/meta/DataModel2.java")));
//
//
//
//    }
}
