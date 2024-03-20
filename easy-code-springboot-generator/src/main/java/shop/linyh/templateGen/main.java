package shop.linyh.templateGen;

import shop.linyh.templateGen.marker.template.TemplateMaker;

import java.io.File;

public class main {

    public static void main(String[] args) {
        TemplateMaker templateMaker = new TemplateMaker();
        String workDir = System.getProperty("user.dir");
        String resourcePath = workDir+File.separator+"easy-generator-demo-projects"+ File.separator +"yhapi-backed"+File.separator+"src"+File.separator+"main";
        templateMaker.loopDirAndDigGroupAndArtifact(3L, "xyz.linyh","groupName","yhapi","artifactName","shop.linyh","template",resourcePath,workDir);
    }

}
