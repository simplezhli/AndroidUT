package com.zl.weilu.androidut.robolectric.broadcast

import android.content.Intent
import android.preference.PreferenceManager
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.broadcast.MyReceiver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication

/**
 * @Description: 广播测试
 * @Author: weilu
 * @Time: 2017/12/4 11:25.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class MyReceiverTest {

    private val action = "com.zl.weilu.androidut"

    @Test
    @Throws(Exception::class)
    fun testRegister() {
        val shadowApplication = ShadowApplication.getInstance()
        val intent = Intent(action)
        // 验证是否注册了相应的Receiver
        assertTrue(shadowApplication.hasReceiverForIntent(intent))
    }

    @Test
    @Throws(Exception::class)
    fun testReceive() {
        //发送广播
        val intent = Intent(action)
        intent.putExtra(MyReceiver.NAME, "AndroidUT")
        val myReceiver = MyReceiver()
        myReceiver.onReceive(RuntimeEnvironment.application, intent)
        //验证广播的处理逻辑是否正确
        val preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application)
        assertEquals("AndroidUT", preferences.getString(MyReceiver.NAME, ""))
    }
}
