package ${basePackage}.generator.template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import ${basePackage}.generator.model.DataModel;
import freemarker.template.TemplateException;
import ${basePackage}.generator.command.DynamicGenerator;
import ${basePackage}.generator.command.StaticGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
* 主生成类，需要生成的所有文件
* @author ${author}
*/
public class MainTemplate {


    private  static void generatorStatic(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(DataModel dataModel) throws IOException, TemplateException {
//        复制静态文件
//       遍历所有需要遍历的文件
        String userDir = System.getProperty("user.dir")+File.separator;
        String inputRootPath = "${fileConfig.inputRootPath}"+ File.separator;
        String outputRootPath = userDir+"${fileConfig.outputRootPath}"+ File.separator;

        String inputPath;
        String outputPath;
    <#list fileConfig.files as fileInfo>

    <#if fileInfo.generateType == "static">
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        System.out.printf("%s \n %s",inputPath,outputPath);
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else >
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        DynamicGenerator.doGenerator(inputPath, outputPath, dataModel);
    </#if>
    </#list>
    }



}
