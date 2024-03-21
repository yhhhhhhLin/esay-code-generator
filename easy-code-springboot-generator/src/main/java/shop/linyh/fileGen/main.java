package shop.linyh.fileGen;

import shop.linyh.fileGen.generator.file.DynamicFileGenerator;
import shop.linyh.fileGen.meta.MetaManager;
import shop.linyh.fileGen.model.Meta;

import java.io.File;
import java.io.IOException;

/**
 * 前端传入meta里面的modal所有参数，后端根据里面的参数，创建freemarker程序，然后将所有参数传入，根据temp下面的freeMarker文件，生成新的项目，然后返回给前端生成后的文件
 */

/**
 * 用来生成制作项目的工具
 */
public class main {
    final static long tempId = 3L;
    final static String templateProjectName = "yhapi-backed";

    public static void main(String[] args) throws IOException {
        String workDir = System.getProperty("user.dir");
        String tempWorkDir = workDir+ File.separator+".temp"+ File.separator+tempId+File.separator+templateProjectName;
        Meta meta = MetaManager.getMeta(tempWorkDir + File.separator + "src" + File.separator + "main" + File.separator + "resources");
        String inputRootResourcePath = workDir + File.separator + "easy-code-springboot-generator"+File.separator+"src"+File.separator+"main"+File.separator+"resources";
        String outputRootPath = workDir + File.separator + "bootGenerated";
        generatorFile(inputRootResourcePath,outputRootPath,meta.getBasePackage(),meta);

    }


    protected static void generatorFile(String inputRootResourcePath, String outputRootPath, String basePackage, Meta meta) throws IOException {
        //        生成model文件
        String outPutJavaPath = outputRootPath + File.separator+ "src"+File.separator+"main"+File.separator+"java";
        String modelFtlPath = inputRootResourcePath + "/templates/java/model/DataModel.java.ftl";
        System.out.println(outputRootPath);
        String modelOutPutPath = outPutJavaPath + basePackage + "/model/DataModel.java";
        DynamicFileGenerator.doGenerate(modelFtlPath, modelOutPutPath, meta);

//        生成command下面的文件
        String configCommandInputPath = inputRootResourcePath + "/templates/java/command/file/ConfigCommand.java.ftl";
        String configCommandOutPutPath = outPutJavaPath + basePackage + "/command/file/ConfigCommand.java";
        DynamicFileGenerator.doGenerate(configCommandInputPath, configCommandOutPutPath, meta);

        String generateCommandInputPath = inputRootResourcePath + "/templates/java/command/file/GenerateCommand.java.ftl";
        String generateCommandOutPutPath = outPutJavaPath + basePackage + "/command/file/GenerateCommand.java";
        DynamicFileGenerator.doGenerate(generateCommandInputPath, generateCommandOutPutPath, meta);

        String listCommandInputPath = inputRootResourcePath + "/templates/java/command/file/ListCommand.java.ftl";
        String listCommandOutPutPath = outPutJavaPath + basePackage + "/command//file/ListCommand.java";
        DynamicFileGenerator.doGenerate(listCommandInputPath, listCommandOutPutPath, meta);

//        生成executor
        String executorInputPath = inputRootResourcePath + "/templates/java/executor/CommandExecutor.java.ftl";
        String executorOutPutPath = outPutJavaPath + basePackage + "/executor/CommandExecutor.java";
        DynamicFileGenerator.doGenerate(executorInputPath, executorOutPutPath, meta);

//        生成template
        String templateInputPath = inputRootResourcePath + "/templates/java/template/MainTemplate.java.ftl";
        String templateOutPutPath = outPutJavaPath + basePackage + "/template/MainTemplate.java";
        DynamicFileGenerator.doGenerate(templateInputPath, templateOutPutPath, meta);

//        生成Generator
        String dynamicGeneratorInputPath = inputRootResourcePath + "/templates/java/command/DynamicGenerator.java.ftl";
        String dynamicGeneratorOutPutPath = outPutJavaPath + basePackage + "/command/DynamicGenerator.java";
        DynamicFileGenerator.doGenerate(dynamicGeneratorInputPath, dynamicGeneratorOutPutPath, meta);

        String staticGeneratorInputPath = inputRootResourcePath + "/templates/java/command/StaticGenerator.java.ftl";
        String staticGeneratorOutPutPath = outPutJavaPath + basePackage + "/command/StaticGenerator.java";
        DynamicFileGenerator.doGenerate(staticGeneratorInputPath, staticGeneratorOutPutPath, meta);

//        生成utils类
        String utilsInputPath = inputRootResourcePath + "/templates/java/utils/CilUtils.java.ftl";
        String utilsOutPutPath = outPutJavaPath + basePackage + "/utils/CilUtils.java";
        DynamicFileGenerator.doGenerate(utilsInputPath, utilsOutPutPath, meta);

//        生成main
        String mainInputPath = inputRootResourcePath + "/templates/java/Main.java.ftl";
        String mainOutPutPath = outPutJavaPath + basePackage + "/Main.java";
        DynamicFileGenerator.doGenerate(mainInputPath, mainOutPutPath, meta);

//        生成pom
        String pomInputPath = inputRootResourcePath + "/templates/pom.xml.ftl";
        String pomOutPutPath = outputRootPath + "/pom.xml";
        DynamicFileGenerator.doGenerate(pomInputPath, pomOutPutPath, meta);
    }
}
