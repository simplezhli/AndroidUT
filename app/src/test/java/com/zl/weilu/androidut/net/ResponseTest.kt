package com.zl.weilu.androidut.net

import android.util.Log
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.bean.User
import io.reactivex.Observer
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/12/22 0022 14:27.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class ResponseTest {

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
        initRxJava2()
    }

    private fun initRxJava2() {
        RxJavaPlugins.reset()
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun getUserTest() {
        GithubService.createGithubService()
                .getUser("simplezhli")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<User> {
                    override fun onSubscribe(d: Disposable) {}

                    override fun onNext(user: User) {
                        assertEquals("唯鹿", user.name)
                        assertEquals("http://blog.csdn.net/qq_17766199", user.blog)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("Test", e.toString())
                    }

                    override fun onComplete() {}
                })
    }
}
