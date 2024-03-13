package shop.linyh;

import shop.linyh.meta.MetaManager;
import shop.linyh.model.FileTypeEnum;
import shop.linyh.model.Meta;

import java.lang.invoke.VarHandle;
//TODO 递归遍历
public class main {
    public static void main(String[] args) {
        MetaManager.initMeta();
        Meta meta = MetaManager.getMeta();
        Meta.FileConfig fileInfo = meta.getFileConfig();
        for (Meta.FileConfig.Files file : fileInfo.getFiles()) {
//            判断是否有condition字段，如果存在并且为false，那么就循环下一个
            if (file.getCondition() != null && !file.getCondition()) {
                continue;
            }

//            如果类型为group，那么需要继续遍历往下的file
            if (file.getType().equals(FileTypeEnum.GROUP.getValue())) {

            }
        }


    }

    public void recursionList(Meta.FileConfig files){

    }
}
