package com.zl.weilu.androidut.robolectric.broadcast;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.zl.weilu.androidut.broadcast.MyReceiver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowApplication;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

/**
 * @Description: 广播测试
 * @Author: weilu
 * @Time: 2017/12/4 11:25.
 */

@RunWith(AndroidJUnit4.class)
public class MyReceiverTest{
    
    private final String action = "com.zl.weilu.androidut";

    @Test
    public void testRegister() throws Exception {
        //  ShadowApplication shadowApplication = ShadowApplication.getInstance();
        ShadowApplication shadowApplication = shadowOf((Application) getApplicationContext());
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
        
        myReceiver.onReceive(getApplicationContext(), intent);
        //验证广播的处理逻辑是否正确
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        assertEquals( "AndroidUT", preferences.getString(MyReceiver.NAME, ""));
    }
}
