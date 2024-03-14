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


    static String inputRootPath;
    static String outputRootPath;

    private  static void generatorStatic(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(DataModel dataModel) throws IOException, TemplateException {
//        复制静态文件
//       遍历所有需要遍历的文件
        String userDir = System.getProperty("user.dir")+File.separator;
        inputRootPath = "${fileConfig.inputRootPath}"+ File.separator;
        outputRootPath = userDir+"${fileConfig.outputRootPath}"+ File.separator;

        String inputPath;
        String outputPath;
    <#list fileConfig.files as fileInfo>
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(${fileInfo},dataModel)
    </#list>
    }


    public static void recursionList(Meta.FileConfig.Files file,DataModel dataModel) {

        if (file.getCondition() == null || dataModal.get(file.getCondition())){
            if ("group".equals(file.getType())){
                for (Meta.FileConfig.Files files : file.getFiles()) {
                    recursionList(files,dataModal);
                }
            }
        //            如果是其他类型的，那么需要生成
            if("file".equals(file.getType())){
                String inputPath;
                String outputPath;
                if("static".equals(file.getGenerateType())){
                    inputPath = new File(inputRootPath,file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    System.out.printf("%s \n %s",inputPath,outputPath);
                    StaticGenerator.copyFilesByHutool(inputPath, outputPath);
                }else{
                    inputPath = new File(inputRootPath, file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    DynamicGenerator.doGenerator(inputPath, outputPath, dataModel);
                }
            }
        }
    }

}
