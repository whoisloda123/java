package com.liucan.db;

import com.liucan.db.model.account;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class AccountDbMgr {
    private SqlSessionFactory sessionFactory;

    public void init() {
        //定义mybatis全局配置文件
        String resource = "mybatis-configuration.xml";

        try {
            //加载 mybatis 全局配置文件
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            //想连接两个数据库，就需要创建两个 SqlSessionFactory 实例，每个数据库对应一个
            sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SqlSession getSession() {
        //根据 sqlSessionFactory 产生 session
         return sessionFactory.openSession();
    }

    public void closeSession(SqlSession session) {
        //session应该在打开操作之后，马上关闭因为是线程不安全的
        if (session != null) {
            session.close();
        }
    }

    //根据id查询bank表数据
    public account selectByPrimaryKey(int accountId) {
        /*这个字符串由 userMapper.xml 文件中 两个部分构成  <mapper namespace="com.ys.po.userMapper"> 的 namespace 的 <select id="selectUserById" > id 值*/
        SqlSession sqlSession = getSession();
        account account = null;
        try {
            account = sqlSession.selectOne("com.liucan.db.inter.accountMapper.selectByPrimaryKey", accountId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
        return account;
    }

    public List<account> selectAllAccount() {
        SqlSession sqlSession = getSession();
        List<account> list = null;
        try {
            list = sqlSession.selectOne("com.liucan.db.inter.accountMapper.selectAllAccount");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
        return list;
    }

    //向 user 表中插入一条数据
    public void insert() {
        SqlSession sqlSession = getSession();
        try {
            String statement = "com.liucan.db.inter.accountMapper.insert";
            account user = new account();
            user.setAccountid(10);
            user.setMoney(0);
            user.setName("liucan10");
            user.setSex("male");
            user.setUid(101);

            sqlSession.insert(statement, user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeSession(sqlSession);
        }
    }
}
