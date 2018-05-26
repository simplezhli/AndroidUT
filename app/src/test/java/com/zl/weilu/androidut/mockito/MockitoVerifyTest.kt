package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

/**
 * @Description: 常用验证方法示例
 * @Author: weilu
 * @Time: 2017/11/4 11:51.
 */

class MockitoVerifyTest {

    @Mock
    lateinit var mPerson: Person

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    fun testPersonVerifyAfter() {
        `when`(mPerson.age).thenReturn(18)
        //延时1s验证
        println(mPerson.age)
        println(System.currentTimeMillis())
        verify<Person>(mPerson, after(1000)).age
        println(System.currentTimeMillis())
        // 抛出异常
        // verify(mPerson, atLeast(2)).age
    }

    @Test
    fun testPersonVerifyAtLeast() {
        mPerson.age
        mPerson.age
        //至少验证2次
        verify<Person>(mPerson, atLeast(2)).age
    }

    @Test
    fun testPersonVerifyAtMost() {
        mPerson.age
        //至多验证2次
        verify<Person>(mPerson, atMost(2)).age
    }

    @Test
    fun testPersonVerifyTimes() {
        mPerson.age
        mPerson.age
        //验证方法在100ms超时前调用2次
        verify<Person>(mPerson, timeout(100).times(2)).age
        reset<Person>(mPerson)
    }

}
