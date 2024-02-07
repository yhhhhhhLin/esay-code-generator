package xyz.linyh.maker.cil.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
// some exports omitted for the sake of brevity

@Command(name = "简单输入输出示例", version = "Out-Put-Demo 1.0", mixinStandardHelpOptions = true)
public class ASCIIArt implements Runnable {

    @Option(names = {"-s", "--font-size"}, description = "Font size")
    int fontSize = 19;

    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli",
            description = "输入单词然后输出")
    private String[] words = {"Hello,", "picocli"};

    //    解析后执行的方法
    @Override
    public void run() {
        System.out.printf("fontSize=%d\n", fontSize);
        System.out.println("words = " + String.join(",", words));
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
        System.exit(exitCode);
    }
}