package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * @Description: 运行器Mock
 * @Author: weilu
 * @Time: 2017/11/4 0004 10:50.
 */

@RunWith(MockitoJUnitRunner::class) //<--使用MockitoJUnitRunner
class MockitoJUnitRunnerTest {

    @Mock //<--使用@Mock注解
    lateinit var mPerson: Person

    @Test
    fun testIsNotNull() {
        assertNotNull(mPerson)
    }

}
