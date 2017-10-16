package com.zl.weilu.androidut.junit;

import com.zl.weilu.androidut.utils.DateUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by weilu on 2017/10/15.
 */

@RunWith(Parameterized.class)
public class DateFormatTest {

    private String time;

    public DateFormatTest(String time) {
        this.time = time;
    }

    @Parameterized.Parameters
    public static Collection primeNumbers() {
        return Arrays.asList("2017-10-15",
                "2017-10-15 16:00:02",
                "2017年10月15日 16时00分02秒");
    }

    @Test(expected = ParseException.class)
    public void dateToStampTest1() throws Exception{
        DateUtil.dateToStamp(time);
    }
}
