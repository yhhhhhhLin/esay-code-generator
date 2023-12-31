package xyz.linyh.maker.generator;

import java.io.*;

public class JarGenerator {

    public static void doGenerate(String projectDir) throws Exception {
        // 指定maven生成jar包的命令
        String windowCommand = "mvn.cmd clean package -DskipTests=true";
        String otherCommand = "mvn clean package -DskipTests=true";

        // 根据操作系统类型选择命令
        String command = System.getProperty("os.name").toLowerCase().contains("windows") ? windowCommand : otherCommand;
        // 创建进程构建器并设置命令
        ProcessBuilder processBuilder = new ProcessBuilder(command.split(" "));
        // 指定工作目录
        processBuilder.directory(new File(projectDir));
        // 启动进程
        Process process = processBuilder.start();
        // 获取进程的输入流
        InputStream inputStream = process.getInputStream();
        // 创建BufferedReader来读取输入流的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line = null;
        while((line=reader.readLine())!=null){
            // 逐行打印输出内容
//            输出指定编码格式
            System.out.println(line);
        }

        int code = process.waitFor();
        if (code != 0) {
            // 如果进程返回码不为0，表示执行失败
            System.out.printf("执行命令失败：%s%n",code);
        }else{
            // 如果进程返回码为0，表示执行成功
            System.out.printf("执行命令成功：%s%n",code);
        }
    }


    public static void main(String[] args) throws Exception {
        doGenerate("F:\\AllIdeaProject\\esay-code-generator\\easy-code-generator-basic");
    }
}
