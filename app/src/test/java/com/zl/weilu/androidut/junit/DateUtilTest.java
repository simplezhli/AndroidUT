package com.zl.weilu.androidut.junit;

import com.zl.weilu.androidut.utils.DateUtil;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by weilu on 2017/10/15.
 */
public class DateUtilTest {

    private String time = "2017-10-15 16:00:02";

    private long timeStamp = 1508054402000L;

    private Date mDate;

    @Before
    public void setUp() throws Exception {
        System.out.println("测试开始！");
        mDate = new Date();
        mDate.setTime(timeStamp);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("测试结束！");
    }

    @Test
    public void stampToDateTest() throws Exception {
        assertEquals(time, DateUtil.stampToDate(timeStamp));
    }

    @Test
    public void dateToStampTest() throws Exception {
        assertNotEquals(4, DateUtil.dateToStamp(time));
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp("2017-10-15");
    }

    @Test
    @Ignore("test方法不执行\n")
    public void test() {
        System.out.println("-----");
    }
}