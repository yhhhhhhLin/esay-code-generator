package xyz.linyh.cil.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
 * 测试给一个命令添加子命令，类似docker的docker build ，docker images，docker push
 */
@Command(name = "docker",mixinStandardHelpOptions = true)
public class SubCommandDemo implements Runnable{

    public static void main(String[] args) {
        new CommandLine(new SubCommandDemo())
                .addSubcommand("images",new Images())
                .addSubcommand("build",new Build())
                .addSubcommand("push",new Push())
//                .execute("build","-t","hello world");
                .execute("build","--help");
    }

    @Override
    public void run() {

    }

    @Command(name="images",mixinStandardHelpOptions = true)
    static class Images implements Runnable{

        @Override
        public void run() {
            System.out.println("查看所有镜像");
        }
    }

    @Command(name="build",mixinStandardHelpOptions = true)
    static class Build implements Runnable{

        @CommandLine.Option(names={"-t"},description = "构建后的名称")
        String buildName;

        @Override
        public void run() {
            System.out.println("开始构建,名称为"+buildName);
        }
    }

    @Command(name="push",mixinStandardHelpOptions = true)
    static class Push implements Runnable{

        @Override
        public void run() {
            System.out.println("开始推送");
        }
    }


}
