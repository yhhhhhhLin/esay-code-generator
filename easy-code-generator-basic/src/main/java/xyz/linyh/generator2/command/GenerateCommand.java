package xyz.linyh.generator2.command;

import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import xyz.linyh.generator2.Template.MainTemplate;
import xyz.linyh.generator2.Template.TemplateConfig;

import java.io.IOException;

@Command(name = "生成代码",mixinStandardHelpOptions = true)
@Data
public class GenerateCommand implements Runnable{

    @Option(names = {"-f","--loop"},description = {"是否循环生成"},arity = "0..1",defaultValue = "false",interactive = true,echo = true,required = true)
    private Boolean loop;

    @Option(names = {"-a","--authorName"},description = {"作者名称"},arity = "0..1",interactive = true,defaultValue = "linyhzz",echo = true,required = true)
    private String authorName;

    @Option(names = {"-o","--output"},arity = "0..1",interactive = true,description = {"输出格式"},defaultValue = "output:",echo = true,required = true)
    private String outputText;

    @Override
    public void run() {
        TemplateConfig templateConfig = new TemplateConfig();
        BeanUtils.copyProperties(this,templateConfig);
        try {
            MainTemplate.doGenerator(templateConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}