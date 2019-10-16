package com.zl.weilu.androidut.dagger;

import android.app.Application;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.weilu.androidut.R;
import com.zl.weilu.androidut.dagger.ui.LoginDaggerActivity;
import com.zl.weilu.androidut.rxjava.RxJavaTestSchedulerRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowProgressDialog;
import org.robolectric.shadows.ShadowToast;

import java.util.concurrent.TimeUnit;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static androidx.test.core.app.ActivityScenario.launch;
import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


/**
 * Created by weilu on 2018/2/6.
 */
@RunWith(AndroidJUnit4.class)
public class LoginDaggerActivityTest {

    private TextView mTvSendIdentify;
    private TextView mTvLogin;
    private EditText mEtMobile;
    private EditText mEtIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp(){
        ShadowLog.stream = System.out;
        ActivityScenario<LoginDaggerActivity> scenario = launch(LoginDaggerActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.onActivity(activity -> {
            mTvSendIdentify = activity.findViewById(R.id.tv_send_identify);
            mTvLogin = activity.findViewById(R.id.tv_login);
            mEtMobile = activity.findViewById(R.id.et_mobile);
            mEtIdentify = activity.findViewById(R.id.et_identify);
        });
    }

    @Test
    public void testGetIdentify() throws Exception {
        Application application = getApplicationContext();
        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));

        // 触发按钮点击
        mTvSendIdentify.performClick();
        // 时间到10秒
        rule.getTestScheduler().advanceTimeTo(10, TimeUnit.SECONDS);
        assertEquals(mTvSendIdentify.isEnabled(), false);
        assertEquals(mTvSendIdentify.getText().toString(), "111秒后重试");

        // 时间到120秒
        rule.getTestScheduler().advanceTimeTo(120, TimeUnit.SECONDS);

        assertEquals(mTvSendIdentify.getText().toString(),
                application.getString(R.string.login_send_identify));
        assertEquals(mTvSendIdentify.isEnabled(), true);
    }

    @Test
    public void testLogin() throws Exception {

        mEtMobile.setText("123");
        mEtIdentify.setText("123");
        mTvLogin.performClick();
        assertEquals("手机号码不正确", ShadowToast.getTextOfLatestToast());

        mEtMobile.setText("13000000000");
        mEtIdentify.setText("123");
        mTvLogin.performClick();
        assertEquals("验证码不正确", ShadowToast.getTextOfLatestToast());

        initRxJava();

        mEtMobile.setText("13000000000");
        mEtIdentify.setText("123456");
        mTvLogin.performClick();

        // 判断ProgressDialog弹出
        assertNotNull(ShadowProgressDialog.getLatestDialog());
        assertEquals("登录成功", ShadowToast.getTextOfLatestToast());
    }

    private void initRxJava() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

}