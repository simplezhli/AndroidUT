package com.zl.weilu.androidut.robolectric.service;

import com.zl.weilu.androidut.BuildConfig;
import com.zl.weilu.androidut.service.MyService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ServiceController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

/**
 * @Description: 自定义服务测试
 * @Author: weilu
 * @Time: 2017/12/4 11:40.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class MyServiceTest {

    private ServiceController<MyService> controller;
    private MyService mService;

    @Before
    public void setUp() throws Exception {
        ShadowLog.stream = System.out;
        controller = Robolectric.buildService(MyService.class);
        mService = controller.get();
    }

    /**
     * 控制Service生命周期进行验证
     *
     * @throws Exception
     */
    @Test
    public void testServiceLifecycle() throws Exception {
        controller.create();
        controller.startCommand(0, 0);
        controller.bind();
        controller.unbind();
        controller.destroy();
    }
}
