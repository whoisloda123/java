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
    //定义 SqlSession
    SqlSession session =null;

    public void init() {
        //定义mybatis全局配置文件
        String resource = "mybatis-configuration.xml";

        try {
            //加载 mybatis 全局配置文件
            Reader reader = Resources.getResourceAsReader(resource);
            //构建sqlSession的工厂
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            //根据 sqlSessionFactory 产生 session
            session = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        if (session != null) {
            session.close();
        }
    }

    //根据id查询bank表数据
    public account selectByPrimaryKey(int accountId) {
        /*这个字符串由 userMapper.xml 文件中 两个部分构成  <mapper namespace="com.ys.po.userMapper"> 的 namespace 的 <select id="selectUserById" > id 值*/
        String statement = "com.liucan.db.inter.accountMapper.selectByPrimaryKey";
        return  session.selectOne(statement, accountId);
    }

    public List<account> selectAllAccount() {
        String statement = "com.liucan.db.inter.accountMapper.selectAllAccount";
        return session.selectList(statement);
    }

    //向 user 表中插入一条数据
    public void insert() {
        String statement = "com.liucan.db.inter.accountMapper.insert";
        account user = new account();
        user.setAccountid(10);
        user.setMoney(0);
        user.setName("liucan10");
        user.setSex("male");
        user.setUid(101);
        session.insert(statement, user);
        //提交插入的数据
        session.commit();
    }

    //根据 account 更新 user 表的数据
    public void updateByPrimaryKey(account user) {
        if (user != null) {
            String statement = "com.liucan.db.inter.accountMapper.updateByPrimaryKey";
            //如果设置的 id不存在，那么数据库没有数据更改;
            session.update(statement, user);
            session.commit();
        }
    }
}
