package com.zl.weilu.androidut.rxjava

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by weilu on 2018/1/6..
 */

class RxJavaTestSchedulerRule : TestRule {

    val testScheduler = TestScheduler()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { testScheduler }

                RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }

                RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

                RxAndroidPlugins.setMainThreadSchedulerHandler { testScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }
}
