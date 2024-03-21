package shop.linyh.fileGen.generator.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;
import shop.linyh.fileGen.model.Meta;

// 静态文件生成器
public class StaticFileGenerator {

    /**
     * 生成静态文件
     *
     * @param inputPath  静态文件原始位置
     * @param outputPath 要生成后的路径
     */
    public static void doGenerate(String inputPath, String outputPath) {
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void main(String[] args) {
        String userDir = System.getProperty("user.dir");
//        doGenerate(userDir+"/easy-generator-demo-projects/acm-templates",userDir);
        String metaJson = ResourceUtil.readUtf8Str("templates/meta.json");
        Meta meta = JSONUtil.toBean(metaJson, Meta.class);
        System.out.println(meta);
    }
}
