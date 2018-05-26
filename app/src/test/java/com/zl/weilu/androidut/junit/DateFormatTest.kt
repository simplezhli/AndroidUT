package com.zl.weilu.androidut.junit

import com.zl.weilu.androidut.utils.DateUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.text.ParseException
import java.util.*

/**
 * Created by weilu on 2017/10/15.
 */

@RunWith(Parameterized::class)
class DateFormatTest(val time: String) {

    @Test(expected = ParseException::class)
    @Throws(Exception::class)
    fun dateToStampTest1() {
        DateUtil.dateToStamp(time)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun primeNumbers(): Collection<String> {
            return Arrays.asList("2017-10-15",
                    // "2017-10-15 16:00:02", // 抛出异常
                    "2017年10月15日 16时00分02秒")
        }
    }
}
