package xyz.linyh.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import xyz.linyh.model.AcmTemplateConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 用来生成动态文件的
 */
public class DynamicGenerator {

    /**
     * 用来生成里面需要插值的模板文件
     * @param inputPath 模板文件位置
     * @param outputPath 生成的文件位置
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerator(String inputPath, String outputPath, AcmTemplateConfig acmTemplateConfig) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(inputPath));

//        String currentPath = System.getProperty("user.dir");
//        File file = new File(currentPath, "/easy-code-generator-basic/src/main/resources/template");

//        获取模板文件的名称
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);
//        template.process(acmTemplateConfig,new FileWriter("easy-code-generator-basic/src/main/java/xyz/linyh/generator/MainTemplate.java"));
        template.process(acmTemplateConfig,new FileWriter(outputPath));

    }

    public static void main(String[] args) {
        try {
            DynamicGenerator dynamicGenerator = new DynamicGenerator();
            Class<? extends DynamicGenerator> aClass = dynamicGenerator.getClass();

            AcmTemplateConfig acmTemplateConfig = new AcmTemplateConfig();
            acmTemplateConfig.setLoop(false);
            acmTemplateConfig.setAuthorName("lin");
            acmTemplateConfig.setOutputText("总和为");
            acmTemplateConfig.setPackageName(aClass.getPackageName());
//            doGenerator(acmTemplateConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
