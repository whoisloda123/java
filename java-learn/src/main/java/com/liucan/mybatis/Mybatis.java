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
 * @brief:
 * 1.SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，没有任何理由对它进行清除或重建
 *  两个数据库需要创建两个SqlSessionFactory 实例，每个数据库对应一个
 * 2.每个线程都应该有它自己的 SqlSession 实例。SqlSession 的实例不是线程安全的，因此是不能被共享的
 */
@Component
public class Mybatis {
    private SqlSessionFactory sessionFactory;

    public void example() {
        getSqlSessionFactoryFromXml();
        UserInfo userInfo = selectByPrimaryKey(8);
        long count = getUserCount();
    }

    private SqlSession openSession() {
        return sessionFactory.openSession();
    }

    private void closeSession(SqlSession session) {
        if (session != null) {
            session.close();
        }
    }

    private void getSqlSessionFactoryFromXml() {
        try {
            InputStream inputStream = Resources.getResourceAsStream("mybatis/mybatis-cfg.xml");
            sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
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

    public UserInfo selectByPrimaryKey(int id) {
        SqlSession sqlSession = openSession();
        UserInfo userInfo = null;
        try {
            userInfo = sqlSession.selectOne("com.liucan.mybatis.dao.UserInfoMapper.selectByPrimaryKey", id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
        return userInfo;
    }

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
