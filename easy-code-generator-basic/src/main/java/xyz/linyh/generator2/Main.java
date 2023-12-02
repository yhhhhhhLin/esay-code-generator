package xyz.linyh.generator2;

import xyz.linyh.cil.example.utils.CilUtils;
import xyz.linyh.generator2.Template.TemplateConfig;
import xyz.linyh.generator2.command.GenerateCommand;
import xyz.linyh.generator2.executor.CommandExecutor;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        args = new String[]{"generate"};
//        判断里面是否包含generator
        if(Arrays.asList(args).contains("generate")){
            args = CilUtils.addRequireArgs(args, GenerateCommand.class.getDeclaredFields());
        }

        commandExecutor.execute(args);
    }
}
