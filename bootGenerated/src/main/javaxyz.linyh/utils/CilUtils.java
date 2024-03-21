package xyz.linyh.generator.utils;

import picocli.CommandLine.Option;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author linyh
 * cil的相关工具类
 */
public class CilUtils {


    /**
     * 根据原先输入的参数和对应的注解来判断是否需要添加对应的参数
     * //        获取原先里面的所有属性
     * <p>
     * //        获取所有必须要的属性
     * <p>
     * //        判断原先args里面是否携带对应属性对应的names
     * <p>
     * //        创建一个list将原先的args保存到list里面
     * <p>
     * //        如果没有携带那么需要在list里面添加对应name
     * <p>
     * //        全部添加完成后将list转换为String[]复制给args
     *
     * @param args
     * @return
     */
    public static String[] addRequireArgs(String[] args, Field[] fields) {
        ArrayList<String> lists = new ArrayList<>(Arrays.asList(args));

        for (Field field : fields) {
            Option annotation = field.getAnnotation(Option.class);
            if (annotation != null && annotation.required()) {
//                判断原先里面是否有对应的参数
                boolean isHas = false;
                for (int i = 0; i < annotation.names().length; i++) {
                    if (lists.contains(annotation.names()[i])) {
                        isHas = true;
                        break;
                    }
                }
                if (!isHas) {
                    lists.add(annotation.names()[0]);
                }
            }
        }

        return lists.toArray(new String[0]);
    }

}
