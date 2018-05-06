package com.liucan.test;

import com.liucan.test.FunctionalInterface.MyInterface;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

@Component
public class FunctionalInterfaceTest extends BaseTest {
    @Override
    public void testAll() {
        super.testAll();

        /*
         * Lambda 表达式通常使用 (argument) -> (body) 语法书写
         * 1.一个 Lambda 表达式可以有零个或多个参数
         * 2.参数的类型既可以明确声明，也可以根据上下文来推断。例如：(int a)与(a)效果相同
         * 3.所有参数需包含在圆括号内，参数之间用逗号相隔。例如：(a, b) 或 (int a, int b) 或 (String a, int b, float c)
         * 4.空圆括号代表参数集为空。例如：() -> 42
         * 5.当只有一个参数，且其类型可推导时，圆括号（）可省略。例如：a -> return a*a
         * 6.Lambda 表达式的主体可包含零条或多条语句
         * 7.如果 Lambda 表达式的主体只有一条语句，花括号{}可省略。匿名函数的返回类型与该主体表达式一致
         * 8.如果 Lambda 表达式的主体包含一条以上语句，则表达式必须包含在花括号{}中（形成代码块）。匿名函数的返回类型与代码块的返回类型一致，若没有返回则为
         **/

        //lambda可直接赋值给函数式接口
        MyInterface myInterface = b -> b++;

        //旧方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from thread");
            }
        }).start();
        //新方法
        new Thread(
                () -> System.out.println("Hello from thread")
        ).start();

        //Old way:
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        for(Integer n: list) {
            System.out.println(n);
        }

        //New way
        list.forEach(n -> System.out.println(n));
        /*
        为了配合λ表达式的使用，需要一些作为形参的函数接口java.util.function

        *从方法的类型签名分析，java.util.function包的核心函数接口有4个。
        函数型T ->R，完成参数类型T向结果类型R的转换。核心函数接口Function
        判断型T ->boolean，核心函数接口Predicate/谓词
        消费型T ->void，核心函数接口Consumer
        供给型void->T，核心函数接口Supplier
        * */
        //function
        Function<Integer, Integer> function1 = n -> {n = n*2; return n+1;};
        Function<Integer, Integer> function2 = n -> n*n;
        int value1 = function1.andThen(function2).apply(3);
        System.out.println("andThen value=" + value1);
        int value2 = function1.compose(function2).apply(3);
        System.out.println("andThen value=" + value2);

        //predicate
        Predicate<Integer> predicate1 = n -> n > 0;
        boolean result = predicate1.test(1);

        IntPredicate predicate2 = n -> n > 0;
        IntPredicate predicate3 = n -> n == 0;
        result = predicate2.and(predicate3).test(3);
        result = predicate2.or(n -> n == 0).test(3);

        //consumer
        IntConsumer intConsumer = n -> {n++; System.out.println("value=" + n);};
        intConsumer.accept(3);

        //supplier
        Supplier<Long> supplier = () -> Instant.now().getEpochSecond();
        Long timeSpan = supplier.get();
    }
}
