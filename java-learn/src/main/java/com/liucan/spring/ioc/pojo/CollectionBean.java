package com.liucan.spring.ioc.pojo;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author liucan
 * @date 2018/5/20
 * @brief
 */
@Data
public class CollectionBean {
    List addressList;
    Set addressSet;
    Map addressMap;
    Properties addressProp; //map:名称和值都是字符串
}
