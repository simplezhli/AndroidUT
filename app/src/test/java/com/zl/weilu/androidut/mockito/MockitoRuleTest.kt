package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

/**
 * @Description: MockitoRule方式Mock
 * @Author: weilu
 * @Time: 2017/11/4 14:43.
 */

class MockitoRuleTest {

    @Mock
    lateinit var mPerson: Person

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    fun testIsNotNull() {
        assertNotNull(mPerson)
    }

}
