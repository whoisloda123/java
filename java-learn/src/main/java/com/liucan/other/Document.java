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
     *      5.LinkedHashMap
     *          参考：https://blog.csdn.net/justloveyou_/article/details/71713781
     *          a.继承HashMap,是Linked和HashMap的结合,是和HashMap无序不一样，是有序的，用链表来实现
     *          b.在put的时候，除了和HashMap一样的会把Entry放到table数组里面，还会将Entry放到双链表里面
     *          c.Entry里面除了有HashMap的可以，value，next之外，还有before，after指针，用于维护双链表
     *          d.默认顺序是插入顺序，可以设置为操作顺序，可以用来实现LRU（最近最少使用）算法
     *      6.IdentityHashMap
     *          https://blog.csdn.net/f641385712/article/details/81880711
     *          后续有时间看一下？？？？？？？？？？？？？？？？？？？？？？？？？？？？
     *
     *     三.Set
     *      1.HashSet
     *          没有重复元素，完全是基于HashMap来实现的，里面有个HashMap对象的引用，直接操作HashMap,只关注HashMap的key
     *      2.TreeSet
     *          没有重复原因，完全是基于TreeMap来实现的，里面有个TreeMap对象的引用，直接操作TreeMap,只关注TreeMap的key
     *      3.LinkedHashSet
     *          和LinkedHashMap一样的
     *
     *     四.并发包/网络编程/多线程编程
     *      1.后续再看？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
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
     *      e.主表更新，子表也会跟着更新，如果主表删除，子表对应外键哪一行也会跟着删除，即级联删除、更新,从表插入在主表不存在的外键会报错
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
     *          b."key-value"形式的数据结构中，key 可以是弱引用。例如 WeakHashMap
     *          c.观察者模式（特别是事件处理）中，观察者或者 listener 如果采用弱引用，则不需要显式的移除
     *          d.缓存
     *      d.PhantomReference虚引用，该应用并不能获取到任何对象，也不会影响对象生命周期，主要是和引用队列一起使用，监控对象被回收的时候，做一些额外处理
     *          a.通过虚引用可以知道一个对象何时被清除出内存。事实上，这也是唯一的途径
     *          b.防止对象在 finalize 方法中被重新“救活”（可参考《深入理解 Java 虚拟机》一书）
     *      e.ReferenceQueue引用队列，当引用对象所引用的值被回收了，该引用对象会被放到引用队列里面，不过需要我们手动处理来回收该引用对象，如WeakHashMap
     *        引用队列一般和软引用，弱引用，虚引用一起用
     *
     *   27.String直接赋值和new区别
     *      参考：https://blog.csdn.net/zqzq310918/article/details/54313262
     *      a.直接赋值是如果不存在常量池（constant pool），就在常量池创建，以后同样的字符串赋值，则直接指向常量池
     *      b.new是每次都在堆中开辟空间
     *      c.常量池:存放字符串常量和基本类型常量（public static final）
     *
     *   28.java8默认方法
     *      Java8中引入的默认方法，充分展示了Java平台中概念的一致性与JDK向前兼容之间的矛盾，并且悲哀地。以牺牲概念的一致性而满足JDK向前兼容
     *      主要是为了向前兼容，但是又不能修改已经发版的版本，所以就搞出了默认方法，为了解决接口的修改与现有的实现不兼容的问题
     *
     *   29.参数传递
     *      java里面都是值传递，复制对象的地址，而基本类型数据的话，是改变不了里面的数据的
     *
     *   30.为何byte取值范围是[-128~127],而不是[-127~127]
     *      参考：https://blog.csdn.net/qq_23418393/article/details/57421688
     *      1.计算机里面是用补码进行数字运算
     *      2.原码第一位表示符号, 其余位表示值，人的正常思维 8（0000 1000），-8（1000 1000）
     *      3.反码，正数是本身，负数是符号位不变其余位取反 8（0000 1000）， -8（1111 0111）
     *      4.补码，正数是本身，负数反码+1 8（0000 1000） -8（1111 1000）
     *      5.用原码来进行加减不能得到正确结果，用反码来进行加减可以得到正确结果，范围是[-127~127],但是会出现+0（0000 0000），-0（1000 0000）都表示0
     *      6.而用补码来进行加减，可以得到正确结果，而且补码计算出来的-128（1000 0000）就是用反码表示的-0，计算出来的+0（0000 0000）可以表示0
     *        所以用补码来进行加减运算既能够解决+0和-0问题，而且能够多表示一位数字-128，所以说为什么byte的取值范围是[-128~127]
     *
     *   31.java内存区域分配和gc（garbage collection）机制
     *      参考：https://www.cnblogs.com/zymyes2020/p/9052651.html
     *      https://www.cnblogs.com/xiaoxi/p/6486852.html
     *      gc青年代，老年代，永久代理论是基于大多数对象的很快就会变得不可达，只有极少数情况会出现旧对象持有新对象的引用
     *      java,gc没有使用引用计数法来回收内存，引用计数法简单，高效，但是致命问题不能解决循环引用问题
     *
     *     一.gc
     *      1.stop-the-world:进行gc的时候，除了gc线程外其他线程都必须要停止下来，来进行gc工作，gc调优通常就是为了改善stop-the-world时间
     *      2.新生代：所有刚开始new的对象都会放入此，分为1个Eden区，2个Survivor区，经过在Survivor区的不停转移来进行gc，Eden区满了，清理并将还存在的对象放入
     *        Survivor1区， Survivor1区满了，清理并将Eden和Survivor1还存在的对象全部放入Survivor1，如此循环反复（Survivor区始终有一个是空的）， 寿命长的对象
     *        被转移到老年区，该方法就是停止-复制算法
     *      3.老年代：
     *          a.标记-清除算法：标记所有需要回收的对象，再清除标记对象，坏处会产生很多内存碎片
     *          b.标记-整理算法：标记所有需要回收的对象，然后将存活对象向一端一端，清除掉其他内存，好处是不会产生内存碎片
     *          c.一般采用标记-整理算法
     *      4.永久代（方法区）：方法区主要回收内容是废弃常量和无用的类，满足回收需要类是无用类，无用类需要满足以下3个条件，
     *          a.该类所有的实例已经被回收
     *          b.加载该类的ClassLoader已经被回收
     *          c.该类对应的java.lang.Class对象没有在任何地方别引用，无法在任何地方通过反射访问该类的方法
     *        满足以上3个可以进行垃圾回收，但并不是马上就回收
     *      5.收集器：一般jvm是HotSpot
     *          新生代一般用停止复制算法，老年代一般用标记-清除和标记-整理算法
     *          a.新生代：Serial收集器(单线程停止复制算法)，ParNew收集器（多线程停止复制算法），Parallel Scavenge收集器（多线程停止复制算法）
     *            Parallel Scavenge收集器关注的是吞吐量（垃圾器收集的时间和总运行时间比例），虚拟机运行在Server模式下的默认垃圾收集器，
     *            而其他2个关注的是每次停顿时间
     *          b.老年代：Serial Old收集器（单线程标记-整理算法），Parallel Old收集器（多线程标记-整理算法）
     *            CMS收集器（以获取最短回收停顿时间为目标的收集器。使用标记 - 清除算法），G1收集器
     *            在注重吞吐量以及CPU资源敏感的场合，都可以优先考虑Parallel Scavenge收集器+Parallel Old收集器的组合
     *       6.注意：
     *         可能存在年老代对象引用新生代对象的情况，如果需要执行Young GC，则可能需要查询整个老年代以确定是否可以清理回收，这显然是低效的。解决的方法是，
     *         年老代中维护一个512 byte的块——”card table“，所有老年代对象引用新生代对象的记录都记录在这里。Young GC时，只要查这里即可，
     *         不用再去查全部老年代，因此性能大大提高
     *
     *     二.运行内存分布
     *      1.堆区
     *      2.方法区：存储已加载的类信息（版本，field，方法，接口等等），常量池（final常量，静态常量）等等
     *      3.虚拟机栈：普通方法栈，方法执行是一个入栈和出栈的过程
     *          a.栈由一系列帧组成（因此Java栈也叫做帧栈）先进后出的数据结构
     *          b.每一次方法调用创建一个新的帧，并压栈
     *      4.本地方法栈：native方法栈
     *      5.程序计数器（PC寄存器）：记录当前线程执行的字节码到第几行
     *      其中堆区和方法区线程共享，其他非线程共享
     *
     *   32.jvm
     *    1.每个java程序运行起来就会产生一个jvm实例，java程序结束jvm实例就会消失
     *
     *   33.类加载
     *      参考：https://www.cnblogs.com/qiuyong/p/6407418.html?utm_source=itdadao&utm_medium=referral
     *      http://www.importnew.com/25295.html
     *      jvm的生命周期一个类只被加载一次
     *    一.过程：jvm类加载过程包括 加载-链接（校验-准备-解析）-初始化
     *      1.加载：
     *          a.class文件加载内存
     *          b.将静态数据结构(数据存在于class文件的结构)转化成方法区中运行时的数据结构(数据存在于JVM时的数据结构)
     *          c.堆中生成java.lang.Class对象，代表加载的对象，作为数据访问入口
     *      2.链接
     *          a.验证：确保加载的类符合规范和安全
     *          b.准备：为static变量分配空间，设置变量初始值
     *          c.解析：将常量池的符号引用（符号可以是任何形式的字面量，只要使用时能无歧义地定位到目标即可）转换为直接引用（指针）
     *      3.初始化
     *          a.执行类构造器<clinit>()方法,它将由编译器自动收集类中的所有类变量的赋值动作(准备阶段的a正是被赋值a)和静态变量与静态语句块static{}合并
     *      4.clinit和init区别
     *          参考：https://blog.csdn.net/u013309870/article/details/72975536
     *          a.init是对象构造器方法，在new一个对象时候，调用构造函数
     *          b.  1.clinit是类构造器方法，在类加载的初始化阶段，只会加载一次，执行类变量赋值和静态语句块
     *              2.子类clinit执行会保证父类的clinit执行
     *              3.接口的clinit执行不会执行父接口的clinit方法，只有父接口定义变量使用才会初始化
     *              4.接口实现类初始化一样不会执行接口的clinit方法
     *
     *    二.类加载器
     *      1.加载器
     *          a.启动类加载器：Bootstrap ClassLoader,加载java_home/lib下的class类库
     *          b.扩展类加载器：Extension ClassLoader,加载JAVA_HOME/lib/ext下的class类库
     *          c.应用程序类加载器：Application ClassLoader,加载用户路径（classpath）上的类库
     *      2.机制:双亲委派
     *          双亲委派加载，调用父类的加载器加载，如果不行才自己加载，好处是安全，防止自己写string等,而且加载出来的只有一个object类
     *      3.Class.forName和ClassLoader.loaderClass区别
     *          Class.forName得到的class是已经初始化完成的，ClassLoader.loaderClass得到的class是还没有链接的
     *
     *  34.为null对mysql索引的影响，空值和null区别
     *      a.空值和null区别:空值不占用空间，用 = <> != 来判断，而null是占用空间的，用is null或is not null判断
     *      b.null在计算数目或者sum等是不计算在内的
     *      c.null对mysql索引影响
     *          1.null需要占用额外空间
     *          2.null不走索引，且会使索引统计更加复杂一些
     *      d.最好把字段设置为不为null，设置默认值，字符串设为空值'',数字设为0
     *
     *  35.mysql事务(innodb),mysql的引擎中只有innodb支持事务
     *      参考：https://blog.csdn.net/w_linux/article/details/79666086、
     *      https://www.cnblogs.com/cjsblog/p/8365921.html
     *      a.4大特征(ACID)：
     *          原子性（A）:事务是最小单位，不可再分
     *          一致性（C）:所有的DML要么一起成功，要么一起失败
     *          隔离性（I）:事务之间具有隔离性
     *          持久性（D）:事务commit后能持久话到磁盘
     *      b.在事务进行过程中，未结束之前，DML语句是不会更改底层数据，只是将历史操作记录一下，在内存中完成记录。
     *          只有在事务结束的时候，而且是成功的结束的时候，才会修改底层硬盘文件中的数据
     *      c.隔离性（用于在并发事务执行的时候，如果串行的话，不用隔离）
     *          4个隔离级别
     *              1.读未提交（read uncommitted）
     *                  事务A未提交的数据，事务B可以读取到，此种方法会有脏数据
     *                  隔离级别最低，这种级别一般是在理论上存在，数据库隔离级别一般都高于该级别
     *              2.读已提交（read committed）
     *                  事务A提交了数据，事务才B可以读取到，此种方法没有脏数据，但会出现重复读取的时候，可能结果已经变了
     *              3.可重复读（repeatable read）
     *                  事务A和事务B，事务A提交之后的数据，事务B读取不到，不管A事务是否committed了，重复读取都是第一次，
     *                  对方提交之后的数据，我还是读取不到，不可重复读重点在于update和delete
     *                  但是会出现幻像读，就是读取到结果，出现新增，如范围查询，结果变多了
     *                  MySQL默认级别
     *              4.串行化（serializable）
     *                  事务A和事务B，事务A在操作数据库时，事务B只能排队等待
     *                  这种隔离级别很少使用，吞吐量太低，用户体验差
     *                  可以避免“幻像读”，每一次读取的都是数据库中真实存在数据，事务A与事务B串行，而不并发
     *                  幻读的重点在于insert
     *       d.mvcc(多版本并发控制)
     *          1.解决不可重复读
     *          2.每一行多了创建事务版本号和删除事务版本号
     *          3.插入时、删除时保存当前事务版本号，查询时查询创建版本号小于等于当前事务版本，删除版本号大于等于当前事务版本号
     *              更新时新插入一行，并以当前事务的版本号作为新行的创建版本号，同时将原记录行的删除版本号设置为当前事务版本号
     *       e.快照读和当前读
     *          快照读：读取的是快照版本，也就是历史版本
     *          当前读：读取的是最新版本
     *          普通的SELECT就是快照,UPDATE、DELETE、INSERT、SELECT ...  LOCK IN SHARE MODE、SELECT ... FOR UPDATE是当前读
     *          Consistent read（一致性读）是READ COMMITTED和REPEATABLE READ隔离级别下普通SELECT语句默认的模式。
     *          一致性读不会给它所访问的表加任何形式的锁，因为都是读取快照版本，因此其它事务可以同时并发的修改它们
     *       f.锁
     *          Record Locks（记录锁）：在索引记录上加锁
     *          Gap Locks（间隙锁）：在索引记录之间加锁
     *          Next-Key Locks：在索引记录上加锁，并且在索引记录之前的间隙加锁
     *       g.总结
     *         1.利用MVCC实现一致性非锁定读，保证同一个事务中多次读取相同的数据返回的结果是一样的，解决了不可重复读的问题
     *         2.利用Gap Locks和Next-Key可以阻止其它事务在锁定区间内插入数据，因此解决了幻读问题
     *
     *
     *  学习方向？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
     *  https://www.cnblogs.com/szlbm/p/5437498.html
     *  http://youzhixueyuan.com/各种干货
     */
}
