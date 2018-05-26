package com.zl.weilu.androidut.junit

import com.zl.weilu.androidut.utils.DateUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import java.text.ParseException
import java.util.*

/**
 * Created by weilu on 2017/10/15.
 */
class DateUtilTest {

    private val time = "2017-10-15 16:00:02"

    private val timeStamp = 1508054402000L

    private lateinit var mDate: Date

    @Before
    @Throws(Exception::class)
    fun setUp() {
        println("测试开始！")
        mDate = Date()
        mDate.time = timeStamp
    }

    @After
    @Throws(Exception::class)
    fun tearDown() {
        println("测试结束！")
    }

    @Test
    @Throws(Exception::class)
    fun stampToDateTest() {
        assertEquals(time, DateUtil.stampToDate(timeStamp))
    }

    @Test
    @Throws(Exception::class)
    fun dateToStampTest() {
        assertNotEquals(4, DateUtil.dateToStamp(time))
    }

    @Test(expected = ParseException::class)
    @Throws(Exception::class)
    fun dateToStampTest1() {
        DateUtil.dateToStamp("2017-10-15")
    }

    @Test
    @Ignore("test方法不执行\n")
    fun test() {
        println("-----")
    }
}