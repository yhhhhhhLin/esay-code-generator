package xyz.linyh.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

import java.lang.invoke.VarHandle;

public class MetaManager {

    /**
     *  volatile 保证所有线程都是可以使用这个变量 有原子性
     */
    private static volatile Meta meta;

    private MetaManager(){

    }

    /**
     * 双重检查锁创建单例模式
     * @return
     */
    public static Meta getMeta(){
        if(meta == null){
            synchronized (MetaManager.class){
                if(meta == null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    /**
     * 如果是饿汉式创建单例模式，可以用这种方式
     private static Meta meta = initMeta();
     * @return
     */
    public static Meta initMeta(){
//        获取模板文件里面的json配置数据
        String metaJson = ResourceUtil.readUtf8Str("templates/meta.json");
        Meta meta = JSONUtil.toBean(metaJson, Meta.class);
//        TODO 可能还有一些其他处理（默认值）
        return meta;
    }
}
