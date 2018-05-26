package com.zl.weilu.androidut.rxjava

import android.widget.TextView
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.ui.LoginActivity
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.util.concurrent.TimeUnit

/**
 * @Description: 测试LoginActivity
 * @Author: weilu
 * @Time: 2018/1/6 15:20.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class LoginActivityTest {

    private lateinit var loginActivity: LoginActivity
    private lateinit var mTvSendIdentify: TextView

    @get:Rule
    var rule = RxJavaTestSchedulerRule()

    @Before
    fun setUp() {
        loginActivity = Robolectric.setupActivity(LoginActivity::class.java)
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify) as TextView
    }

    @Test
    fun testLoginActivity() {
        assertNotNull(loginActivity)
    }

    @Test
    @Throws(Exception::class)
    fun testGetIdentify() {
        val application = RuntimeEnvironment.application
        assertEquals(mTvSendIdentify.text.toString(),
                application.getString(R.string.login_send_identify))

        // 触发按钮点击
        mTvSendIdentify.performClick()
        // 时间到10秒
        rule.testScheduler.advanceTimeTo(10, TimeUnit.SECONDS)
        assertEquals(mTvSendIdentify.isEnabled, false)
        assertEquals(mTvSendIdentify.text.toString(), "111秒后重试")

        // 时间到120秒
        rule.testScheduler.advanceTimeTo(120, TimeUnit.SECONDS)

        assertEquals(mTvSendIdentify.text.toString(),
                application.getString(R.string.login_send_identify))
        assertEquals(mTvSendIdentify.isEnabled, true)
    }

}