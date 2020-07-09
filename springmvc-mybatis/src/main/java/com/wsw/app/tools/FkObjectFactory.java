package com.wsw.app.tools;

import com.wsw.app.NewsManager;
import com.wsw.app.domain.NewsInf;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.Properties;

/**
 * @ClassName FkObjectFactory
 * @Description: 自定义对象工厂，该对象工厂会对生成的News对象进行额外处理
 *      为该对象添加author和queryDate两个元素。
 * @Author wangshiwen
 * @Date 2020/7/9
 * @Version V1.0
 */
// 继承 DefaultObjectFactory 创建自定义对象工厂
public class FkObjectFactory extends DefaultObjectFactory {
    private static Logger logger = LogManager.getLogger(FkObjectFactory.class);
    private String author;
    // 使用无参数的构造器创建对象时，调用该方法
    public Object create(Class type){
        logger.debug("无参数的构造器创建：" + type);
        var obj = super.create(type);
        return processObject(obj);
    }
    // 该方法负责框 objectFactory 元素内配置的属性传入该对象
    public void setProperties(Properties properties){
        super.setProperties(properties);
        logger.debug("设置属性值：{}",properties);
        this.author = properties.getProperty("author");
    }
    public <T> boolean isCollection(Class<T> type){
        logger.debug("==isCollection==");
        // 直接调用父类的方法
        return super.isCollection(type);
    }
    private Object processObject(Object obj){
        // 如果 type 是 NewsInf 的子类或本身
        if (NewsInf.class.isAssignableFrom(obj.getClass())){
            var news = (NewsInf)obj;
            //为 NewsInf 放入额外的信息
            news.getMeta().put("author",this.author);
            news.getMeta().put("queryDate",new Date());
        }
        return obj;
    }
}
