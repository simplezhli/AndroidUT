package com.zl.weilu.androidut.robolectric.broadcast;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zl.weilu.androidut.BuildConfig;
import com.zl.weilu.androidut.broadcast.MyReceiver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Description: 广播测试
 * @Author: weilu
 * @Time: 2017/12/4 11:25.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MyReceiverTest{
    
    private final String action = "com.zl.weilu.androidut";

    @Test
    public void testRegister() throws Exception {
        ShadowApplication shadowApplication = ShadowApplication.getInstance();
        Intent intent = new Intent(action);
        // 验证是否注册了相应的Receiver
        assertTrue(shadowApplication.hasReceiverForIntent(intent));
    }

    @Test
    public void testReceive() throws Exception {
        //发送广播
        Intent intent = new Intent(action);
        intent.putExtra(MyReceiver.NAME, "AndroidUT");
        MyReceiver myReceiver = new MyReceiver();
        myReceiver.onReceive(RuntimeEnvironment.application, intent);
        //验证广播的处理逻辑是否正确
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RuntimeEnvironment.application);
        assertEquals( "AndroidUT", preferences.getString(MyReceiver.NAME, ""));
    }
}
