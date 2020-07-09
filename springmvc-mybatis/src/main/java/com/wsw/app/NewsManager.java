package com.wsw.app;

import com.wsw.app.dao.NewsInfDao;
import com.wsw.app.domain.NewsInf;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class NewsManager {

    private static Logger logger = LogManager.getLogger(NewsManager.class.getName());
    private static SqlSessionFactory sqlSessionFactory;

    public static void main(String[] args) throws Exception{
        var resource = "mybatis-config.xml";
        // 使用 Resources 工具从类路径下加载指定文件
        var inputStream = Resources.getResourceAsStream(resource);
        // 构建 SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(inputStream);
        // 打开 Session
        var sqlSession = sqlSessionFactory.openSession();
        try {
            //insertTest(sqlSession);
            //insertByMapperTest(sqlSession);
            selectByPrimaryKey(sqlSession);
        }finally {
            //关闭资源
            sqlSession.close();;
        }

    }
    public static void  insertTest(SqlSession sqlSession){
        // 创建消息实例
        var news = new HashMap<String,String>();
        // 设置消息标题和消息内容
        news.put("title","王世文的女朋友");
        news.put("content","她是郑爽");
        try {
            // 调用 insert 方法执行 SQL 语句
            var n = sqlSession.insert("com.wsw.app.dao.NewsMapper.saveNews",news);
            System.out.printf("插入了%d条数据%n",n);
        }catch (Exception ex){
            logger.error("加入数据库报错：" + ex.getCause());
        }finally {
            // 提交事务
            sqlSession.commit();
        }
    }

    public static void insertByMapperTest(SqlSession sqlSession){
        try {
            var news = new NewsInf();
            news.setNewsTitle("我爱我的老婆");
            news.setNewsContent("我的老婆是郑爽");
            //获取 Mapper 对象
            var newsMapper = sqlSession.getMapper(NewsInfDao.class);
            //调用 Mapper 对象的方法执行持久化操作
            var n = newsMapper.insert(news);
            logger.debug("插入了{}条数据",n);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            //提交事务
            sqlSession.commit();
        }
    }

    public static void selectByPrimaryKey(SqlSession sqlSession){
        var newsMapper = sqlSession.getMapper(NewsInfDao.class);
        var newsInf = newsMapper.selectByPrimaryKey(1);
        logger.debug("查询结果:{}",newsInf.toString());
        sqlSession.commit();
    }
}
