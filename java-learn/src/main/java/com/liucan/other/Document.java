package com.liucan.other;

/**
 * other学习文档
 * @author liucan
 * @version 2018/9/22
 */
public class Document {
    /* *
     * 1.接口和抽象类区别
     *   a.抽象类，除了有抽象方法外，还可以提供默认的实现,提供属性和方法
     *   b.接口，根本不是类，只是抽象方法的集合
     *   c.场景：抽象类可将公用的方法实现，不公用的子类实现，当关注事物本身的时候，用抽象类，当关注操作行为的时候，或者想多继承的时候，用接口
     *   e.标记接口：里面没有任何方法
     *
     * 2.instanceof是运算符
     *
     * 3.装箱，拆箱
     *   装箱：将基本类型转换为包装器类型（调用如Integer.valueOf）,拆箱：将保证器类型转换为基本类型（调用如：intValue）
     *   Byte,Short,Integer,Long,Character的valueOf的实现都是，如值（-128,127）返回缓存同样对象，如值（128，xx)返回新对象
     *   Boolean的valueOf都相等，Float,Double的valueOf都是返回新对象
     *   equals会先判断对象的类型是否相等，才判断其xxValue是否相等
     *
     * 4.String
     *   String类是不可改变的，创建了String对象，值就无法改变了。如需要对字符串做很多修改，使用StringBuffer & StringBuilder 类,并且不产生多个对象
     *   StringBuilder是java5提出来的是线程不安全的，而StringBuffer是线程安全的
     *
     * 5.Arrays专门操作数组,Collections专门操作集合（List，Set，Map等）
     *
     * 6.可变参数必须是最后一个参数且只能有一个
     *
     * 7.finalize函数是在对象析构之前调用
     *   a.一般在里面做一些释放操作，并非是java,new出来的内存，比如说文件句柄关闭，释放调用c，c++的来分配空间等
     *   b.调用该函数会引起死锁和线程挂起，因为并不存在任何一种机制可以把资源的释放与对象的生命周期完全绑定在一起，如果处理不好还会耗尽资源。
     *   c.java9已经抛弃了,不建议使用
     *
     * 8.stream
     *   a.控制台输入System.in,输出System.out
     *   b.参考https://www.w3cschool.cn/java/java-files-io.html
     *   c.一个流被定义为一个数据序列，输入流用于从源读取数据，输出流用于向目标写数据
     *
     * 9.try-with-resource
     *   a.凡是实现了AutoCloseable接口的都可以是使用try-with-resource,能保证走到finally里面关闭
     *   b.在块中可以使用多个资源而且这些资源都能被自动地关闭
     *
     * 10.exception,error
     *   a.Exception和Error都继承Throwable
     *   b.error一般是系统错误、内存溢出、虚拟机错误、栈溢出等，和程序本身没有关系，且无法处理，表示jvm出了问题
     *   c.注意，注意，注意：在try，catch里return语句，执行之后，返回之前，会执行finally语句，若finally语句有返回值，则从finally里面返回，也可以说用
     *     finally返回值代替try，catch返回值
     *
     * 11.方法重写的规则
     *   a.子类的访问权限只能大于等于父类方法的访问权限
     *
     * 12.多态
     *   a.动态绑定，运行时绑定，相对于静态绑定在编译的时候就决定调用哪个函数，而是在运行的时候决定运行哪个方法
     *   b.具体实现就是每个类有个指向方法区里面虚方法表的指针，里面保存了虚方法的的地址，如果没有重写，则父类的方法地址一样，重写了则指向自己的地址
     *   c.成员变量是不支持多态的，因为不需要，方法是类提供给外部调用的，而变量则是内部使用，不对外公开，没有必要支持
     *   e.具体实现：参考：
     *     https://blog.csdn.net/SEU_Calvin/article/details/52191321，
     *     https://www.cnblogs.com/tsiangleo/p/4415628.html
     *     大概是，类加载后，有各自的方法区，里面保存了
     *     指向不同方法的地址，若没有重写，则和父类的方法区方法地址一样，否则就是子类的方法区方法地址
     *
     * 13.public,default,private
     *    a.外部类，只能用public和default，因为外部类的上一级就是包，权限就只有在包内和包外，没有其他的
     *    b.default在方法上，只有在本包内才可以调用
     *
     * 14.大端、小端字节序,网络字节序
     *    a.大端：高位字节数据放在内存的低地址
     *    b.小端：低位字节数据放在内存的高地址
     *    c.如：0x12345678,在大端cpu里面是0x12,0x34,0x56,0x78,而在小端里面是0x78,0x56,0x34,0x12
     *      Windows，linux操作系统都是小端字节序,字节序和cpu有关系
     *    d.
     *      网络字节序是大端字节序
     *      C++是主机字节序（高、低都有可能）
     *      JAVA是网络字节序、也就是大端字节序
     *    e:参考
     *      https://blog.csdn.net/aitangyong/article/details/23204817
     *      https://www.cnblogs.com/CharlesGrant/p/7201858.html
     *
     *  16.volatile
     *      参考：https://www.cnblogs.com/chengxiao/p/6528109.html
     *     a.轻量级锁
     *     b.能保证共享变量对所有线程的可见性，当写一个volatile变量时，JMM会把该线程对应的本地内存中的变量强制刷新到主内存中去
     *     c.禁止指令重排序优化
     *     d.volatile对于单个的共享变量的读/写具有原子性，但是像num++这种复合操作，volatile无法保证其原子性,可以用原子锁
     *
     *  17.fail-fast,fail-safe迭代器
     *      参考：https://blog.csdn.net/m0_37907797/article/details/80499422
     *     a.fail-fast:快速失败，在迭代过程中，如果数据结构被修改（增加、删除）了则抛出异常，实现机制是modCount和expectedCount比较不相等则抛异常
     *       而在插入和删除的时候会更新modCount
     *
     *       注意：快速失败的行为并不能得到正确的保证，一般来说，存在非同步的并发修改时，不可能做出任何坚决的保证的，fail-fast只是尽
     *       最大努力来抛出异常，所以fail-fast仅用来检查程序的bug
     *
     *     b.fail-safe：安全失败，在迭代的时候，先拷贝一份，然后在迭代，不会抛出异常，但是内容可能不是最新的
     *     c.java.util里面的容器都是快速失败，不支持异步，而java.util.concurrent包里面的容器都是安全失败，支持异步
     *
     *  18.Iterator迭代器
     *
     *  19.JMM（java内存模型）
     *      有时间了解一下？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
     *
     *  20.各种数据集合
     *      参考：http://www.cnblogs.com/skywang12345/p/3323085.html
     *     一.List
     *      a.Vector和ArrayList都是基于数组的，支持动态增长，Vector是线程安全的（synchronized），ArrayList是不安全的，但效率高
     *      b.LinkList是基于双链表实现的，且可以当做栈，双端队列使用，并实现了相应的接口,而ArrayList没有，因为插入和删除的效率比较低
     *      c.实现List接口的常用类有LinkedList，ArrayList，Vector和Stack,而Stack是继承与Vector
     *      d.ArrayList实现了Serializable接口（ObjectOutputStream,ObjectInputStream操作类）,序列化writeObject先写入size，再写入元素
     *     二.Map
     *      1.TreeMap
     *          a.是通过红黑树实现的(http://www.cnblogs.com/skywang12345/p/3245399.html)，
     *            每个节点有left,right,parent,color,key,value
     *          b.实现NavigableMap接口，提供导航方法，返回比指定key大于小于的值
     *          c.实现Cloneable,Serializable支持复制，序列化
     *          d.key默认是自增的，可以通过构造函数传入自定义Comparator比较器
     *          e.时间复杂度log(n)
     *          e.非同步，fail-fast迭代器
     *      2.HashMap
     *          a.影响hashMap性能的2个参数，容量和加载因子，容量为桶的大小，加载因子（默认0.75）为当容量在多少时候自动扩容(大约2倍)，重建内部结构
     *          b.是个散列表，采用是拉链法（单链表）来解决hash冲突的，如果链表长度太大，会变成树
     *          c.非同步，fail-fast迭代器
     *      3.Hashtable
     *          和HashMap实现差不多，只是是线程安全
     *      4.WeakHashMap
     *          a.实现和HashMap差不多
     *          b.其键是弱引用键WeakReference,通过WeakReference和ReferenceQueue实现的
     *          c.当某“弱键”不再被其它对象引用，并被GC回收时。在GC回收该“弱键”时，这个“弱键”也同时会被添加到ReferenceQueue(queue)队列中
     *          d.当下一次操作WeakHashMap时，table中保存了全部的键值对，queue中保存被GC回收的键值对；同步它们，就是删除table中被GC回收的键值对。
     *
     *      4.Hashtable和HashMap和ConcurrentHashMap区别
     *          参考：https://www.cnblogs.com/heyonggang/p/9112731.html
     *          a.t没有大写
     *          b.Hashtable是Dictionary的实现，而Dictionary已经被抛弃了，被map代替了
     *          c.Hashtable是线程安全的，实现方式在修改数据时，直接锁住整个Hashtable，效率低基本上被弃用了，而HashMap线程不安全
     *          d.Hashtable不支持key和value为空，而HashMap，key和value都可以为空，所以通过get来判断是否存在会有问题的
     *          e.ConcurrentHashMap采用分段锁，一次锁住一个桶，效率高，支持线程安全
     *          f.散列表采用拉链法，数组+链表，如果链表的长度太大，则会变成树
     *
     *     三.Set
     *      1.HashSet
     *          没有重复元素，完全是基于HashMap来实现的，里面有个HashMap对象的引用，直接操作HashMap,只关注HashMap的key
     *      2.TreeSet
     *          没有重复原因，完全是基于TreeMap来实现的，里面有个TreeMap对象的引用，直接操作TreeMap,只关注TreeMap的key
     *
     *  21.序列化
     *      参考：https://www.cnblogs.com/sharkli/p/5607895.html
     *          https://blog.csdn.net/Leon_cx/article/details/81517603
     *      a.必须实现Serializable接口或Externalizable接口的类才能进行序列化
     *      b.transient和static修饰符修饰的成员变量不会参与序列化和反序列化
     *      c.反序列化对象和序列化前的对象的全类名和serialVersionUID必须一致,反序列的时候是通过序列化版本号serialVersionUID判断类是否一致
     *        如果被修改了则会异常，如果类不指定会通过属性方法等默认生成，最好不用默认，指定生成一个，这样不会报错
     *      d.在目标类中添加私有的writeObject和readObject方法可以覆盖默认的序列化和反序列化方法,
     *        objectOutputStream的writeObject和ObjectInputStream的readObject里面会通过反射的方式调用类的private的writeObject和readObject方法
     *      e.在目标类中添加私有的readResolve可以最终修改反序列化回来的对象，可用于单例模式防止序列化导致生成第二个对象的问题
     *
     *  22.主键，外建，主表，从表
     *      参考：https://blog.csdn.net/WICHER_WU/article/details/80263486
     *          https://www.cnblogs.com/Brambling/p/9263377.html
     *          https://blog.csdn.net/relaxyu/article/details/81735450
     *      a.B表里面的一个键是跟随A表的键值修改而修改的，B表里面的那个键叫外键，A表叫主表，B表叫从表，
     *      b.作用是保持数据的一致性,主表更新和删除，子表也会跟着变动
     *      c.主键是唯一能确定不一样的
     *      d.具体用法参考上面地址
     *      e.主表更新，字表也会跟着更新，如果主表删除，子表对应外键哪一行也会跟着删除，即级联删除、更新,从表插入在主表不存在的外键会报错
     *
     *   23.clone
     *      a.浅拷贝，深拷贝
     *      b.继承Cloneable接口，重写clone方法，并设为public,而object的clone方法是native
     *      c.浅拷贝有危险，需要深拷贝,默认super.clone是浅拷贝
     *      d.深拷贝一般有2中方法。1.先把对象序列化，再反序列化，2.调用super.clone方法产生新对象，再手动给拷贝，一般用第一种
     *
     *   24.Native
     *      是调用其他语言接口实现的，JNI（java native interface）
     *
     *   25.RandomAccess
     *      a.空接口，表示list支持快速随机访问
     *      b.RandomAccess接口这个空架子的存在，是为了能够更好地判断集合是否ArrayList或者LinkedList，从而能够更好选择更优的遍历方式，提高性能
     *
     *   26.Reference（强引用，软引用，弱引用，虚引用,引用队列）
     *      参考：https://www.cnblogs.com/huajiezh/p/5835618.html
     *          https://www.cnblogs.com/dreamroute/p/5029899.html
     *          https://blog.csdn.net/woblog/article/details/51332342
     *          http://bylijinnan.iteye.com/blog/2085082
     *          https://blog.csdn.net/qq_33663983/article/details/78349641
     *      a.StrongReference强引用，经常用到，只要强引用还在就GC不会回收，可用赋值null方式手动回收
     *      b.SoftReference软引用,有用但是不是非必须的对象，只有在内存不足的时候才会回收该对象，可以解决OOM内存溢出情况
     *        可用来实现内存敏感的高速缓存,比如网页缓存、图片缓存等。使用软引用能防止内存泄露
     *      c.WeakReference弱引用,弱引用的生命周期较软引用更加短暂,GC进行回收的时候，不管当前内存空间是否足够，都会回收
     *          a.在“引用计数法”的垃圾回收机制中，能避免“循环引用”，因为 Weak references are not counted in reference counting
     *          b."key-value"形式的数据结构中，key 可以是弱引用。例如 Map
     *          c.观察者模式（特别是事件处理）中，观察者或者 listener 如果采用弱引用，则不需要显式的移除
     *          d.缓存
     *      d.PhantomReference虚引用，该应用并不能获取到任何对象，也不会影响对象生命周期，主要是和引用队列一起使用，监控对象被回收的时候，做一些额外处理
     *          a.通过虚引用可以知道一个对象何时被清除出内存。事实上，这也是唯一的途径
     *          b.防止对象在 finalize 方法中被重新“救活”（可参考《深入理解 Java 虚拟机》一书）
     *      e.ReferenceQueue引用队列，当引用对象所引用的值被回收了，该引用对象会被放到引用队列里面，不过需要我们手动处理来回收该引用对象，如WeakHashMap
     *        引用队列一般和软引用，弱引用，虚引用一起用
     */
}
