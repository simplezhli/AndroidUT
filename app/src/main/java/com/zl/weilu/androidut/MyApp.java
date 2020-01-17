package com.zl.weilu.androidut;

import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.LogLevel;
import com.elvishew.xlog.XLog;
import com.zl.weilu.androidut.dagger.base.component.DaggerAppComponent;
import com.zl.weilu.androidut.net.GithubApi;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

//import com.zl.weilu.androidut.dagger.base.component.DaggerAppComponent;

/**
 * Created by weilu on 2017/12/3.
 */

public class MyApp extends DaggerApplication {
    
    private static MyApp instance;

    @Inject
    GithubApi mGithubApi;
    
    @Override
    public void onCreate() {
        super.onCreate();

        if (instance == null){
            instance = this;
        }
        
        LogConfiguration config = new LogConfiguration.Builder()
                .logLevel(BuildConfig.DEBUG ? LogLevel.ALL : LogLevel.NONE)
                .build();
        XLog.init(config);
    }

    public static MyApp getInstance() {
        return instance;
    }

    public GithubApi getGithubApi(){
        return mGithubApi;
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.factory().create(this);
    }
}
