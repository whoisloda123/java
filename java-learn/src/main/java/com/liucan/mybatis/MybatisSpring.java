package com.liucan.mybatis;

import com.google.common.collect.Lists;
import com.liucan.mybatis.dao.DaoMapper;
import com.liucan.mybatis.dao.UserInfoMapper;
import com.liucan.mybatis.dao.UserOrderMapper;
import com.liucan.mybatis.mode.UserInfo;
import com.liucan.mybatis.mode.UserOrder;
import com.liucan.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liucan
 * @date 2018/5/13
 * @brief
 */
@Component
public class MybatisSpring {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserOrderMapper userOrderMapper;
    @Autowired
    private DaoMapper daoMapper;

    public void example() {
        try {
            List<UserInfo> userInfoList = daoMapper.selectUserInfoInUids(Lists.newArrayList("1231313","1316766"));
            long count = userInfoMapper.countByExample(null);
            List<UserOrder> orderList = userOrderMapper.selectByExample(null);
            String email = daoMapper.getUserEmail("7878746", "15928672109");
            String phone = daoMapper.getUserPhone("7878746");

            UserInfo userInfo = new UserInfo();
            userInfo.setId(14);
            userInfo.setUserName("liucan10");
            userInfoMapper.updateByPrimaryKeySelective(userInfo);

            UserOrder userOrder = new UserOrder();
            userOrder.setId(67);
            userOrder.setUserId(354545);
            userOrder.setOrderId("31313");
            userOrder.setOrderStat(8);
            userOrder.setPrice(60);
            userOrder.setCreateTime(Date.from(Instant.now()));
            userOrder.setUpdateTime(Date.from(Instant.now()));
            userOrderMapper.updateByPrimaryKey(userOrder);

            List<Student> students = new LinkedList<>();
            Student student = new Student();
            student.setName("liucan10");
            student.setEmail("524242624@qq.com");
            student.setId(323141);
            students.add(student);
            student = new Student();
            student.setName("liucan11");
            student.setEmail("524242627@qq.com");
            student.setId(323142);
            students.add(student);
            daoMapper.insertUserInfo(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
