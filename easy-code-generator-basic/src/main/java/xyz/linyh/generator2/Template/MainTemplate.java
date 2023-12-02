package xyz.linyh.generator2.Template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class MainTemplate {


    private  static void generatorStatic(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(TemplateConfig templateConfig) throws IOException, TemplateException {
//        复制静态文件
        String parentPath = System.getProperty("user.dir");
        File file = new File(parentPath + File.separator+"easy-generator-demo-projects"+File.separator+"acm-template");
        generatorStatic(file.getAbsolutePath(),parentPath);

//        获取模板路径
        String templatePath = parentPath+"/easy-code-generator-basic/src/main/resources/template/AcmMainTemplate.java.ftl";
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("utf-8");
        System.out.println(new File(templatePath).getParentFile());
//        TODO
        configuration.setDirectoryForTemplateLoading(new File(templatePath).getParentFile());

//        获取模板文件
        String name = new File(templatePath).getName();
        Template template = configuration.getTemplate(name);

//        输出到对应的位置
        String outputPath = parentPath+"/acm-template/src/xyz/linyh/acm/MainTemplate.java";
        template.process(templateConfig,new FileWriter(outputPath));


//        将template模板文件赋值并复制到对应位置

    }

    public static void main(String[] args) {
        TemplateConfig config = new TemplateConfig();
        config.setLoop(false);
        config.setAuthorName("linyhzz");
        config.setOutputText("hello world::");
        try {
            doGenerator(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }
    }

}
