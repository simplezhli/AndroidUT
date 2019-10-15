package com.zl.weilu.androidut.rxjava;

import android.app.Application;
import android.widget.TextView;

import com.zl.weilu.androidut.R;
import com.zl.weilu.androidut.ui.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @Description: 测试LoginActivity
 * @Author: weilu
 * @Time: 2018/1/6 15:20.
 */
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    private LoginActivity loginActivity;
    private TextView mTvSendIdentify;

    @Rule
    public RxJavaTestSchedulerRule rule = new RxJavaTestSchedulerRule();

    @Before
    public void setUp(){
        loginActivity = Robolectric.setupActivity(LoginActivity.class);
        mTvSendIdentify = loginActivity.findViewById(R.id.tv_send_identify);
    }

    @Test
    public void testLoginActivity() {
        assertNotNull(loginActivity);
    }

    @Test
    public void testGetIdentify() throws Exception {
        Application application = RuntimeEnvironment.application;
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

}