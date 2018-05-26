package com.zl.weilu.androidut.dagger.base.component

import com.zl.weilu.androidut.MyApp
import com.zl.weilu.androidut.dagger.base.module.AppModule
import com.zl.weilu.androidut.dagger.base.module.ClientModule
import com.zl.weilu.androidut.dagger.ui.BuildersModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * 作者：weilu on 2017/4/26 15:20
 */

@Singleton
@Component(modules = [
    (AppModule::class),
    (ClientModule::class),
    (BuildersModule::class),
    (AndroidSupportInjectionModule::class)])
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApp>()

}
