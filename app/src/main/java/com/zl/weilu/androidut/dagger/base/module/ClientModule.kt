package com.zl.weilu.androidut.dagger.base.module


import com.zl.weilu.androidut.net.GithubApi
import com.zl.weilu.androidut.net.LoggingInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author weilu
 * 作者：weilu on 2018/2/5 15:22
 */

@Module
class ClientModule {

    @Singleton
    @Provides
    internal fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(builder: Retrofit.Builder, client: OkHttpClient): Retrofit {
        return builder
                .baseUrl(HttpUrl.parse(GithubApi.BASE_URL)!!)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun provideClient(okHttpClient: OkHttpClient.Builder, loggingInterceptor: LoggingInterceptor): OkHttpClient {
        val builder = okHttpClient.addInterceptor(loggingInterceptor)
        return builder.build()
    }

    /**
     * 打印信息的拦截器
     * @return 拦截器
     */
    @Singleton
    @Provides
    internal fun provideLoggingInterceptor(): LoggingInterceptor {
        return LoggingInterceptor()
    }

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }

    @Singleton
    @Provides
    internal fun provideClientBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }

}
