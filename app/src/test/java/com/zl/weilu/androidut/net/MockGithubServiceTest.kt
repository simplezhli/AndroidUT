package com.zl.weilu.androidut.net

import android.util.Log
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.bean.User
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
import java.net.URISyntaxException

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class MockGithubServiceTest {

    private var jsonFullPath: String? = null
    private var mockGithubService: GithubApi? = null

    @get:Rule
    var rule = RxJavaRule()

    @Before
    @Throws(URISyntaxException::class)
    fun setUp() {

        ShadowLog.stream = System.out
        //获取测试json文件地址
        jsonFullPath = javaClass.getResource(JSON_ROOT_PATH).toURI().getPath()

        //定义Http Client,并添加拦截器
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(MockInterceptor(jsonFullPath!!))
                .build()

        //设置Http Client
        val retrofit = Retrofit.Builder()
                .baseUrl(GithubApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        mockGithubService = retrofit.create<GithubApi>(GithubApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun getUserTest() {
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

    companion object {

        private val JSON_ROOT_PATH = "/json/"
    }

}
