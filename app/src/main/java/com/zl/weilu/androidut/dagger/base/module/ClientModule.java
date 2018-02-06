package com.zl.weilu.androidut.dagger.base.module;


import com.zl.weilu.androidut.net.GithubApi;
import com.zl.weilu.androidut.net.LoggingInterceptor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author weilu
 * 作者：weilu on 2018/2/5 15:22
 */

@Module
public class ClientModule {

    @Singleton
    @Provides
    GithubApi provideGithubApi(Retrofit retrofit){
        return retrofit.create(GithubApi.class);
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return builder
                .baseUrl(HttpUrl.parse(GithubApi.BASE_URL))
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder okHttpClient, LoggingInterceptor loggingInterceptor) {
        OkHttpClient.Builder builder = okHttpClient.addInterceptor(loggingInterceptor);
        return builder.build();
    }

    /**
     * 打印信息的拦截器
     * @return 拦截器
     */
    @Singleton
    @Provides
    LoggingInterceptor provideLoggingInterceptor() {
        return new LoggingInterceptor(); 
    }

    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

}
