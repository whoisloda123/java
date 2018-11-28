package com.liucan.regex;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 参考：http://www.runoob.com/java/java-regular-expressions.html
 * https://www.cnblogs.com/chenshengjava/p/8584293.html
 * <p>
 * 正则表达式组
 * ((A)(B(C)))，其组的数量是从左边开始计算（的数量，从1开始，即使有嵌套也是一样的
 * 上面，总共4组（4个'（'）,第一组((A)(B(C)))，第二组(A)，第三组(B(C))，第四组(C)
 * 组号0是全部，通过group(index)来获取组里面内容
 * 而我们如果不加（），组数是0，用group（），或group（0）获取到全部
 *
 * @author liucan
 * @version 18-11-25
 */
@Component
public class Regex {

    public void example() {
        try {
            String string = "a5b4c";
            String regex = "\\d+(\\.\\d+)?";
            //注意matches是匹配整个字符串,,lookingAt()匹配文本开头
            boolean bMatch = Pattern.matches(regex, string);

            //split,用正则表达式作为分隔符
            Pattern pattern = Pattern.compile(regex);
            String[] split = pattern.split(string);

            //返回正则表达式
            String tmp = pattern.pattern();

            //遍历
            Matcher matcher = Pattern.compile("\\d+(\\.\\d+)?").matcher(string);
            int groupCount = matcher.groupCount();
            while (matcher.find()) {
                String result = matcher.group();
                int start = matcher.start();
                int end = matcher.end();
                System.out.println(result);
            }

            //replaceFirst,replaceAll
            tmp = matcher.replaceAll("你");
            tmp = matcher.replaceFirst("好");

            //appendReplacement,appendTail也是文本替换，但是比replaceAll粒度更细
            matcher.reset(); //重置matcher状态
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                if (matcher.start() == 1) {
                    matcher.appendReplacement(sb, "你");
                } else {
                    matcher.appendReplacement(sb, "好");
                }
            }
            matcher.appendTail(sb);
            System.out.println(sb.toString());

            //分组
            matcher = Pattern.compile("\\{taobaoid=(\\d+)}").matcher("你好{taobaoid=54524555}啊，你想到{taobaoid=4424555}爽肤水是否{taobaoid=54523555}的");
            groupCount = matcher.groupCount();
            while (matcher.find()) {
                tmp = matcher.group(1);
                matcher.start();
                matcher.end();
            }

            /* *
             *             匹配身份证
             *             定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）

             *             假设18位身份证号码:41000119910101123X  410001 19910101 123X
             *             ^开头
             *             [1-9] 第一位1-9中的一个      4
             *             \\d{5} 五位数字           10001（前六位省市县地区）
             *             (18|19|20)                19（现阶段可能取值范围18xx-20xx年）
             *             \\d{2}                    91（年份）
             *             ((0[1-9])|(10|11|12))     01（月份）
             *             (([0-2][1-9])|10|20|30|31)01（日期）
             *             \\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
             *             [0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
             *             $结尾
             *
             *             假设15位身份证号码:410001910101123  410001 910101 123
             *             ^开头
             *             [1-9] 第一位1-9中的一个      4
             *             \\d{5} 五位数字           10001（前六位省市县地区）
             *             \\d{2}                    91（年份）
             *             ((0[1-9])|(10|11|12))     01（月份）
             *             (([0-2][1-9])|10|20|30|31)01（日期）
             *             \\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
             *             $结尾
             */
            String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                    "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
            String input = "500242199006286876";
            bMatch = Pattern.matches(regularExpression, input);
            matcher = Pattern.compile(regularExpression).matcher(input);
            groupCount = matcher.groupCount();

            System.out.println(groupCount);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            System.out.println("正则表达式语法有问题，" + e.getPattern());
        }
    }
}
