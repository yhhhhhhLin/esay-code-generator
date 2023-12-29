package ${basePackage}.model;

import lombok.Data;

@Data
public class DataModel {

<#list modelConfig.models as modelInfo>
    <#if modelInfo.description??>
        /**
        * ${modelInfo.description}
        */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??>=<#if modelInfo.type == "boolean">${modelInfo.defaultValue?string('true', 'false')}<#else>${modelInfo.defaultValue}</#if></#if>;
</#list>
}
