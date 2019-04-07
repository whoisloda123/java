package com.liucan.mybatis;

import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.mode.UserInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author liucan
 * @date 2018/5/12
 * 参考：https://blog.csdn.net/u014745069/article/details/80788127
 * 1.SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建
 *  两个数据库需要创建两个SqlSessionFactory 实例，每个数据库对应一个
 * 2.每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的
 *
 * 3.PageHelper是Mybatis数据库物理分页插件，是对mybatis执行流程进行了增强，添加了limit和count属于物理分页
 *  可以直接获取分页内容和总数
 *  PageHelper的原理是基于拦截器实现的。拦截器的配置有两种方法，一种是在mybatis的配置文件中配置，一种是直接在spring的配置文件中进行
 *  相当于在真正查询的时候会把分页传进去查询
 *  直接用PageHelper.startPage(currentPage, pageSize).doSelect()就可以了
 *  参考：https://www.cnblogs.com/digdeep/p/4608933.html
 *
 * 4.mysql一般常用的排序语句，order by case
 *  如：select * from table order by case when xxx(condition) then xxx(result)
 *                  when xxx(condition) then xxx(result)
 *                  else xxx(result) end asc
 *       select * from test order by case when num = -1 then 1
 *          when age=2 then 0
 *          else -1 end desc
 *       当num=-1是排序权值是1，当age=2是排序权值是0，其他是-1
 * 5.orm:数据库数据和pojo映射框架，mybatis是一个持久层框架，是一个不完全的orm框架，因为需要自己写sql语句
 * 6.jdbc
 *  参考：https://blog.csdn.net/zdb292034/article/details/81705876
 *      a.是访问和操作数据库的规范
 *      b.常用接口：
 *      　　  DriverManager：这个类管理数据库驱动程序的列表，查看加载的驱动是否符合JAVA Driver API的规范。
 * 　　       Connection：与数据库中的所有的通信是通过唯一的连接对象。
 * 　　       Statement：把创建的SQL对象，转而存储到数据库当中。
 * 　　       ResultSet：它是一个迭代器，用于检索查询数据。
 *
 */
@Component
public class Mybatis {
    private static SqlSessionFactory sessionFactory;

    //初始化一次
    static {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-cfg.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public SqlSession openSession() {
       return Mybatis.sessionFactory.openSession();
    }

    public void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    private void getSqlSessionFactoryFromCode() {
//        try {
//            DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//            TransactionFactory transactionFactory = new JdbcTransactionFactory();
//            Environment environment = new Environment("development", transactionFactory, dataSource);
//            Configuration configuration = new Configuration(environment);
//            configuration.addMapper(BlogMapper.class);
//            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public void example() {
        UserInfo userInfo = selectByPrimaryKey(8);
        long count = getUserCount();
    }

    public UserInfo selectByPrimaryKey(int id) {
        SqlSession sqlSession = openSession();
        UserInfo userInfo = null;
        try {
            //statementId是selectByPrimaryKeyUserInfoMapper.xml里面的id
            userInfo = sqlSession.selectOne("com.liucan.mybatis.mybatis.UserInfoMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
        return userInfo;
    }

    /**
     * mybatis-spring
     * MapperFactoryBean这个类的作用是：
     * <p>
     * 将传统Mybatis调用数据库的方式：
     * <p>
     * xxxMapper mapper = sqlSession.getMapper(xxxMapper.class);
     * <p>
     * 变成
     * <p>
     * xxxMapper mapper = context.getBean(“xxxMapper”);
     * <p>
     * 也就是将mybatis的对象由spring以bean的方式管理
     * <p>
     * 形成了一一对应关系，方便在service层直接注入使用。
     */
    public long getUserCount() {
        SqlSession sqlSession = openSession();
        long count = 0;
        try {
            UserInfoMapper userInfoMapper = sqlSession.getMapper(UserInfoMapper.class);
            count = userInfoMapper.countByExample(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
        return count;
    }

    public void insert() {
        SqlSession sqlSession = openSession();
        try {
            UserInfo user = new UserInfo();
            user.setPhone("15928672345");
            sqlSession.insert("com.liucan.mybatis.mapper.userinfo.insert", user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
    }
}
