package com.zl.weilu.androidut.assertj;

import org.assertj.core.util.Lists;
import org.assertj.core.util.Maps;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.util.DateUtil.*;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * @Description: AssertJ测试用例（参考：http://sgq0085.iteye.com/blog/2030609）
 * @Author: weilu
 * @Time: 2018/5/15 0015 09:33.
 */
public class AssertJTest {

    @Test
    public void testString(){
        String str = null;
        // 断言null或者为空字符串
        assertThat(str).isNullOrEmpty();
        // 断言空字符串
        assertThat("").isEmpty();
        // 断言字符串相等 断言忽略大小写判断字符串相等  
        assertThat("weilu").isEqualTo("weilu").isEqualToIgnoringCase("WeiLu");
        // 断言开始字符串 结束字符穿 字符串长度  
        assertThat("weilu").startsWith("w").endsWith("lu").hasSize(5);
        // 断言包含字符串 不包含字符串  
        assertThat("weilu").contains("il").doesNotContain("ll");
        // 断言字符串只出现过一次  
        assertThat("weilu").containsOnlyOnce("ei");
        // 判断正则匹配  
        assertThat("weilu").matches("..i.u").doesNotMatch(".*d");
        // as指定错误信息
        // assertThat("weilu").as("没有a或者b").contains("a").contains("b");
    }

    @Test
    public void testNumber() {
        Integer num = null;
        // 断言空  
        assertThat(num).isNull();
        // 断言相等  
        assertThat(42).isEqualTo(42);
        // 断言大于 大于等于  
        assertThat(42).isGreaterThan(38).isGreaterThanOrEqualTo(38);
        // 断言小于 小于等于  
        assertThat(42).isLessThan(58).isLessThanOrEqualTo(58);
        // 断言0  
        assertThat(0).isZero();
        // 断言正数 非负数  
        assertThat(1).isPositive().isNotNegative();
        // 断言负数 非正数  
        assertThat(-1).isNegative().isNotPositive();
    }

    @Test
    public void testDate() {
        // 断言与指定日期相同 不相同 在指定日期之后 在指定日期之钱  
        assertThat(parse("2018-05-15"))
                .isEqualTo("2018-05-15")
                .isNotEqualTo("2018-05-14")
                .isAfter("2018-04-01")
                .isBefore("2029-03-01");
        // 断言 2018 在指定年份之前 在指定年份之后  
        assertThat(now()).isBeforeYear(2030).isAfterYear(2017);
        // 断言时间再指定范围内 不在指定范围内  
        assertThat(parse("2018-05-15"))
                .isBetween("2018-04-01", "2018-06-01")
                .isNotBetween("2019-01-01", "2019-12-31");

        // 断言两时间相差100毫秒  
        Date d1 = new Date();
        Date d2 = new Date(d1.getTime() + 100);
        assertThat(d1).isCloseTo(d2, 100);

        Date date1 = parseDatetimeWithMs("2018-01-01T01:00:00.000");
        Date date2 = parseDatetimeWithMs("2018-01-01T01:00:00.555");
        Date date3 = parseDatetimeWithMs("2018-01-01T01:00:55.555");
        Date date4 = parseDatetimeWithMs("2018-01-01T01:55:55.555");
        Date date5 = parseDatetimeWithMs("2018-01-01T05:55:55.555");

        // 断言 日期忽略毫秒，与给定的日期相等  
        assertThat(date1).isEqualToIgnoringMillis(date2);
        // 断言 日期与给定的日期具有相同的年月日时分秒  
        assertThat(date1).isInSameSecondAs(date2);
        // 断言 日期忽略秒，与给定的日期时间相等  
        assertThat(date1).isEqualToIgnoringSeconds(date3);
        // 断言 日期与给定的日期具有相同的年月日时分  
        assertThat(date1).isInSameMinuteAs(date3);
        // 断言 日期忽略分，与给定的日期时间相等  
        assertThat(date1).isEqualToIgnoringMinutes(date4);
        // 断言 日期与给定的日期具有相同的年月日时  
        assertThat(date1).isInSameHourAs(date4);
        // 断言 日期忽略小时，与给定的日期时间相等  
        assertThat(date1).isEqualToIgnoringHours(date5);
        // 断言 日期与给定的日期具有相同的年月日  
        assertThat(date1).isInSameDayAs(date5);
    }

    @Test
    public void testList() {
        // 断言 列表是空的  
        assertThat(newArrayList()).isEmpty();
        // 断言 列表的开始 结束元素  
        assertThat(newArrayList(1, 2, 3)).startsWith(1).endsWith(3);
        // 断言 列表包含元素 并且是排序的  
        assertThat(newArrayList(1, 2, 3))
                .contains(1, atIndex(0))
                .contains(2, atIndex(1))
                .contains(3)
                .isSorted();
        // 断言 被包含与给定列表  
        assertThat(newArrayList(3, 1, 2)).isSubsetOf(newArrayList(1, 2, 3, 4));
        // 断言 存在唯一元素  
        assertThat(Lists.newArrayList("a", "b", "c")).containsOnlyOnce("a");
    }

    @Test
    public void testMap() {
        Map<String, Integer> foo = Maps.newHashMap("A", 1);
        foo.put("B", 2);
        foo.put("C", 3);

        // 断言 map 不为空 size  
        assertThat(foo).isNotEmpty().hasSize(3);
        // 断言 map 包含元素  
        assertThat(foo).contains(entry("A", 1), entry("B", 2));
        // 断言 map 包含key  
        assertThat(foo).containsKeys("A", "B", "C");
        // 断言 map 包含value  
        assertThat(foo).containsValue(3);
    }
    
}
