<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>freemarker测试</title>
</head>
<body>
<#--    将传递过来的所有数据遍历出来-->
    <#list items as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
<#--    获取当前事件-->
    <footer> ${currentYear}</footer>

<#--    if判断是否为空-->
    <#if user??>${user}
    <#else>empty
    </#if>

<#--    定义一个宏（这里面的一个预定义模板）-->
<#--    card为这个宏的名称，userName为这个宏需要传递的参数-->
    <#macro card text>
        |                |
        |                |
        |   ${text}      |
        |                |
        |                |
    </#macro>

<@card text = "hello" />

<#--内嵌函数，可以通过？来调用-->
${helloText?upper_case}
<#--输出链表长度-->
<#--${helloText2?size}-->

<#list ['h','e','l','l','0'] as w>
    <li>${w?index}</li>
</#list>
</body>
</html>