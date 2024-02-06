package xyz.linyh.maker;

import cn.hutool.core.util.StrUtil;
import xyz.linyh.maker.generator.JarGenerator;
import xyz.linyh.maker.generator.ScriptGenerator;
import xyz.linyh.maker.generator.file.DynamicFileGenerator;
import xyz.linyh.maker.meta.Meta;
import xyz.linyh.maker.meta.MetaManager;

import java.io.File;
import java.io.IOException;

public class MainGenerator2 extends MainTemplate {

    /**
     * 如果想要修改某一步的步骤，直接重写就好了
     *
     * @param outputRootPath
     * @param meta
     * @throws IOException
     */
    @Override
    protected void generatorScript(String outputRootPath, Meta meta) throws IOException {
        super.generatorScript(outputRootPath, meta);
    }

    /**
     * 直接根据配置文件的json和对应的模板文件，生成对应的项目
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        MainGenerator2 generator = new MainGenerator2();
        generator.doGenerator();
    }


}
