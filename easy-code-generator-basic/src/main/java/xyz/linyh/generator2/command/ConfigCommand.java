package xyz.linyh.generator2.command;

import picocli.CommandLine.Command;
import xyz.linyh.generator2.Template.TemplateConfig;

import java.lang.reflect.Field;

@Command(name = "需要传入的配置", mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable {
    @Override
    public void run() {
        Class<TemplateConfig> clazz = TemplateConfig.class;
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            System.out.println(i++ + ". " + field.getName() + "需要的类型为" + field.getType().getSimpleName());
            System.out.println("----------------------------");
        }
    }
}
