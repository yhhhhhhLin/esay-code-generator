package shop.linyh.templateGen.marker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import shop.linyh.templateGen.model.FileTypeEnum;
import shop.linyh.templateGen.model.Meta;
import shop.linyh.templateGen.model.Meta.FileConfig;
import shop.linyh.templateGen.model.Meta.FileConfig.Files;
import shop.linyh.templateGen.model.Meta.ModelsConfig;
import shop.linyh.templateGen.model.Meta.ModelsConfig.Models;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用于生成模板的工具类，将原先项目挖空后生成对应的ftl文件或json文件等等
 */
public class TemplateMaker2Test {

//    public static void genTemplateFile(Long pathId) {
//
//        /**
//         * 可以复制单个ftl文件和挖一个空
//         */
//        String inputPath = "src" + File.separator + "xyz" + File.separator + "linyh" + File.separator + "acm" + File.separator + "MainTemplate.java";
//
//        String userWorkPath = System.getProperty("user.dir");
//        String projectPath = userWorkPath + File.separator + "easy-generator-demo-projects" + File.separator + "acm-template" + File.separator;
////        生成的模板文件先保存到temp目录下，如果每一次新增的id相同的话，那么就需要把原先的模板先复制过去，然后生成新的ftl文件
//        String outputRootPath = userWorkPath + File.separator + ".temp" + File.separator + pathId + File.separator + "acm-template" + File.separator;
//
//        String tempDir = userWorkPath + File.separator + ".temp" + File.separator + pathId;
//
//        if (!FileUtil.exist(tempDir)) {
//            FileUtil.mkdir(tempDir);
////            复制模板总框架过来
//            FileUtil.copy(projectPath, tempDir, true);
//        }
//
////        获取要复制的模板源文件
//        String filePath = projectPath + inputPath;
//        String fileOutputName = outputRootPath + inputPath + ".ftl";
//
//        String oldStrContent = FileUtil.readUtf8String(filePath);
//        String searchStr = "Sum: ";
//        String fieldName = "outputText";
//        String format = String.format("${%s}", fieldName);
//        String newContent = StrUtil.replace(oldStrContent, searchStr, format);
//        FileUtil.writeUtf8String(newContent, fileOutputName);
//
////        创建meta.json文件
//        Meta meta = new Meta();
//        meta.setName("acm模板");
//        meta.setDescription("模板生成器");
//        FileConfig fileConfig = new FileConfig();
//        fileConfig.setInputRootPath(projectPath + inputPath);
//        fileConfig.setOutputRootPath(outputRootPath + fileOutputName);
//
//        meta.setFileConfig(fileConfig);
//        ModelsConfig modelsConfig = new ModelsConfig();
//        ArrayList<Models> models = new ArrayList<>();
//        models.add(new Models("acm", "acm"));
//        modelsConfig.setModels(models);
//        meta.setModelConfig(modelsConfig);
//        System.out.println(JSONUtil.toJsonPrettyStr(meta));
//        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(meta), outputRootPath + File.separator+"src"+File.separator+"resource"+File.separator+"meta.json");
//
//
//    }


    /**
     * 可以对同一个ftl文件进行多次挖空，还可以追加meta里面的配置信息
     *
     * @param pathId
     */
    public static void genTemplateFiles(Long pathId, String searchStr, String fieldName, String inputResource) {

//        创建对应的files
        String userWorkPath = System.getProperty("user.dir");
        String projectPath = userWorkPath + File.separator + "easy-generator-demo-projects" + File.separator + "acm-template" + File.separator;
//        生成的模板文件先保存到temp目录下，如果每一次新增的id相同的话，那么就需要把原先的模板先复制过去，然后生成新的ftl文件
        String outputRootPath = userWorkPath + File.separator + ".temp" + File.separator + pathId + File.separator + "acm-template" + File.separator;

        String tempDir = userWorkPath + File.separator + ".temp" + File.separator + pathId;

//        如果原先的已经存在了
        if (!FileUtil.exist(tempDir)) {
            FileUtil.mkdir(tempDir);
//            复制模板总框架过来
            FileUtil.copy(projectPath, tempDir, true);
        }

//        获取要复制的模板源文件，追加： 判断对应的ftl文件是否存在，如果存在，那么就对ftl文件进行继续挖空
        String filePath = projectPath + inputResource;
        String fileOutputName = outputRootPath + inputResource + ".ftl";

//        判断ftl文件是否存在
        String newContent;
        if (FileUtil.exist(fileOutputName)) {
//            进行继续挖空
            String oldContent = FileUtil.readUtf8String(fileOutputName);
            String format = String.format("${%s}", fieldName);
            newContent = StrUtil.replace(oldContent, searchStr, format);
        } else {
//            直接生成新的
            String oldStrContent = FileUtil.readUtf8String(filePath);
            String format = String.format("${%s}", fieldName);
            newContent = StrUtil.replace(oldStrContent, searchStr, format);
        }
//        创建对应的文件ftl文件
        FileUtil.writeUtf8String(newContent, fileOutputName);

//        判断meta是否存在
        Meta meta = new Meta();

        FileConfig.Files addFile = new FileConfig.Files(inputResource + "ftl", inputResource, FileTypeEnum.FILE.getValue(), "dynamic", null, null);
        if (FileUtil.exist(outputRootPath + "src" + File.separator + "resource" + File.separator + "meta.json")) {
//            进行追加操作
            String metaStr = FileUtil.readUtf8String(outputRootPath + "src" + File.separator + "resource" + File.separator + "meta.json");
            meta = JSONUtil.toBean(metaStr, Meta.class);
            FileConfig fileConfig = meta.getFileConfig();
            ModelsConfig modelConfig = meta.getModelConfig();
            List<FileConfig.Files> files = fileConfig.getFiles();
            List<Models> oldModels = modelConfig.getModels();
            meta.getFileConfig().getFiles().add(addFile);
//            todo
            oldModels.add(new Models("acm2", "acm2"));
//            进行去重操作
            meta.getModelConfig().setModels(distinctModels(oldModels));
            meta.getFileConfig().setFiles(distinctFiles(files));
        } else {
//        创建meta.json文件
            meta.setName("acm模板");
            meta.setDescription("模板生成器");
            FileConfig fileConfig = new FileConfig();
            fileConfig.setInputRootPath(projectPath + inputResource);
            fileConfig.setOutputRootPath(outputRootPath + fileOutputName);
            meta.setFileConfig(fileConfig);
            ArrayList<FileConfig.Files> files = new ArrayList<>();
            files.add(addFile);
            meta.getFileConfig().setFiles(files);
            ModelsConfig modelsConfig = new ModelsConfig();
            ArrayList<Models> models = new ArrayList<>();
            models.add(new Models("acm", "acm"));
            modelsConfig.setModels(models);
            meta.setModelConfig(modelsConfig);
        }
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(meta), outputRootPath + File.separator + "src" + File.separator + "resource" + File.separator + "meta.json");


    }

    /**
     * 对里面的根据inputPath去重
     *
     * @param oldFiles
     * @return
     */
    public static List<FileConfig.Files> distinctFiles(List<FileConfig.Files> oldFiles) {
        return new ArrayList<FileConfig.Files>(oldFiles.stream()
                .collect(
                        Collectors.toMap(FileConfig.Files::getInputPath,
                                o -> o,
                                (oldValue, newValue) -> newValue))
                .values());
    }

    /**
     * 对里面的根据fieldName去重
     *
     * @param
     * @return
     */
    public static List<Models> distinctModels(List<Models> oldModels) {
        return new ArrayList<Models>(oldModels.stream().collect(Collectors.toMap(Models::getFieldName,o -> o, (oldValue, newValue) -> newValue)).values());
    }


//    public static void main(String[] args) {
//        long tempPathId = IdUtil.getSnowflakeNextId();
//        genTemplateFile(tempPathId);
//
//    }

    /**
     * 设置成为可以追加新的挖空模板文件
     *
     * @param args
     */
    public static void main(String[] args) {
        long tempPathId = IdUtil.getSnowflakeNextId();
        String inputPath = "src" + File.separator + "xyz" + File.separator + "linyh" + File.separator + "acm" + File.separator + "MainTemplate.java";
        genTemplateFiles(1L, "Sum: ", "outputText", inputPath);

    }

//    /**
//     * 遍历包下面的所有文件，然后将包名都挖空
//     *
//     * @param args
//     */
//    public static void main(String[] args) {
//        String workPath = System.getProperty("user.dir");
//        loopDirAndDig(2L, "xyz.linyh", "packageName", "acm", "artifactName", "shop.linyh", "acm", workPath + File.separator + "easy-generator-demo-projects" + File.separator + "acm-template" + File.separator + "src", workPath);
//    }
//


    /**
     * 遍历包下面的所有文件，然后将包名都挖空,然后将对应的文件保存到meta.json中
     *
     * @param pathId             temp区的id
     * @param srcPackageName     原先的组织名
     * @param srcArtifactName    原先的artifactName
     * @param distPackageName    新的组织名
     * @param distArtifactName   新的artifactName
     * @param resourceParentPath 资源根路径
     * @param outputParentPath   输出根路径
     */
    public static void loopDirAndDig(Long pathId, String srcPackageName, String templatePackageName, String srcArtifactName, String templateArtifactName, String distPackageName, String distArtifactName, String resourceParentPath, String outputParentPath) {

        String tempOutputRootPath = outputParentPath + File.separator + ".temp" + File.separator + pathId + File.separator + "acm-template";
        String resourcePath = resourceParentPath + File.separator + srcPackageName.replace(".", File.separator) + File.separator + srcArtifactName;

        String metaPath = tempOutputRootPath + File.separator + "src" + File.separator + "resource" + File.separator + "meta.json";
        Meta meta = getMetaJsonStr(metaPath);
        String workPath = System.getProperty("user.dir");
        if (meta == null) {
            meta = initMetaJson(metaPath, "springboot模板", "springboot模板", distPackageName, "1.0", "linyh", tempOutputRootPath, workPath + File.separator + "generated");
        }
//        遍历包下的所有文件，然后挖空后移动到目标的对应路径
        for (File file : FileUtil.loopFiles(resourcePath)) {
            String absolutePath = file.getAbsolutePath();
            if (StrUtil.isBlank(absolutePath) || !absolutePath.startsWith(resourcePath)) {
                System.out.println("无法复制");
                return;
            }
            String fileBack = absolutePath.replace(resourcePath + File.separator, "");
            String[] split = fileBack.split("\\\\");
            String packageName = StrUtil.join(File.separator, Arrays.copyOf(split, split.length - 1));
            String fileContent = FileUtil.readUtf8String(file);
//            进行artifact挖空
            String format = String.format("${%s}", templateArtifactName);
            String newFileContent = StrUtil.replace(fileContent, srcArtifactName, format);

//            进行组名挖空
            format = String.format("${%s}", templatePackageName);
            newFileContent = StrUtil.replace(newFileContent, srcPackageName, format);

            String outputBack = distPackageName.replace(".", File.separator) + File.separator + distArtifactName + File.separator + packageName;
            String outputFilePath = tempOutputRootPath + File.separator + outputBack;
            if (!FileUtil.exist(outputFilePath)) {
                FileUtil.mkdir(outputFilePath);
            }
            FileUtil.writeUtf8String(newFileContent, outputFilePath + File.separator + file.getName() + ".ftl");

            meta = addMetaFiles(outputBack + file.getName(), outputBack + file.getName() + ".ftl", meta);
        }
        meta.updateMeta(metaPath);

    }

    /**
     * 更新
     *
     * @param meta
     * @param metaPath
     */
    private static void updateMeta(Meta meta, String metaPath) {

    }

    private static Meta addMetaFiles(String inputPath, String outputPath, Meta meta) {
        List<Files> files = meta.getFileConfig().getFiles();
        files.add(new Files(inputPath, outputPath, FileTypeEnum.FILE.getValue(), "dynamic", null, null));
        List<Files> newFiles = distinctFiles(files);
        meta.getFileConfig().setFiles(newFiles);
        return meta;
    }

    /**
     * 初始化meta.json 里面内容
     *
     * @param inputPath
     * @return
     */
    public static Meta getMetaJsonStr(String inputPath) {
        if (!FileUtil.exist(inputPath)) {
            return null;
        }
        String metaJsonStr = FileUtil.readUtf8String(inputPath);
        return JSONUtil.toBean(metaJsonStr, Meta.class);
    }

    /**
     * @param projectName
     * @param projectDesc
     * @param basePackage
     * @param version
     * @param author
     * @param inputRootPath
     * @param outputRootPath
     * @return
     */
    public static Meta initMetaJson(String metaPath, String projectName, String projectDesc, String basePackage, String version, String author, String inputRootPath, String outputRootPath) {

        Meta meta = new Meta();
        meta.setName(projectName);
        meta.setDescription(projectDesc);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(new Date().toString());
        FileConfig fileConfig = new FileConfig();
        fileConfig.setInputRootPath(inputRootPath);
        fileConfig.setOutputRootPath(outputRootPath);
        ArrayList<Files> files = new ArrayList<>();
        fileConfig.setFiles(files);
        meta.setFileConfig(fileConfig);
        ModelsConfig modelsConfig = new ModelsConfig();
        ArrayList<Models> models = new ArrayList<>();
        modelsConfig.setModels(models);
        meta.setModelConfig(modelsConfig);
        FileUtil.writeUtf8String(JSONUtil.toJsonStr(meta), metaPath);


        return meta;
    }
}
