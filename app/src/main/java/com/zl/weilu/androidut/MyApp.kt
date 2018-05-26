package com.zl.weilu.androidut

import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.zl.weilu.androidut.dagger.base.component.DaggerAppComponent
import com.zl.weilu.androidut.net.GithubApi
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

/**
 * Created by weilu on 2017/12/3.
 */

class MyApp : DaggerApplication() {

    @Inject
    lateinit var githubApi: GithubApi

    override fun onCreate() {
        super.onCreate()

        instance = this

        val config = LogConfiguration.Builder()
                .logLevel(if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE)
                .build()
        XLog.init(config)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }

    companion object {

        lateinit var instance: MyApp
    }
}
