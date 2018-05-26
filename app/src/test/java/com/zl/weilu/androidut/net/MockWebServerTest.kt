package com.zl.weilu.androidut.net

import android.util.Log
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.bean.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
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
import java.util.concurrent.TimeUnit

/**
 * Created by weilu on 2017/12/23.
 */

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class MockWebServerTest {

    private var mockGithubService: GithubApi? = null
    private var server: MockWebServer? = null

    @get:Rule
    var rule = RxJavaRule()

    @Before
    fun setUp() {
        ShadowLog.stream = System.out

        // 创建一个 MockWebServer
        server = MockWebServer()

        val dispatcher = object : Dispatcher() {

            @Throws(InterruptedException::class)
            override fun dispatch(request: RecordedRequest): MockResponse {

                return if (request.path == "/users/simplezhli") {
                    MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .addHeader("Cache-Control", "no-cache")
                            .setBody("{\"id\": 12456431, " +
                                    " \"name\": \"唯鹿\"," +
                                    " \"blog\": \"http://blog.csdn.net/qq_17766199\"}")
                } else {
                    MockResponse()
                            .addHeader("Content-Type", "application/json;charset=utf-8")
                            .setResponseCode(404)
                            .throttleBody(5, 1, TimeUnit.SECONDS) //一秒传递5个字节
                            .setBody("{\"error\": \"网络异常\"}")
                }
            }
        }

        server!!.setDispatcher(dispatcher)

        //默认返回http code是 200
//                val mockResponse = MockResponse()
//                        .addHeader("Content-Type", "application/json;charset=utf-8")
//                        .addHeader("Cache-Control", "no-cache")
//                        .setBody("{\"id\": 12456431, " +
//                                 " \"name\": \"唯鹿\"," +
//                                 " \"blog\": \"http://blog.csdn.net/qq_17766199\"}")
//
//                val mockResponse1 = MockResponse()
//                        .addHeader("Content-Type", "application/json;charset=utf-8")
//                        .setResponseCode(404)
//                        .throttleBody(5, 1, TimeUnit.SECONDS) //一秒传递5个字节
//                        .setBody("{\"error\": \"网络异常\"}")
//
//                server!!.enqueue(mockResponse) //成功响应
//                server!!.enqueue(mockResponse1)//失败响应

        //定义Http Client,并添加拦截器
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()

        //设置Http Client
        val retrofit = Retrofit.Builder()
                .baseUrl("http://" + server!!.hostName + ":" + server!!.port + "/")
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

        //验证我们的请求客户端是否按预期生成了请求
        val request = server!!.takeRequest()
        assertEquals("GET /users/weilu HTTP/1.1", request.requestLine)
        assertEquals("okhttp/3.9.1", request.getHeader("User-Agent"))

        // 关闭服务
        server!!.shutdown()
    }
}
