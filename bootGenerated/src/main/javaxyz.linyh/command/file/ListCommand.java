package xyz.linyh.generator.command.file;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
* 执行list指令的时候会执行到这个方法
*@author linyh
*/
@Command(name = "列出所有内容",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");

        String inputPath = "F:/AllIdeaProject/esay-code-generator/.temp/3/yhapi-backed/src/main";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file1 : files) {
            System.out.println(file1.getAbsolutePath());
        }

    }
}
