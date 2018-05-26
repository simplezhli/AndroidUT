package com.zl.weilu.androidut.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @Description: GithubService
 * @Author: weilu
 * @Time: 2017/12/22 0022 14:08.
 */
object GithubService {

    private val retrofit = Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    private val okHttpClient: OkHttpClient
        get() = OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor())
                .build()

    fun createGithubService(): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }
}
