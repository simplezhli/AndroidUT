package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * @Description: 普通方法Mock
 * @Author: weilu
 * @Time: 2017/11/4 10:44.
 */

class MockitoTest {

    @Test
    fun testIsNotNull() {
        val mPerson = mock<Person>(Person::class.java) //<--使用mock方法

        assertNotNull(mPerson)
    }
}
