package ${basePackage}.generator.command;

import cn.hutool.core.io.FileUtil;

/**
 * 用来将静态文件（不需要动态调整里面参数的）直接生成
 */
public class StaticGenerator {

    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(String inputPath, String outputPath){
        copyFilesByHutool(inputPath, outputPath);
    }


}
