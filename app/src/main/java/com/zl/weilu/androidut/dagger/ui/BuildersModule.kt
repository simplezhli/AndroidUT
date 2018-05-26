package com.zl.weilu.androidut.dagger.ui

import com.zl.weilu.androidut.dagger.base.scope.ActivityScope

import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * 作者：weilu on 2018/2/5 09:46
 */

@Module
abstract class BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun loginDaggerActivityInjector(): LoginDaggerActivity

}
