package shop.linyh.templateGen;

import shop.linyh.templateGen.meta.MetaManager;
import shop.linyh.templateGen.model.Meta;

import java.io.File;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {


    static String inputRootPath;
    static String outputRootPath;

    public static void main(String[] args) {
        MetaManager.initMeta();
        Meta meta = MetaManager.getMeta();
        Class<? extends Meta> metaClazz = meta.getClass();
        for (Field declaredField : metaClazz.getDeclaredFields()) {
            System.out.println(declaredField.getName());
        }
        HashMap<String, Boolean> dataModal = new HashMap<>();
        String userDir = System.getProperty("user.dir") + File.separator;
        inputRootPath = meta.getFileConfig().getInputRootPath() + File.separator;
        outputRootPath = userDir + meta.getFileConfig().getOutputRootPath() + File.separator;
        List<Meta.FileConfig.Files> files = meta.getFileConfig().getFiles();
//        判断file的condition字段，如果字段为null或dataModal里面对应的condition字段为true，那么就需要生成，但是需要判断file的type字段，如果为group，那么就继续循环里面的file字段
        for (Meta.FileConfig.Files file : files) {
            recursionList(file, dataModal);
        }
    }

    public static void recursionList(Meta.FileConfig.Files file, Map<String, Boolean> dataModal) {
        if (file.getCondition() == null || dataModal.get(file.getCondition())) {
            if ("group".equals(file.getType())) {
                for (Meta.FileConfig.Files files : file.getFiles()) {
                    recursionList(files, dataModal);
                }
            }
//            如果是其他类型的，那么需要生成
            if ("file".equals(file.getType())) {
                String inputPath;
                String outputPath;
                if ("static".equals(file.getGenerateType())) {
                    inputPath = new File(inputRootPath, file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    System.out.printf("%s \n %s", inputPath, outputPath);
                    System.out.println("-----------静态生成----------------");
//                    StaticGenerator.copyFilesByHutool(inputPath, outputPath);
                } else {
                    inputPath = new File(inputRootPath, file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    System.out.println("-------------动态生成-------------,输入地址"+inputPath+"，输出地址 "+outputPath);
//                    DynamicGenerator.doGenerator(inputPath, outputPath, dataModel);
                }
            }
        }
    }
}
