package com.zl.weilu.androidut.robolectric.shadow

import android.util.Log
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.bean.Person
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadow.api.Shadow.extract
import org.robolectric.shadows.ShadowLog

/**
 * @Description: 自定义Shadow测试
 * @Author: weilu
 * @Time: 2017/12/4 13:07.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23], shadows = [(ShadowPerson::class)])
class ShadowTest {

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
    }

    @Test
    fun testShadowShadow() {
        val person = Person()
        //实际上调用的是ShadowPerson的方法
        Log.d("test", person.name)
        Log.d("test", person.age.toString())
        Log.d("test", person.sex.toString())

        //获取Person对象对应的Shadow对象
        val shadowPerson = extract<ShadowPerson>(person)
        assertEquals("AndroidUT", shadowPerson.name)
        assertEquals(18, shadowPerson.age)
        assertEquals(0, person.sex)
    }
}
