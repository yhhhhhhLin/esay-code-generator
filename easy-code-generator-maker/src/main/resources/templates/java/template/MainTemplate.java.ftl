package ${basePackage}.generator.template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import ${basePackage}.generator.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class MainTemplate {


    private  static void generatorStatic(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(DataModel dataModel) throws IOException, TemplateException {
//        复制静态文件
//       遍历所有需要遍历的文件
        String inputRootPath = "${fileConfig.inputRootPath}";
        String outputRootPath = "${fileConfig.outputRootPath}";

    String inputPath;
    String outputPath;
    <#list fileConfig.files as fileInfo>

    <#if fileInfo.type == "static">
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    <#else >
        inputPath = new File(inputRootPath, "${fileInfo.inputPath}").getAbsolutePath();
        outputPath = new File(outputRootPath, "${fileInfo.outputPath}").getAbsolutePath();
        DynamicGenerator.doGenerate(inputPath, outputPath, dataModel);
    </#if>
    </#list>
    }



}
