package com.liucan.other;

import com.liucan.util.Constants;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

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
        //基本数据类型
        baseType();

        //string
        baseString();

        //操作数组和集合
        collections();

        //可变参数
        printNumbers(1, 2, 3, 4);

        //try-with-resource
        tryWithResource();

        //控制台输入
        //stream();
        fileStream();

        //tryCatch
        tryCatch();

        //只有方法才支持多态，变量不支持
        B b = new B();
        b.show();

        //bitset
        bitSet();

        //stack
        stack();

        //iterator
        iter();

        //vector
        vector();

        //treeMap
        treeMap();

        //hashMap
        hashMap();

        //weakHashMap
        weakHashMap();

        //hashSet
        hashSet();

        //treeSet
        treeSet();

        //linkdeHashMap
        linkedHashMap();

        string();

        arrays();

        base64();
    }

    private void base64() {
        //base64主要是用64个字符来表示二进制内容，比如说图片，程序等等，严格来说用于加密和解密不安全
        String string = Base64.getUrlEncoder().encodeToString(String.valueOf("1313123sfs ").getBytes());
        byte[] bytes = Base64.getUrlDecoder().decode(string);
        string = new String(bytes, StandardCharsets.UTF_8);
    }

    private void arrays() {
        Integer array[] = {1, -1, 3, 2};
        Arrays.sort(array);
        int key = Arrays.binarySearch(array, 2);

        Integer des[] = new Integer[array.length];
        System.arraycopy(array, 0, des, 0, array.length);

        //二维数组
        int[][] data = {{1, 3, 2}, {2}};
        int length = data.length;
        length = data[1].length;

        Integer max = Collections.max(Arrays.asList(array));
        Integer min = Stream.of(array).min(Integer::compareTo).get();

        //并集
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(3, 4));
        list1.addAll(list2);

        //交集
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list4 = new ArrayList<>(Arrays.asList(3, 4));
        list3.retainAll(list4);

        //差集
        List<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list6 = new ArrayList<>(Arrays.asList(3, 4));
        list5.removeAll(list6);

        //无重复并集
        List<Integer> list7 = new ArrayList<>(Arrays.asList(1, 2, 3));
        List<Integer> list8 = new ArrayList<>(Arrays.asList(3, 4));
        list7.removeAll(list8);
        list8.addAll(list7);

    }

    private void string() {
        String string = "Hello2312.Wor2ld!";
        String tmp;
        int index;
        //string里面的replace方法里面都是用的regex
        tmp = string.replaceAll("\\d+", "liucan");

        //字符串反转
        tmp = new StringBuilder(string).reverse().toString();
        index = string.indexOf("He");

        //split里面其实是调用正则表达式的pattern.split方法
        String[] split = string.split("\\.");

        //转换大写
        tmp = string.toUpperCase();
        tmp = string.toLowerCase();

        //测试字符串指定区域是否相等
        boolean matches = string.regionMatches(false, 2, "反射31llo", 4, 3);
    }

    private void linkedHashMap() {
        //无序的
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("name1", "1");
        hashMap.put("name2", "2");
        hashMap.put("name3", "3");

        Iterator iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //插入顺序
        Map<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("name1", "1");
        linkedHashMap.put("name2", "2");
        linkedHashMap.put("name3", "3");

        iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //操作顺序
        linkedHashMap = new LinkedHashMap<>(50, 0.75f, true);
        linkedHashMap.put("name4", "1");
        linkedHashMap.put("name5", "2");
        linkedHashMap.put("name6", "3");

        iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        linkedHashMap.get("name5");
        iterator = linkedHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @SuppressWarnings("unchecked")
    private void treeSet() {
        TreeSet<Integer> set = new TreeSet(Comparator.comparing(Function.identity()).reversed());
        set.add(2);
        set.add(3);
        set.add(3);
        Integer num = set.lower(2);
    }

    private void hashSet() {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        set.add(1);
    }

    private void weakHashMap() {
        Map<String, Integer> weakHashMap = new WeakHashMap<>();
        String string1 = new String("1");
        String string2 = new String("2");
        String string3 = new String("3");
        weakHashMap.put(string1, 1);
        weakHashMap.put(string2, 2);
        weakHashMap.put(string3, 3);

        string1 = null;
        System.gc(); //内存回收

        Iterator iterator = weakHashMap.keySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private void hashMap() {
        Map<Integer, String> hashMap = new HashMap<>(32, 0.6f);
        //同步hashMap
        hashMap = Collections.synchronizedMap(hashMap);
        hashMap.put(1, "刘灿");
        hashMap.put(2, "周雯媚");

        //使用方式和treeMap实现一样
        Set<Map.Entry<Integer, String>> entry = hashMap.entrySet();
        hashMap.keySet();
        hashMap.values();

        //边遍历边删除
        Iterator iterator = entry.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
    }

    @SuppressWarnings("unchecked")
    private void treeMap() {
        //自定义比较器
        TreeMap<Integer, Integer> treeMap = new TreeMap(Comparator.comparing(Function.identity()).reversed());
        treeMap.put(4, 0);
        treeMap.put(3, 0);
        treeMap.put(10, 0);
        treeMap.put(50, 0);

        //遍历key
        Iterator keyIterator = treeMap.keySet().iterator();
        while (keyIterator.hasNext()) {
            System.out.println(keyIterator.next());
        }

        //遍历entryKey
        Iterator entryIterator = treeMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            System.out.println(entryIterator.next());
        }

        //遍历value
        Iterator valueIterator = treeMap.values().iterator();
        while (valueIterator.hasNext()) {
            System.out.println(valueIterator.next());
        }

        //查找大于或者小于key的map
        Map.Entry entry = treeMap.lowerEntry(50);
        Map.Entry entry1 = treeMap.higherEntry(50);

        //反向map
        Map<Integer, Integer> treeMap1 = treeMap.descendingMap();
    }

    private void vector() {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
    }

    private void iter() {
        List<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }

    private void dictionary() {
        //Dictionary是抽象类需要自己实现和map一样，但是已经过时了，用map
        Map<Integer, Integer> map = new HashMap<>(); //散列表
        map = new TreeMap<>(); //红黑树
        map = new Hashtable<>(); //t是小写，哈哈
    }

    private void stack() {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        Integer i = (Integer) stack.peek();
    }

    private void bitSet() {
        //注意BitSet里面的保存顺序为低位字节数据到高位字节
        BitSet bitSet = new BitSet();
        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(3);
        int m = bitSet.cardinality();
        boolean b = bitSet.get(0);
        b = bitSet.get(3);
        m = bitSet.length();
        m = bitSet.size();
        long[] longs = bitSet.toLongArray();
        byte[] bytes = bitSet.toByteArray();
        String string = bitSet.toString();

        bitSet = BitSet.valueOf(new long[]{53});

        BitSet bitSet1 = BitSet.valueOf(new long[]{3});
        bitSet.and(bitSet1);
    }

    public class A {
        protected String string = "A";

        protected void show() {
            System.out.println(string);
        }
    }

    public class B extends A {
        protected String string = "B";

    }

    private void tryCatch() {
        System.out.println(tryCatch1());
    }

    private int tryCatch1() {
        int b = 20;
        try {
            return b += 80;
        } finally {
            if (b > 50) {
                System.out.println("do 1");
            }
            return 300;
        }
    }

    private void fileStream() {
        //从文件读取数据
        try (FileInputStream fileInputStream = new FileInputStream("/home/liucan/Documents/常用数据")) {
            byte[] file = new byte[1024];
            int readLen;
            int offset = 0;
            do {
                readLen = fileInputStream.read(file, offset, 200);
                offset += readLen;
            } while (readLen != -1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        File file = new File(Constants.TMP_PATH);
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("创建文件夹成功" + file.getPath());
            }
        }

        //写入到文件中
        try (FileOutputStream fileOutputStream = new FileOutputStream(Constants.TMP_PATH + "stream-test");
             OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream)) {
            writer.append("\"你好啊！\"\n");
            writer.append("\'hello world !\'\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stream() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            //从输入流里面读取一个字符并作为整数返回
            char c = (char) bufferedReader.read();
            System.out.println(c);
            //从输入流里面读取字符串
            String str = bufferedReader.readLine();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void tryWithResource() {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("~/Documents/常用数据");
            byte[] file = new byte[1024];
            inputStream.read(file, 0, 1024);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }

        //凡是实现了AutoCloseable接口的都可以是使用try-with-resource,能保证走到finally里面关闭
        //在块中使用多个资源而且这些资源都能被自动地关闭
        try (FileInputStream inputStream1 = new FileInputStream("~/Documents/常用数据");
             BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream1)) {
            byte[] file = new byte[1024];
            int m = inputStream1.read(file, 0, 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printNumbers(int... numbers) {
        for (int number : numbers) {
            System.out.println(number);
        }
    }

    private void collections() {
        int[] arrayVar = {1, 6, 3, 4, 5};
        Arrays.sort(arrayVar);
        //二分查找
        int ab = Arrays.binarySearch(arrayVar, 3);
        Collections.emptyList();

        //转义字符\"
        System.out.println("she said \"hello\" to me");
    }

    private void baseString() {
        String string = "hello world!";
        int index = string.lastIndexOf("h");

        //线程安全
        StringBuffer sb = new StringBuffer(string);
        sb.append(1);
        sb.delete(1, 2);
    }

    private void baseType() {
        char a = 'b'; //2字节,unicode字符
        a = '\u0011'; //unicode文字使用 \\uxxxx 格式
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
    }

}
