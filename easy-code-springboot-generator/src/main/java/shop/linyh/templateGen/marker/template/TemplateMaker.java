package shop.linyh.templateGen.marker.template;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import shop.linyh.templateGen.model.Meta;

import java.io.File;
import java.util.List;

/**
 * 用于生成模板的工具类，将原先项目挖空后生成对应的ftl文件或json文件等等
 */
public class TemplateMaker {
    public static void main(String[] args) {
        String name = "acm-template-generator";
        String description = "模板生成器";

        String projectPath = System.getProperty("user.dir");
        String sourceRootPath = new File(projectPath)+File.separator+"easy-generator-demo-projects"+File.separator+"acm-template";
        sourceRootPath = sourceRootPath.replace("\\","/");

        String fileInputPath = "/src/xyz/linyh/acm/MainTemplate.java";
        String fileOutputPath = fileInputPath+".ftl";


        List<Meta.ModelsConfig.Models> models = new Meta.ModelsConfig().getModels();
        models.add(new Meta.ModelsConfig.Models("outputText", "String"));

        String readResult = FileUtil.readUtf8String(sourceRootPath + fileInputPath);
        String format = String.format("${%s}", models.get(0).getFieldName());
        String replaceResult = StrUtil.replace(readResult, "Sum: ",format);

        System.out.println(replaceResult);
    }
}
