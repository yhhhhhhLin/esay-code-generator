package shop.linyh.templateGen.marker.template;
import shop.linyh.templateGen.model.Meta.ModelsConfig.Models;
import shop.linyh.templateGen.model.Meta.FileConfig.Files;
import java.util.ArrayList;
import shop.linyh.templateGen.model.Meta.FileConfig;
import shop.linyh.templateGen.model.Meta.ModelsConfig;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import shop.linyh.templateGen.model.Meta;

import java.io.File;
import java.util.List;

/**
 * 用于生成模板的工具类，将原先项目挖空后生成对应的ftl文件或json文件等等
 */
public class TemplateMaker {

    public static void genTemplateFile(Long pathId) {

        String inputPath = "src" + File.separator + "xyz" + File.separator + "linyh" + File.separator + "acm" + File.separator + "MainTemplate.java";

        String userWorkPath = System.getProperty("user.dir");
        String projectPath = userWorkPath + File.separator + "easy-generator-demo-projects" + File.separator + "acm-template" + File.separator;
//        生成的模板文件先保存到temp目录下，如果每一次新增的id相同的话，那么就需要把原先的模板先复制过去，然后生成新的ftl文件
        String outputRootPath = userWorkPath + File.separator + ".temp" + File.separator + pathId + File.separator + "acm-template" + File.separator;

        String tempDir = userWorkPath + File.separator + ".temp" + File.separator + pathId;

        if (!FileUtil.exist(tempDir)) {
            FileUtil.mkdir(tempDir);
//            复制模板总框架过来
            FileUtil.copy(projectPath, tempDir, true);
        }

//        获取要复制的模板源文件
        String filePath = projectPath + inputPath;
        String fileOutputName = outputRootPath + inputPath + ".ftl";

        String oldStrContent = FileUtil.readUtf8String(filePath);
        String searchStr = "Sum: ";
        String fieldName = "outputText";
        String format = String.format("${%s}", fieldName);
        String newContent = StrUtil.replace(oldStrContent, searchStr, format);
        FileUtil.writeUtf8String(newContent, fileOutputName);

//        创建meta.json文件
        Meta meta = new Meta();
        meta.setName("acm模板");
        meta.setDescription("模板生成器");
        FileConfig fileConfig = new FileConfig();
        fileConfig.setInputRootPath(projectPath+inputPath);
        fileConfig.setOutputRootPath(outputRootPath+fileOutputName);

        meta.setFileConfig(fileConfig);
        ModelsConfig modelsConfig = new ModelsConfig();
        modelsConfig.setModels(new ArrayList<Models>());

        meta.setModelConfig(modelsConfig);




    }

    public static void main(String[] args) {
        long tempPathId = IdUtil.getSnowflakeNextId();
        genTemplateFile(tempPathId);

    }
}
