package xyz.linyh.generator2.executor;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import xyz.linyh.generator2.command.ConfigCommand;
import xyz.linyh.generator2.command.GenerateCommand;
import xyz.linyh.generator2.command.ListCommand;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lin
 */
@Command(name = "amc模板生成执行器",mixinStandardHelpOptions = true )
public class CommandExecutor implements Runnable{

    private final CommandLine commandLine;

    {
//        引入子命令，并且这个类只需要被执行一次
        this.commandLine = new CommandLine(this)
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
        return commandLine.execute(args);
    }
}
