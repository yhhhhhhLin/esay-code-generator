package ${basePackage}.generator.command.file;

import picocli.CommandLine.Command;
import ${basePackage}.generator.model.DataModel;

import java.lang.reflect.Field;

@Command(name = "需要传入的配置",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        Class<DataModel> clazz = DataModel.class;
        Field[] fields = clazz.getDeclaredFields();
        int i = 0;
        for (Field field : fields) {
            System.out.println(i++ +". "+ field.getName()+"需要的类型为"+field.getType().getSimpleName());
            System.out.println("----------------------------");
        }
    }
}
