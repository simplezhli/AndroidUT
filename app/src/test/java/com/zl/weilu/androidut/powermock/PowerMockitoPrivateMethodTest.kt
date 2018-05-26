package com.zl.weilu.androidut.powermock

import com.zl.weilu.androidut.bean.Banana

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.support.membermodification.MemberModifier
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

/**
 * @Description: mock私有方法
 * @Author: weilu
 * @Time: 2017/11/18 11:20.
 */

@RunWith(PowerMockRunner::class)
@PrepareForTest(Banana::class)
class PowerMockitoPrivateMethodTest {

    @Test
    @Throws(Exception::class)
    fun testPrivateMethod() {
        val mBanana = PowerMockito.mock<Banana>(Banana::class.java)
        PowerMockito.`when`(mBanana.bananaInfo).thenCallRealMethod()
        PowerMockito.`when`<Any>(mBanana, "flavor").thenReturn("苦苦的")
        Assert.assertEquals("苦苦的黄色的", mBanana.bananaInfo)
        //验证flavor是否调用了一次
        PowerMockito.verifyPrivate(mBanana).invoke("flavor")
    }

    @Test
    fun skipPrivateMethod() {
        val mBanana = Banana
        //跳过flavor方法
        PowerMockito.suppress(PowerMockito.method(Banana::class.java, "flavor"))
        Assert.assertEquals("null黄色的", mBanana.bananaInfo)
    }

    /**
     * 更改父类私有变量
     */
    @Test
    @Throws(Exception::class)
    fun testChangeParentPrivate() {
        val mBanana = Banana
        MemberModifier.field(Banana::class.java, "fruit").set(mBanana, "蔬菜")
        Assert.assertEquals("蔬菜", mBanana.fruit)
    }

}
