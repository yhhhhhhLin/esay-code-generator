package shop.linyh.templateGen.marker.project;

import cn.hutool.core.util.StrUtil;
import shop.linyh.templateGen.marker.project.file.DynamicFileGenerator;
import shop.linyh.templateGen.marker.project.file.StaticFileGenerator;
import shop.linyh.templateGen.model.Meta;
import shop.linyh.templateGen.model.ftl.DataModel;

import java.io.File;
import java.util.List;

/**
 * 用来将ftl文件转为java文件
 */
public class ProjectMarker {

    public void genProject(Meta meta, DataModel dataModel) throws Exception {
        String resourcePath = meta.getFileConfig().getInputRootPath();
        String fileOutputRootPath = StrUtil.replace(meta.getFileConfig().getOutputRootPath(),"#{projectName}",String.format("%s", dataModel.getProjectName()));
//        将meta所有文件生成出来
        List<Meta.FileConfig.Files> files = meta.getFileConfig().getFiles();
        System.out.println(files);
        for (Meta.FileConfig.Files file : files) {
//            判断里面是那种生成方式
//            将file里面output路径给改了
            String groupFormat = String.format("%s", dataModel.getGroupName().replace(".", File.separator));
            String artifactFormat = String.format("%s", dataModel.getArtifactName());
            String outPathBack = StrUtil.replace(StrUtil.replace(file.getOutputPath(), "#{basePackage}", groupFormat), "#{baseArtifact}", artifactFormat);
            String fileInputPath = resourcePath + File.separator + file.getInputPath();
            String fileOutputPath = fileOutputRootPath + File.separator + outPathBack;
            if(file.getGenerateType().equals("dynamic")){
//                使用动态生成
                DynamicFileGenerator.doGenerate(fileInputPath,fileOutputPath,dataModel);
            }else if(file.getGenerateType().equals("static")){
//                使用静态生成
                StaticFileGenerator.doGenerate(fileInputPath,fileOutputPath);
            }
        }
    }
}
