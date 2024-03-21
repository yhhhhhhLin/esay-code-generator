package xyz.linyh.generator;

import xyz.linyh.generator.utils.CilUtils;
import xyz.linyh.generator.command.file.GenerateCommand;
import xyz.linyh.generator.executor.CommandExecutor;

import java.util.Arrays;

/**
* @author linyh
* Main方法
*/
public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
//        判断里面是否包含generator
        if(Arrays.asList(args).contains("generate")){
            args = CilUtils.addRequireArgs(args, GenerateCommand.class.getDeclaredFields());
        }

//        采用命令模式运行(可以在里面统一管理和运行所有的命令（例如可以在里面进行历史命令的管理和可以有回退的操作）)
        commandExecutor.execute(args);
    }
}
