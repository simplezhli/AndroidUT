package com.zl.weilu.androidut.dagger.base.component;

import com.zl.weilu.androidut.MyApp;
import com.zl.weilu.androidut.dagger.base.module.AppModule;
import com.zl.weilu.androidut.dagger.base.module.ClientModule;
import com.zl.weilu.androidut.dagger.ui.BuildersModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * 作者：weilu on 2017/4/26 15:20
 */

@Singleton
@Component(modules = {
        AppModule.class,
        ClientModule.class,
        BuildersModule.class,
        AndroidSupportInjectionModule.class})
public interface AppComponent extends AndroidInjector<MyApp> {

    @Component.Factory
    abstract class Factory implements AndroidInjector.Factory<MyApp> {}

}
