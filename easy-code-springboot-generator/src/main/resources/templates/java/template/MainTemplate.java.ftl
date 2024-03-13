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
<#--        判断他如果存在condition字段，如果存在并且condition为false，那么直接遍历下一个list，其他情况就需要判断是什么类型的文件，如果是group类型的，那么需要写一个while循环所有files-->
    <#if fileInfo.condition??>
        if(dataModel.#{fileInfo.condition}){
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
        }
    <#else>
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
    </#if>

    </#list>
    }



}
