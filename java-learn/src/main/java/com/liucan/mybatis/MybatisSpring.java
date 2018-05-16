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

            List<Student> students = new LinkedList<>();
            Student student = new Student();
            student.setName("liucan8");
            student.setEmail("524242624@qq.com");
            student.setId(323131);
            students.add(student);
            student = new Student();
            student.setName("liucan9");
            student.setEmail("524242627@qq.com");
            student.setId(323132);
            students.add(student);
            daoMapper.insertUserInfo(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
