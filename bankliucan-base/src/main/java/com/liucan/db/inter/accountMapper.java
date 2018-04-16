package com.liucan.db.inter;

import com.liucan.db.model.account;

import java.util.List;

public interface accountMapper {
    int deleteByPrimaryKey(Integer accountid);

    int insert(account record);

    int insertSelective(account record);

    account selectByPrimaryKey(Integer accountid);

    int updateByPrimaryKeySelective(account record);

    int updateByPrimaryKey(account record);

    List<account> selectAllAccount();
}