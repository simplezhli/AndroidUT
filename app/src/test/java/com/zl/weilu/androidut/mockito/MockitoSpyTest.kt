package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Home
import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mockito.`when`
import org.mockito.Spy
import org.mockito.junit.MockitoJUnit

/**
 * @Description: Spy示例
 * @Author: weilu
 * @Time: 2017/11/4 19:12.
 */

class MockitoSpyTest {

    @Spy
    lateinit var mPerson: Person

    @InjectMocks
    lateinit var mHome: Home

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    fun testIsNotNull() {
        assertNotNull(mPerson)
    }

    @Test
    fun testPersonSpy() {
        //输出11
        println(mPerson.age)
    }

    @Test
    fun testHomeInjectMocks() {
        `when`(mPerson.name).thenReturn("weilu")
        println(mHome.master)
    }
}
