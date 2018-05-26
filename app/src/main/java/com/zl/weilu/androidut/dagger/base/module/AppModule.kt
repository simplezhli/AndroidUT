package com.zl.weilu.androidut.dagger.base.module

import android.content.Context
import com.zl.weilu.androidut.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author weilu
 * 作者：weilu on 2018/2/5 15:20
 */

@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(mApplication: MyApp): Context {
        return mApplication.applicationContext
    }
}
