package xyz.linyh.generator;

import cn.hutool.core.io.FileUtil;
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
        configuration.setDirectoryForTemplateLoading(new File(inputPath).getParentFile());

//        获取模板文件的名称
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);
//        如果输出路径不存在，那么就创建
        if (!new File(outputPath).exists()) {
            FileUtil.touch(outputPath);
        }
        template.process(acmTemplateConfig,new FileWriter(outputPath+ File.separator+"MainTemplate.java"));

    }

    public static void main(String[] args) {
        try {
            AcmTemplateConfig acmTemplateConfig = new AcmTemplateConfig();
            acmTemplateConfig.setLoop(false);
            acmTemplateConfig.setAuthorName("lin");
            acmTemplateConfig.setOutputText("总和为");
//            acmTemplateConfig.setPackageName(aClass.getPackageName());
//            doGenerator(acmTemplateConfig);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
