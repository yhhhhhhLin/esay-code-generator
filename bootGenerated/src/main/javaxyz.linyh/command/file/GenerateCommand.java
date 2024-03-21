package xyz.linyh.generator.command.file;

import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import xyz.linyh.generator.template.MainTemplate;
import xyz.linyh.generator.model.DataModel;

import java.io.IOException;

/**
* 执行generate指令的时候会执行到这个方法
*@author linyh
*/
@Command(name = "springboot模板",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable{

    /**
     * echo 表示是否显示该选项的值
     * interactive 表示是否需要用户输入该选项的值
     * defaultValue 表示该选项的默认值
     * required 表示该选项是否必须输入
     * arity 表示该选项的数量
     * description 表示该选项的描述
     */
    @Option(names = {"-nc", "--needCors"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要跨域文件", interactive = true, echo = true)
    private boolean needCors;

    @Option(names = {"-nbw", "--needBootWeb"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要springboot web依赖框架", interactive = true, echo = true)
    private boolean needBootWeb;

    @Option(names = {"-nba", "--needBootAop"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要springboot aop依赖", interactive = true, echo = true)
    private boolean needBootAop;

    @Option(names = {"-nmp", "--needMybatisPlus"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要MybatisPlus依赖", interactive = true, echo = true)
    private boolean needMybatisPlus;

    @Option(names = {"-nm", "--needMybatis"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Mybatis依赖", interactive = true, echo = true)
    private boolean needMybatis;

    @Option(names = {"-nmsql", "--needMysql"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Mysql依赖", interactive = true, echo = true)
    private boolean needMysql;

    @Option(names = {"-nredis", "--needRedis"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Redis依赖", interactive = true, echo = true)
    private boolean needRedis;

    @Option(names = {"-ngson", "--needGson"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Gson依赖", interactive = true, echo = true)
    private boolean needGson;

    @Option(names = {"-nFastJ", "--needFastJson"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要FastJson依赖", interactive = true, echo = true)
    private boolean needFastJson;

    @Option(names = {"-nhutool", "--needHutool"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Hutool依赖", interactive = true, echo = true)
    private boolean needHutool;

    @Option(names = {"-nKnife4j", "--needKnife4j"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要Knife4j依赖", interactive = true, echo = true)
    private boolean needKnife4j;

    @Option(names = {"-nLombok", "--needLombok"}, arity = "0..1",defaultValue = "true"  ,  description = "是否需要lombok依赖", interactive = true, echo = true)
    private boolean needLombok;


    @Override
    public void run() {
        DataModel dataModel = new DataModel();
        BeanUtils.copyProperties(this,dataModel);
        try {
            MainTemplate.doGenerator(dataModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
