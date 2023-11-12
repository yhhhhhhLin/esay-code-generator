package xyz.linyh.generator;

import cn.hutool.core.io.FileUtil;

import java.nio.file.Files;

/**
 * 用来将静态文件（不需要动态调整里面参数的）直接生成
 */
public class StaticGenerator {

    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }


}
