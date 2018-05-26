package com.zl.weilu.androidut.powermock

import com.zl.weilu.androidut.bean.Banana

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @Description: mock final方法
 * @Author: weilu
 * @Time: 2017/11/18 11:33.
 */

@RunWith(PowerMockRunner::class)
class PowerMockitoFinalMethodTest {

    @Test
    @PrepareForTest(Banana::class)
    @Throws(Exception::class)
    fun testFinalMethod() {
        val mBanana = PowerMockito.mock<Banana>(Banana::class.java)
        PowerMockito.`when`(mBanana.isLike).thenReturn(false)
        Assert.assertFalse(mBanana.isLike)
    }
}
