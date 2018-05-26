package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * @Description: 注解方法Mock
 * @Author: weilu
 * @Time: 2017/11/4 10:47.
 */

class MockitoAnnotationsTest {

    @Mock //<--使用@Mock注解
    lateinit var mPerson: Person

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this) //<--初始化
    }

    @Test
    fun testIsNotNull() {
        assertNotNull(mPerson)
    }

}
