package com.zl.weilu.androidut.robolectric.service

import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.service.MyService

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ServiceController
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * @Description: 自定义服务测试
 * @Author: weilu
 * @Time: 2017/12/4 11:40.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class MyServiceTest {

    private var controller: ServiceController<MyService>? = null
    private var mService: MyService? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out
        controller = Robolectric.buildService(MyService::class.java)
        mService = controller!!.get()
    }

    /**
     * 控制Service生命周期进行验证
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testServiceLifecycle() {
        controller!!.create()
        controller!!.startCommand(0, 0)
        controller!!.bind()
        controller!!.unbind()
        controller!!.destroy()
    }
}
