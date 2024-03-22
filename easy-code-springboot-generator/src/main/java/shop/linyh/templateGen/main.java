package shop.linyh.templateGen;

import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.auth.STSAssumeRoleSessionCredentialsProvider;
import com.aliyuncs.exceptions.ClientException;
import shop.linyh.templateGen.marker.entity.EntityMaker;
import shop.linyh.templateGen.marker.project.ProjectMarker;
import shop.linyh.templateGen.marker.template.TemplateMaker;
import shop.linyh.templateGen.meta.MetaManager;
import shop.linyh.templateGen.model.Meta;
import shop.linyh.templateGen.model.ftl.DataModel;

import java.io.File;

public class main {

    static Long tempId = 7L;
    static String projectName = "yhapi-backed";
    static TemplateMaker templateMaker = new TemplateMaker();
    static String workDir = System.getProperty("user.dir");

    public static void main(String[] args) throws Exception {
        Long tempId = 7L;
        String projectName = "yhapi-backed";
        TemplateMaker templateMaker = new TemplateMaker();
        String workDir = System.getProperty("user.dir");
        String dataModelFtlPath = workDir + File.separator +"easy-code-springboot-generator"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"templates"+File.separator+"java"+File.separator+"model";
        String resourcePath = workDir+File.separator+"easy-generator-demo-projects"+ File.separator +projectName;
        templateMaker.loopDirAndDigGroupAndArtifact(tempId, "xyz.linyh","groupName","yhapi","artifactName",resourcePath,workDir);
        templateMaker.addDataModalFtl(tempId,dataModelFtlPath,workDir);

//        将meta.json下面的所有实体类创建一个实体对象
//        EntityMaker entityMaker = new EntityMaker();
//        String tempPath = workDir+ File.separator+".temp"+File.separator+tempId +File.separator+projectName;
//        entityMaker.doGenerate(tempPath,workDir+File.separator+"easy-code-springboot-generator"+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator +"shop"+File.separator +"linyh" +File.separator +"templateGen"+File.separator+"model" + File.separator +"ftl");

    }

//    public static void main(String[] args) throws Exception {
//        String metaPath = workDir + File.separator +".temp"+File.separator + tempId + File.separator + projectName + File.separator + "src" + File.separator + "main" + File.separator + "resources";
//        Meta meta = MetaManager.getMeta(metaPath);
//        ProjectMarker projectMarker = new ProjectMarker();
//        DataModel dataModel = new DataModel();
//        dataModel.setGroupName("shop.linyh");
//        dataModel.setArtifactName("backed");
//        dataModel.setNeedCors(false);
//        dataModel.setNeedBootWeb(false);
//        dataModel.setNeedBootAop(false);
//        dataModel.setNeedMybatisPlus(false);
//        dataModel.setNeedMybatis(false);
//        dataModel.setNeedMysql(false);
//        dataModel.setNeedRedis(false);
//        dataModel.setNeedGson(false);
//        dataModel.setNeedFastJson(false);
//        dataModel.setNeedHutool(false);
//        dataModel.setNeedKnife4j(false);
//        dataModel.setNeedLombok(false);
//        dataModel.setProjectName("backed");
//        dataModel.setNeedUserExample(false);
//
//        projectMarker.genProject(meta,dataModel);
//    }


}
