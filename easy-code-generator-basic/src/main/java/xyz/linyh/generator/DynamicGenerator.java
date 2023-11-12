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

    public static void generator() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);

        String currentPath = System.getProperty("user.dir");
        File file = new File(currentPath, "/easy-code-generator-basic/src/main/resources/template");
        configuration.setDirectoryForTemplateLoading(file);
        configuration.setDefaultEncoding("utf-8");

        AcmTemplateConfig acmTemplateConfig = new AcmTemplateConfig();
        acmTemplateConfig.setLoop(false);
        acmTemplateConfig.setAuthorName("lin");
        acmTemplateConfig.setOutputText("总和为");

        Template template = configuration.getTemplate("AcmMainTemplate.java.ftl");
        template.process(acmTemplateConfig,new FileWriter("easy-code-generator-basic/src/main/java/xyz/linyh/generator/MainTemplate.java"));

    }

    public static void main(String[] args) {
        try {
            generator();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
