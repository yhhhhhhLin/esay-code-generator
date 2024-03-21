package xyz.linyh.generator.executor;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import xyz.linyh.generator.command.file.ConfigCommand;
import xyz.linyh.generator.command.file.GenerateCommand;
import xyz.linyh.generator.command.file.ListCommand;

/**
 * @author linyh
 */
@Command(name = "springboot模板",mixinStandardHelpOptions = true )
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
//        引入子命令，并且这个类只需要被执行一次
        this.commandLine = new CommandLine(this)
//                添加这个操作启动的时候的子命令
                .addSubcommand("config",new ConfigCommand())
                .addSubcommand("generate",new GenerateCommand())
                .addSubcommand("list",new ListCommand());
    }

    @Override
    public void run() {
        System.out.println("启动！");
    }

//    执行命令
    public Integer execute(String[] args){
//        可以在这里面管理所有子命令的执行
        return commandLine.execute(args);
    }
}
