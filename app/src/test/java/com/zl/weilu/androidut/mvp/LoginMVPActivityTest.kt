package com.zl.weilu.androidut.mvp

import android.widget.EditText
import android.widget.TextView
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.mvp.ui.LoginMVPActivity
import com.zl.weilu.androidut.rxjava.RxJavaTestSchedulerRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
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
import org.robolectric.shadows.ShadowLog
import org.robolectric.shadows.ShadowProgressDialog
import org.robolectric.shadows.ShadowToast
import java.util.concurrent.TimeUnit


/**
 * Created by weilu on 2018/1/27.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class LoginMVPActivityTest {

    private lateinit var loginActivity: LoginMVPActivity
    private lateinit var mTvSendIdentify: TextView
    private lateinit var mTvLogin: TextView
    private lateinit var mEtMobile: EditText
    private lateinit var mEtIdentify: EditText

    @get:Rule
    var rule = RxJavaTestSchedulerRule()

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
        loginActivity = Robolectric.setupActivity(LoginMVPActivity::class.java)
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify) as TextView
        mTvLogin = loginActivity.findViewById(R.id.tv_login) as TextView
        mEtMobile = loginActivity.findViewById(R.id.et_mobile) as EditText
        mEtIdentify = loginActivity.findViewById(R.id.et_identify) as EditText
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

    @Test
    @Throws(Exception::class)
    fun testLogin() {

        mEtMobile.setText("123")
        mEtIdentify.setText("123")
        mTvLogin.performClick()
        assertEquals("手机号码不正确", ShadowToast.getTextOfLatestToast())

        mEtMobile.setText("13000000000")
        mEtIdentify.setText("123")
        mTvLogin.performClick()
        assertEquals("验证码不正确", ShadowToast.getTextOfLatestToast())

        initRxJava()

        mEtMobile.setText("13000000000")
        mEtIdentify.setText("123456")
        mTvLogin.performClick()

        // 判断ProgressDialog弹出
        assertNotNull(ShadowProgressDialog.getLatestDialog())
        assertEquals("登录成功", ShadowToast.getTextOfLatestToast())
    }

    private fun initRxJava() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

}