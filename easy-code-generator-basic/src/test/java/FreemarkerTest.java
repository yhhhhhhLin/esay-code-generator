import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {

    @Test
    public void Test1() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);

//        设置freemarker模板文件位置
        configuration.setDirectoryForTemplateLoading((new File("src/main/resources/template")));
        configuration.setDefaultEncoding("UTF-8");

//        创建一个总的map数据，用来后面传递到模板里面
        HashMap<String, Object> map = new HashMap<>();
        ArrayList<Map<String, String>> items = new ArrayList<>();
        map.put("currentYear",2023);
        HashMap<String, String> item1 = new HashMap<>();
        item1.put("url","https://www.baidu.com");
        item1.put("label","百度");
        items.add(item1);
        HashMap<String, String> item2 = new HashMap<>();
        item2.put("url","https://www.google.com");
        item2.put("label","谷歌");
        items.add(item2);
        map.put("items",items);

        map.put("user","小张");

        map.put("helloText","helloWorld!");
        map.put("helloText2","helloWorld!");

        Template template = configuration.getTemplate("freemarkerTest.html.ftl");
//        输出生成后的模板文件
        template.process(map,new FileWriter("freemarkerTest.html"));
    }
}
