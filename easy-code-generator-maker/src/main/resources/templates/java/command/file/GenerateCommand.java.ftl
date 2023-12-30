package ${basePackage}.generator.command.file;

import freemarker.template.TemplateException;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import ${basePackage}.generator.Template.MainTemplate;
import ${basePackage}.generator.model.DataModel;

import java.io.IOException;

@Command(name = "${description}",mixinStandardHelpOptions = true)
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
<#list modelConfig.models as modelInfo>
    @Option(names = {<#if modelInfo.abbr??>"-${modelInfo.abbr}", </#if>"--${modelInfo.fieldName}"}, arity = "0..1", <#if modelInfo.description??>description = "${modelInfo.description}", </#if>interactive = true, echo = true)
    private ${modelInfo.type} ${modelInfo.fieldName}<#if modelInfo.defaultValue??> = <#if modelInfo.type=='boolean'>${modelInfo.defaultValue?c} <#else>"${modelInfo.defaultValue}" </#if></#if>;

</#list>

    @Override
    public void run() {
        DataModel dataModel = new DataModel();
        BeanUtils.copyProperties(this,DataModel);
        try {
            MainTemplate.doGenerator(dataModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        }

    }
}
