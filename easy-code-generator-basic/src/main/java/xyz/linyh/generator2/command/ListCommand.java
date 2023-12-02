package xyz.linyh.generator2.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "列出所有内容",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        File file = new File(projectPath, "/easy-generator-demo-projects/acm-template");
        List<File> files = FileUtil.loopFiles(file.toString());
        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
        }

    }
}
