package com.liucan.other;

import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

@Component
public final class Other {
    /* *
     * java.util.Date和SimpleDateFormatter都不是线程安全的，java8而LocalDate和LocalTime和最基本的String一样，
     * 是不变类型，不但线程安全，而且不能修改
     */
    public final void time() {
        //参考https://my.oschina.net/benhaile/blog/193956
        //LocalDate
        LocalDate date = LocalDate.now();
        System.out.println("当前日期：" + date);
        System.out.println("当前日期的年：" + date.getYear());
        System.out.println("当前日期的月--枚举类型：" + date.getMonth());
        System.out.println("当前日期的月--数字类型：" + date.getMonthValue());
        System.out.println("当前日期是周几：" + date.getDayOfWeek());
        System.out.println("当前日期是一年之中的第几天：" + date.getDayOfYear());

        LocalDate christmas = LocalDate.of(2017, 12, 25);
        System.out.println("christmas：" + christmas);

        LocalDate endOfDec = LocalDate.parse("2017-12-28");
        System.out.println("endOfDec：" + endOfDec);

        //对日期进行加减 plus minus
        System.out.println("当前日期plus一天：" + date.plusDays(1));
        System.out.println("当前日期minus一天：" + date.minusDays(1));

        //LocalTime
        LocalTime now = LocalTime.now();
        System.out.println("当前时间" + now);
        System.out.println("当前时间：小时--" + now.getHour());
        System.out.println("当前时间：分钟--" + now.getMinute());
        System.out.println("当前时间：秒--" + now.getSecond());
        //纳秒，不是毫秒，是十亿分之一秒。
        System.out.println("当前时间：纳秒--" + now.getNano());
        //你可以肆意设置时间的任意一个位置的值，使用withXXX()就可以啦。
        System.out.println("当前时间：清空纳秒--" + now.withHour(1));
        System.out.println("当前时间：挨个清零--" + now.withHour(1).withMinute(1).withSecond(1).withNano(1));

        //LocalDateTime
        //当前时间，以秒为单位
        long epochSecond = System.currentTimeMillis() / 1000L;
        //默认使用系统时区
        ZoneId zoneId = ZoneOffset.systemDefault();
        //之所以这么初始化，是因为根据传入的时间进行操作
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond), zoneId);
        //LocalDateTime.now();//也可以这么获得当前时间

        System.out.println("localDateTime 初始化值：" + localDateTime);
        System.out.println("getYear：" + localDateTime.getYear());
        //方法返回值类型特殊，是枚举类型：Month类型
        System.out.println("getMonth：" + localDateTime.getMonth());
        System.out.println("getDayOfMonth：" + localDateTime.getDayOfMonth());
        System.out.println("getSecond：" + localDateTime.getSecond());

        //时间戳
        long dayStart = localDateTime.atZone(zoneId).toEpochSecond();
        System.out.println("dayStart 时间戳，秒数：" + dayStart);

        localDateTime = LocalDateTime.of(2018, 4, 22, 15, 58, 0);
        System.out.println("getDayOfWeek 的 ordinal 值：" + localDateTime.getDayOfWeek().ordinal());

        /*
         * 传入时间的年的第一天
         */
        LocalDateTime firstDayOfYear = localDateTime.with(TemporalAdjusters.firstDayOfYear());
        System.out.println("firstDayOfYear：" + firstDayOfYear);
        LocalDateTime plusDas = firstDayOfYear.plusDays(1);
        System.out.println("plusDays：" + plusDas);

        localDateTime = LocalDateTime.parse("2016-11-13 23:59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        System.out.println(localDateTime);
        //时间戳以及LocalDateTime和Instant相互转换
        /*
        * Instant对于之前的Date代表确定的时间点,且精确到纳秒
        * */
        Instant nowStamp = Instant.now();
        System.out.println("nowStamp.getEpochSecond " + nowStamp.getEpochSecond() + ":" +nowStamp.getNano());

        //Instant to LocalDateTime
        localDateTime = LocalDateTime.ofInstant(nowStamp, ZoneOffset.systemDefault());
        System.out.println("Instant to LocalDateTime：" + localDateTime);

        //LocalDataTime to Instant
        nowStamp = localDateTime.toInstant(ZoneOffset.of("+8"));
        System.out.println("LocalDateTime to Instant：" + nowStamp.getEpochSecond());
        System.out.println("localDateTime.toEpochSecond：" + localDateTime.toEpochSecond(ZoneOffset.of("+8")));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"
    }

    public void other() {
        Exe2 exe2;

        char a = 'b'; //2字节,unicode字符

        double b = 1231.31;
        float c = (float) 131323.1;

        byte bt = 1; //1字节
        short f = 12; //2字节
        int d = 1231; //4字节
        long e = 31321; //8字节

        //instanceof是运算符
        boolean isOther = this instanceof Other;

        Integer i1 = 100; //实际上是调用valueof(100)
        Integer i2 = 100;
        Integer i3 = 200;
        Integer i4 = 200;
        System.out.println(i1 == i2);
        System.out.println(i3 == i4);

        Double d1 = 100.0;
        Double d2 = 100.0;
        System.out.println(d1 == d2);
        System.out.print("she said \"hello\" to me");
        String string = "hello world!";
    }

    //静态嵌套类，和文件类关系不大，很少用
    public static class Exe1 {

    }

    //内部类-成员类
    public abstract class Exe2 {
        private transient int limit = 52;

        public abstract void fun();

        public synchronized void test() {

        }

    }
}
