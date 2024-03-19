package shop.linyh.templateGen.marker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import shop.linyh.templateGen.model.FileTypeEnum;
import shop.linyh.templateGen.model.Meta;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 生成ftl文件
 * 都先生成到.tmp目录下
 */
public class TemplateMaker {

    private final String PROJECT_PARENT_NAME = "easy-generator-demo-projects";

    private final String GEN_PROJECT_NAME = "acm-template";

    /**
     * 可以对同一个ftl文件进行多次挖空，还可以追加meta里面的配置信息 todo 添加指定挖到temp的哪个包
     *
     * @param pathId
     */
    public void genTemplateFiles(Long pathId, String searchStr, String fieldName, String inputResource,String srcPackageName,String srcArtifactName,String distPackageName,String distArtifactName) {

//        创建对应的files
        String userWorkPath = System.getProperty("user.dir");
        String projectPath = userWorkPath + File.separator + PROJECT_PARENT_NAME + File.separator + GEN_PROJECT_NAME + File.separator;
//        生成的模板文件先保存到temp目录下，如果每一次新增的id相同的话，那么就需要把原先的模板先复制过去，然后生成新的ftl文件
        String outputRootPath = userWorkPath + File.separator + ".temp" + File.separator + pathId + File.separator + GEN_PROJECT_NAME + File.separator;

        String tempDir = userWorkPath + File.separator + ".temp" + File.separator + pathId;
        Meta meta = getMetaJsonStr(outputRootPath + "src" + File.separator +"main"+File.separator+ "resource" + File.separator + "meta.json");

//        如果原先的已经存在了
        if (!FileUtil.exist(tempDir)) {
            FileUtil.mkdir(tempDir);
//            复制模板总框架过来
            FileUtil.copy(projectPath, tempDir, true);
        }

//        获取要复制的模板源文件，追加： 判断对应的ftl文件是否存在，如果存在，那么就对ftl文件进行继续挖空
        String filePath = projectPath + inputResource;
        String outputResource = inputResource.replace(srcPackageName.replace(".", File.separator), distPackageName.replace(".", File.separator)).replace(srcArtifactName, distArtifactName);
        String fileOutputName = outputResource + ".ftl";

        String newContent;
        if (FileUtil.exist(fileOutputName)) {
//            进行继续挖空
//            判断原先是否是static类型，如果是那么就改为dynamic类型
            Meta.FileConfig.Files addFile = new Meta.FileConfig.Files(fileOutputName, inputResource, FileTypeEnum.FILE.getValue(), "dynamic", null, null);
            meta = addMetaFiles(addFile, meta);

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
        Meta.FileConfig.Files addFile = new Meta.FileConfig.Files(fileOutputName, inputResource, FileTypeEnum.FILE.getValue(), "dynamic", null, null);
        meta = addMetaFiles(addFile, meta);
        FileUtil.writeUtf8String(JSONUtil.toJsonPrettyStr(meta), outputRootPath + File.separator + "src" + File.separator +"main"+File.separator+ "resource" + File.separator + "meta.json");


    }

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
    public void loopDirAndDigGroupAndArtifact(Long pathId, String srcPackageName, String templatePackageName, String srcArtifactName, String templateArtifactName, String distPackageName, String distArtifactName, String resourceParentPath, String outputParentPath) {

        String tempOutputRootPath = outputParentPath + File.separator + ".temp" + File.separator + pathId + File.separator + GEN_PROJECT_NAME + File.separator + "src" + File.separator + "main";
        String resourcePath = resourceParentPath + File.separator + srcPackageName.replace(".", File.separator) + File.separator + srcArtifactName;

        String metaPath = tempOutputRootPath + File.separator + "resource" + File.separator + "meta.json";
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
//            判断如果是.java文件，那么就挖组和artifact
            String outputBack = distPackageName.replace(".", File.separator) + File.separator + distArtifactName + File.separator + packageName;
            String outputFilePath = tempOutputRootPath + File.separator + "java" + File.separator + outputBack;
            if (file.getName().endsWith(".java")) {
//            进行artifact挖空
                String format = String.format("${%s}", templateArtifactName);
                String newFileContent = StrUtil.replace(fileContent, srcArtifactName, format);

//            进行组名挖空
                format = String.format("${%s}", templatePackageName);
                newFileContent = StrUtil.replace(newFileContent, srcPackageName, format);

                if (!FileUtil.exist(outputFilePath)) {
                    FileUtil.mkdir(outputFilePath);
                }
                FileUtil.writeUtf8String(newFileContent, outputFilePath + File.separator + file.getName() + ".ftl");
                Meta.FileConfig.Files addFile = new Meta.FileConfig.Files(outputBack + file.getName(), outputBack + file.getName() + ".ftl", FileTypeEnum.FILE.getValue(), "dynamic", null, null);
//                添加动态的文件
                meta = addMetaFiles(addFile, meta);
            } else {
//                添加静态的文件
                FileUtil.writeUtf8String(fileContent, outputFilePath + file.getName());
                Meta.FileConfig.Files addFile = new Meta.FileConfig.Files(outputBack + file.getName(), outputBack + file.getName() + ".ftl", FileTypeEnum.FILE.getValue(), "static", null, null);
                addMetaFiles(addFile, meta);
            }
        }
        meta.updateMeta(metaPath);
    }

    /**
     * 初始化meta文件
     *
     * @param metaPath
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
        Meta.FileConfig fileConfig = new Meta.FileConfig();
        fileConfig.setInputRootPath(inputRootPath);
        fileConfig.setOutputRootPath(outputRootPath);
        ArrayList<Meta.FileConfig.Files> files = new ArrayList<>();
        fileConfig.setFiles(files);
        meta.setFileConfig(fileConfig);
        Meta.ModelsConfig modelsConfig = new Meta.ModelsConfig();
        ArrayList<Meta.ModelsConfig.Models> models = new ArrayList<>();
        modelsConfig.setModels(models);
        meta.setModelConfig(modelsConfig);
        FileUtil.writeUtf8String(JSONUtil.toJsonStr(meta), metaPath);


        return meta;
    }

    /**
     * 对里面的根据inputPath去重
     *
     * @param oldFiles
     * @return
     */
    public List<Meta.FileConfig.Files> distinctFiles(List<Meta.FileConfig.Files> oldFiles) {
        return new ArrayList<Meta.FileConfig.Files>(oldFiles.stream()
                .collect(
                        Collectors.toMap(Meta.FileConfig.Files::getInputPath,
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
    public List<Meta.ModelsConfig.Models> distinctModels(List<Meta.ModelsConfig.Models> oldModels) {
        return new ArrayList<Meta.ModelsConfig.Models>(oldModels.stream().collect(Collectors.toMap(Meta.ModelsConfig.Models::getFieldName, o -> o, (oldValue, newValue) -> newValue)).values());
    }

    /**
     * 更新 meta里面的file文件
     *
     * @param meta
     * @return
     */
    private Meta addMetaFiles(Meta.FileConfig.Files file, Meta meta) {
        List<Meta.FileConfig.Files> files = meta.getFileConfig().getFiles();
        files.add(file);
        List<Meta.FileConfig.Files> newFiles = distinctFiles(files);
        meta.getFileConfig().setFiles(newFiles);
        return meta;
    }

    /**
     * 初始化meta.json 里面内容
     *
     * @param inputPath
     * @return
     */
    public Meta getMetaJsonStr(String inputPath) {
        if (!FileUtil.exist(inputPath)) {
            return null;
        }
        String metaJsonStr = FileUtil.readUtf8String(inputPath);
        return JSONUtil.toBean(metaJsonStr, Meta.class);
    }

    public static void main(String[] args) {
        TemplateMaker templateMaker = new TemplateMaker();

        String inputPath = "src" + File.separator+"main"+File.separator +"java"+File.separator+ "xyz" + File.separator + "linyh" + File.separator + "acm" + File.separator + "MainTemplate.java";
        templateMaker.genTemplateFiles(2L, "Sum: ", "outputText", inputPath,"xyz.linyh", "acm", "shop.linyh", "acm");

        String workPath = System.getProperty("user.dir");
//        templateMaker.loopDirAndDigGroupAndArtifact(2L, "xyz.linyh", "packageName", "acm", "artifactName", "shop.linyh", "acm", workPath + File.separator + "easy-generator-demo-projects" + File.separator + "acm-template" + File.separator + "src", workPath);

    }
}
