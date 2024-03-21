package xyz.linyh.generator.template;

import cn.hutool.core.io.FileUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import xyz.linyh.generator.model.DataModel;
import freemarker.template.TemplateException;
import xyz.linyh.generator.command.DynamicGenerator;
import xyz.linyh.generator.command.StaticGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
* 主生成类，需要生成的所有文件
* @author linyh
*/
public class MainTemplate {


    static String inputRootPath;
    static String outputRootPath;

    private  static void generatorStatic(String inputPath, String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    public static void doGenerator(DataModel dataModel) throws IOException, TemplateException {
//        复制静态文件
//       遍历所有需要遍历的文件
        String userDir = System.getProperty("user.dir")+File.separator;
        inputRootPath = "F:/AllIdeaProject/esay-code-generator/.temp/3/yhapi-backed/src/main"+ File.separator;
        outputRootPath = userDir+"F:/AllIdeaProject/esay-code-generator/generated"+ File.separator;

        String inputPath;
        String outputPath;
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/user/UserLoginRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/user/UserLoginRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/service/PostService.java, outputPath=java/xyz/linyh/yhapi/service/PostService.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/user/UserUpdateRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/user/UserUpdateRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/exception/GlobalExceptionHandler.java, outputPath=java/xyz/linyh/yhapi/exception/GlobalExceptionHandler.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/post/PostUpdateRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/post/PostUpdateRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/entity/User.java, outputPath=java/xyz/linyh/yhapi/model/entity/User.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/controller/UserController.java, outputPath=java/xyz/linyh/yhapi/controller/UserController.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/mapper/PostMapper.java, outputPath=java/xyz/linyh/yhapi/mapper/PostMapper.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/aop/LogInterceptor.java, outputPath=java/xyz/linyh/yhapi/aop/LogInterceptor.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/user/UserAddRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/user/UserAddRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/config/CorsConfig.java, outputPath=java/xyz/linyh/yhapi/config/CorsConfig.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/service/UserService.java, outputPath=java/xyz/linyh/yhapi/service/UserService.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/service/impl/PostServiceImpl.java, outputPath=java/xyz/linyh/yhapi/service/impl/PostServiceImpl.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/user/UserQueryRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/user/UserQueryRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/mapper/UserMapper.java, outputPath=java/xyz/linyh/yhapi/mapper/UserMapper.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/post/PostQueryRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/post/PostQueryRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/exception/BusinessException.java, outputPath=java/xyz/linyh/yhapi/exception/BusinessException.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/vo/PostVO.java, outputPath=java/xyz/linyh/yhapi/model/vo/PostVO.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/vo/UserVO.java, outputPath=java/xyz/linyh/yhapi/model/vo/UserVO.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=resources/mapper/PostMapper.xml, outputPath=resources/mapper/PostMapper.xml.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/enums/PostReviewStatusEnum.java, outputPath=java/xyz/linyh/yhapi/model/enums/PostReviewStatusEnum.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/user/UserRegisterRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/user/UserRegisterRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=resources/application.yml, outputPath=resources/application.yml.ftl, type=file, generateType=static, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/annotation/AuthCheck.java, outputPath=java/xyz/linyh/yhapi/annotation/AuthCheck.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/service/impl/UserServiceImpl.java, outputPath=java/xyz/linyh/yhapi/service/impl/UserServiceImpl.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/common/ErrorCode.java, outputPath=java/xyz/linyh/yhapi/common/ErrorCode.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/aop/AuthInterceptor.java, outputPath=java/xyz/linyh/yhapi/aop/AuthInterceptor.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/controller/PostController.java, outputPath=java/xyz/linyh/yhapi/controller/PostController.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/common/DeleteRequest.java, outputPath=java/xyz/linyh/yhapi/common/DeleteRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=resources/application-prod.yml, outputPath=resources/application-prod.yml.ftl, type=file, generateType=static, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/constant/UserConstant.java, outputPath=java/xyz/linyh/yhapi/constant/UserConstant.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/common/BaseResponse.java, outputPath=java/xyz/linyh/yhapi/common/BaseResponse.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/common/ResultUtils.java, outputPath=java/xyz/linyh/yhapi/common/ResultUtils.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/constant/CommonConstant.java, outputPath=java/xyz/linyh/yhapi/constant/CommonConstant.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/config/MyBatisPlusConfig.java, outputPath=java/xyz/linyh/yhapi/config/MyBatisPlusConfig.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/common/PageRequest.java, outputPath=java/xyz/linyh/yhapi/common/PageRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/config/Knife4jConfig.java, outputPath=java/xyz/linyh/yhapi/config/Knife4jConfig.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=resources/mapper/UserMapper.xml, outputPath=resources/mapper/UserMapper.xml.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/post/PostAddRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/post/PostAddRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/enums/PostGenderEnum.java, outputPath=java/xyz/linyh/yhapi/model/enums/PostGenderEnum.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=resources/banner.txt, outputPath=resources/banner.txt.ftl, type=file, generateType=static, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/dto/post/PostDoThumbRequest.java, outputPath=java/xyz/linyh/yhapi/model/dto/post/PostDoThumbRequest.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/model/entity/Post.java, outputPath=java/xyz/linyh/yhapi/model/entity/Post.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
//        如果condition字段为空，或者对应的dataModal为true，那么就继续判断里面的type如果type为group那么就继续递归，直到type为file类型，那么生成文件，然后递归出来继续生成对应file文件
        recursionList(Meta.FileConfig.Files(inputPath=java/xyz/linyh/yhapi/MyApplication.java, outputPath=java/xyz/linyh/yhapi/MyApplication.java.ftl, type=file, generateType=dynamic, condition=null, files=null),dataModel)
    }


    public static void recursionList(Meta.FileConfig.Files file,DataModel dataModel) {

        if (file.getCondition() == null || dataModal.get(file.getCondition())){
            if ("group".equals(file.getType())){
                for (Meta.FileConfig.Files files : file.getFiles()) {
                    recursionList(files,dataModal);
                }
            }
        //            如果是其他类型的，那么需要生成
            if("file".equals(file.getType())){
                String inputPath;
                String outputPath;
                if("static".equals(file.getGenerateType())){
                    inputPath = new File(inputRootPath,file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    System.out.printf("%s \n %s",inputPath,outputPath);
                    StaticGenerator.copyFilesByHutool(inputPath, outputPath);
                }else{
                    inputPath = new File(inputRootPath, file.getInputPath()).getAbsolutePath();
                    outputPath = new File(outputRootPath, file.getOutputPath()).getAbsolutePath();
                    DynamicGenerator.doGenerator(inputPath, outputPath, dataModel);
                }
            }
        }
    }

}
