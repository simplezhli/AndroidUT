package com.zl.weilu.androidut.mvp;

import com.zl.weilu.androidut.mvp.ui.LoginMvpView;
import com.zl.weilu.androidut.mvp.ui.LoginPresenter;
import com.zl.weilu.androidut.rxjava.RxJavaTestSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.shadows.ShadowLog;

import java.util.concurrent.TimeUnit;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


/**
 * Created by weilu on 2018/1/27.
 */
@RunWith(AndroidJUnit4.class)
public class LoginPresenterTest{

    private LoginPresenter mPresenter;

    @Mock
    private LoginMvpView mvpView;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp(){
        //输出日志
        ShadowLog.stream = System.out;

        mPresenter = new LoginPresenter();
        mPresenter.attachView(mvpView);
    }

    @Test
    public void testGetIdentify() throws Exception {
        mPresenter.getIdentify();
        // 时间到10秒
        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);
        // 验证方法被调用10次
        verify(mvpView, times(10)).countdownNext(anyString());
        // 时间到120秒
        rule.getTestScheduler().advanceTimeTo(120, TimeUnit.SECONDS);
        verify(mvpView, times(120)).countdownNext(anyString());
        // 验证倒计时完成方法被调用
        verify(mvpView).countdownComplete();
    }

    @Test
    public void testLogin() throws Exception {

        initRxJava();

        mPresenter.login("123", "123");
        verify(mvpView).showToast("手机号码不正确");

        mPresenter.login("13000000000", "123");
        verify(mvpView).showToast("验证码不正确");

        mPresenter.login("13000000000", "123456");

        verify(mvpView).showProgress();

        verify(mvpView).loginSuccess();

        verify(mvpView).closeProgress();

    }

    private void initRxJava() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }
}
