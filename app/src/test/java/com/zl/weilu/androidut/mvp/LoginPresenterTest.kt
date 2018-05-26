package com.zl.weilu.androidut.mvp

import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.mvp.ui.LoginMvpView
import com.zl.weilu.androidut.mvp.ui.LoginPresenter
import com.zl.weilu.androidut.rxjava.RxJavaTestSchedulerRule
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import java.util.concurrent.TimeUnit


/**
 * Created by weilu on 2018/1/27.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class LoginPresenterTest {

    private lateinit var mPresenter: LoginPresenter

    @Mock
    private lateinit var mvpView: LoginMvpView

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule = RxJavaTestSchedulerRule()

    @Before
    fun setUp() {
        //输出日志
        ShadowLog.stream = System.out

        mPresenter = LoginPresenter()
        mPresenter.attachView(mvpView)
    }

    @Test
    @Throws(Exception::class)
    fun testGetIdentify() {
        mPresenter.getIdentify()
        // 时间到10秒
        rule.testScheduler.advanceTimeTo(10, TimeUnit.SECONDS)
        // 验证方法被调用10次
        verify<LoginMvpView>(mvpView, times(10)).countdownNext(anyString())
        // 时间到120秒
        rule.testScheduler.advanceTimeTo(120, TimeUnit.SECONDS)
        verify<LoginMvpView>(mvpView, times(120)).countdownNext(anyString())
        // 验证倒计时完成方法被调用
        verify<LoginMvpView>(mvpView).countdownComplete()
    }

    @Test
    @Throws(Exception::class)
    fun testLogin() {

        initRxJava()

        mPresenter.login("123", "123")
        verify<LoginMvpView>(mvpView).showToast("手机号码不正确")

        mPresenter.login("13000000000", "123")
        verify<LoginMvpView>(mvpView).showToast("验证码不正确")

        mPresenter.login("13000000000", "123456")

        verify<LoginMvpView>(mvpView).showProgress()

        verify<LoginMvpView>(mvpView).loginSuccess()

        verify<LoginMvpView>(mvpView).closeProgress()

    }

    private fun initRxJava() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }
}
