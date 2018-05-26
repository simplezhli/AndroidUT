package com.zl.weilu.androidut.net

import android.util.Log
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.bean.User
import io.appflate.restmock.JVMFileParser
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.RESTMockServerStarter
import io.appflate.restmock.utils.RequestMatchers.pathContains
import io.appflate.restmock.utils.RequestMatchers.pathEndsWith
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by weilu on 2018/5/14.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class RESTMockTest {

    private var mockGithubService: GithubApi? = null

    @get:Rule
    var rule = RxJavaRule()

    @Before
    fun setUp() {
        ShadowLog.stream = System.out
        // 启动服务
        RESTMockServerStarter.startSync(JVMFileParser())

        //定义Http Client,并添加拦截器
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()

        //设置Http Client
        val retrofit = Retrofit.Builder()
                .baseUrl(RESTMockServer.getUrl())  //<--注意这里
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mockGithubService = retrofit.create<GithubApi>(GithubApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun getUserTest() {
        RESTMockServer.whenGET(pathContains("users"))
                //                .delay(TimeUnit.SECONDS, 5) // 模拟响应时长
                .thenReturnFile(200, "json/users.json")

        mockGithubService!!.getUser("weilu")
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

    @Test
    @Throws(Exception::class)
    fun testNotFound() {
        RESTMockServer.whenGET(pathEndsWith("weilu")).thenReturnString(404, "{message : \"服务器异常\"}")
        mockGithubService!!.getUser("weilu")
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
