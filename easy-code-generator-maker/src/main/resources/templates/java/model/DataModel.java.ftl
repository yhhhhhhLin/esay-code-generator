package ${basePackage}.generator.model;

import lombok.Data;

@Data
public class DataModel {

<#list modelConfig.models as modelInfo>

    <#if modelInfo.models??>
        /**
        * ${modelInfo.description}
        */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??> = <#if modelInfo.type=='boolean'>${modelInfo.defaultValue?c} <#else>"${modelInfo.defaultValue}"</#if></#if>;
</#list>
}
