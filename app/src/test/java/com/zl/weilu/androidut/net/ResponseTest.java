package com.zl.weilu.androidut.net;

import android.util.Log;

import com.zl.weilu.androidut.bean.User;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowLog;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import io.reactivex.Observer;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/12/22 0022 14:27.
 */
@RunWith(AndroidJUnit4.class)
public class ResponseTest {

    @Before
    public void setUp() {
        ShadowLog.stream = System.out;
        initRxJava2();
    }

    private void initRxJava2() {
        RxJavaPlugins.reset();
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.reset();
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }
    
    @Test
    public void getUserTest() {
        GithubService.createGithubService()
                .getUser("simplezhli")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onNext(User user) {
                        assertEquals("唯鹿", user.name);
                        assertEquals("http://blog.csdn.net/qq_17766199", user.blog);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Test", e.toString());
                    }

                    @Override
                    public void onComplete() {}
                });
    }
}
