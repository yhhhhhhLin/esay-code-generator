package xyz.linyh.cil.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;
import xyz.linyh.cil.example.utils.CilUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CommandLine.Command(name = "登录测试",mixinStandardHelpOptions = true)
public class LoginDemo implements Runnable{

    @Option(names = {"-u","-user"},description={"用户账号"})
   String user;


    /**
     * 添加 arity 可以让用户在命令行输入，也可以交互式输入，不然的话如果直接命令行输入会出问题
     */
    @Option(names = {"-p","-password"},description={"用户密码"},interactive = true,arity = "0..1",echo = false)
   String password;

    @Option(names = {"-cp","-checkPassword"},defaultValue = "whuis",description={"再次输入用户密码"},interactive = true,echo = false)
    String checkPassword;

    @Option(names = {"-c","-code"},description={"验证码"},arity = "0..1",interactive = true,echo = false,required = true)
    String code;


    @Option(names = {"-t"},description={"测试字段"},interactive = true,echo = false,required = true)
    String test;
    @Override
    public void run() {
//        验证码输入不能为空
        if(code==null && System.console()!=null){
            System.out.println("请输入验证码 -c");
            code = System.console().readLine();
        }
        if(password.equals(checkPassword)){
            System.out.println("登录成功");
        }
        System.out.println("code:"+code);
        System.out.println("password:"+password);
    }

    /**
     * 执行命令行
     * @param args
     */
    public static void main(String[] args) {
        args = CilUtils.addRequireArgs(args,LoginDemo.class.getDeclaredFields());

        new CommandLine(new LoginDemo()).execute(args);

    }


}
