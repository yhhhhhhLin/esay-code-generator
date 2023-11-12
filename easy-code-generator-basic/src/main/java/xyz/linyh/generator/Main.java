package xyz.linyh.generator;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        获取用户当前工作的目录
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
//        获取要输入的文件的地址
        String inputPath = new File(currentPath, "easy-generator-demo-projects/acm-template").getAbsolutePath();
        System.out.println(inputPath);
        String outputPath = currentPath;
        StaticGenerator.copyFilesByHutool(inputPath, outputPath);
    }
}
