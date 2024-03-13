package ${basePackage}.generator.command;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ${basePackage}.generator.model.DataModel;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * 用来生成动态文件的
 *@author ${author}
 */
public class DynamicGenerator {

    /**
     * 用来生成里面需要插值的模板文件
     * @param inputPath 模板文件位置
     * @param outputPath 生成的文件位置
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerator(String inputPath, String outputPath, DataModel dataModel) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("utf-8");
        configuration.setDirectoryForTemplateLoading(new File(inputPath).getParentFile());

        if (!new File(outputPath).exists()) {
            FileUtil.touch(outputPath);
        }

//        获取模板文件的名称
        String templateName = new File(inputPath).getName();
        Template template = configuration.getTemplate(templateName);
        template.process(dataModel,new FileWriter(outputPath));
    }

}
