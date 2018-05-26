package com.zl.weilu.androidut.powermock

import com.zl.weilu.androidut.bean.Banana
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import org.powermock.reflect.Whitebox

/**
 * @Description: mock静态方法
 * @Author: weilu
 * @Time: 2017/11/18 11:12.
 */

@RunWith(PowerMockRunner::class)
@PrepareForTest(Banana::class)
class PowerMockitoStaticMethodTest {

    @Test
    fun testStaticMethod() {
        PowerMockito.mockStatic(Banana::class.java) //<-- mock静态类
        PowerMockito.`when`(Banana.color()).thenReturn("绿色")
        Assert.assertEquals("绿色", Banana.color())
    }

    /**
     * 更改类的私有static常量
     */
    @Test
    fun testChangeColor() {
        Whitebox.setInternalState(Banana::class.java, "COLOR", "红色的")
        Assert.assertEquals("红色的", Banana.color())
    }
}
