package com.liucan.other;

/**
 * @author liucan
 * @date 2018/9/22
 * @brief 学习文档
 */
public class Document {
    /**
     * 1.接口和抽象类区别
     *   a.抽象类，除了有抽象方法外，还可以提供默认的实现
     *   b.接口，根本不是类，只是抽象方法的集合
     *   c.场景：抽象类可将公用的方法实现，不公用的子类实现，当关注事物本身的时候，用抽象类，当关注操作行为的时候，或者想多继承的时候，用接口
     *
     * 2.instanceof是运算符
     *
     * 3.装箱：将基本类型转换为包装器类型（调用如Integer.valueOf）,拆箱：将保证器类型转换为基本类型（调用如：intValue）
     *   Byte,Short,Integer,Long,Character的valueOf的实现都是，如值（-128,127）返回缓存同样对象，如值（128，xx)返回新对象
     *   Boolean的valueOf都相等，Float,Double的valueOf都是返回新对象
     *   equals会先判断对象的类型是否相等，才判断其xxValue是否相等
     *
     * 4.String类是不可改变的，创建了String对象，值就无法改变了。如需要对字符串做很多修改，使用StringBuffer & StringBuilder 类
     */
}
