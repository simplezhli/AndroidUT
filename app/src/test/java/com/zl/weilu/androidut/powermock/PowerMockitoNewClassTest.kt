package com.zl.weilu.androidut.powermock

import com.zl.weilu.androidut.bean.Person
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.rule.PowerMockRule

/**
 * @Description: mock new方法
 * @Author: weilu
 * @Time: 2017/11/18 19:30.
 */

class PowerMockitoNewClassTest {

    @get:Rule
    var rule = PowerMockRule()

    @Test
    @PrepareForTest(Person::class)
    @Throws(Exception::class)
    fun testNewClass() {
        val mPerson = PowerMockito.mock<Person>(Person::class.java)
        PowerMockito.`when`(mPerson.name).thenReturn("大香蕉")
        //如果new新对象，则返回这个上面设置的这个对象
        PowerMockito.whenNew<Person>(Person::class.java).withNoArguments().thenReturn(mPerson)
        //new新的对象
        val newPerson = Person()
        Assert.assertEquals("大香蕉", newPerson.name)
    }
}
