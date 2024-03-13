package xyz.linyh.maker;

import xyz.linyh.maker.generator.JarGenerator;
import xyz.linyh.maker.generator.ScriptGenerator;
import xyz.linyh.maker.generator.file.DynamicFileGenerator;
import xyz.linyh.maker.meta.Meta;
import xyz.linyh.maker.meta.MetaManager;
import java.io.File;
import java.io.IOException;


/**
 * 生成器的模板方法，可以按照doGenerator的方法进行生成，子类继承后，如果想要修改某一步的过程，就可以重写里面的方法
 */
public abstract class MainTemplate {

    public void doGenerator() throws Exception {

//        读取配置文件meta信息
        Meta meta = MetaManager.getMeta();

//        生成配置文件里面其他的数据
        String userDir = System.getProperty("user.dir");
        String basePackage = meta.getBasePackage().replace(".", File.separator) + "/generator";
        String outputRootPath = userDir + File.separator + meta.getFileConfig().getOutputRootPath() + File.separator + meta.getName();
        String inputRootResourcePath = meta.getFileConfig().getInputRootPath() + "/src/main/resources";
        String outPutJavaPath = outputRootPath + "/src/main/java" + File.separator;

//        生成对应的模板文件
        generatorFile(inputRootResourcePath, outputRootPath, outPutJavaPath, basePackage, meta);

//        生成jar包
        generatorJar(outputRootPath);

//        生成简单的脚本
        generatorScript(outputRootPath, meta);
    }

    protected void generatorScript(String outputRootPath, Meta meta) throws IOException {
        //        生成脚本
        String shellOutputFilePath = outputRootPath + File.separator + "generator";
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
    }

    protected void generatorJar(String outputRootPath) throws Exception {
        //        生成jar包
        JarGenerator.doGenerate(outputRootPath);
    }

    protected void generatorFile(String inputRootResourcePath, String outputRootPath, String outPutJavaPath, String basePackage, Meta meta) throws IOException {
        //        生成model文件
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
