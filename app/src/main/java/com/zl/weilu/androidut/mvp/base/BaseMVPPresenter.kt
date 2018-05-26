package com.zl.weilu.androidut.mvp.base

import java.lang.ref.Reference
import java.lang.ref.WeakReference

/**
 * Created by weilu on 2018/1/27.
 */

abstract class BaseMVPPresenter<T : MvpView> {

    /**
     * View接口类型的弱引用
     */
    private var mViewRef: Reference<T>? = null

    protected lateinit var mMvpView: T

    /**
     * 获取View
     * @return View
     */
    val view: T
        get() = mViewRef!!.get()!!

    /**
     * UI展示相关的操作需要判断一下 Activity 是否已经 finish.
     *
     *
     * todo : 只有当 isActivityAlive 返回true时才可以执行与Activity相关的操作,
     * 比如 弹出Dialog、Window、跳转Activity等操作.
     *
     * @return boolean
     */
    val isViewAttached: Boolean
        get() = mViewRef != null && mViewRef!!.get() != null

    /**
     * 建立关联
     */
    fun attachView(view: T) {
        mViewRef = WeakReference(view)
        if (isViewAttached) {
            mMvpView = view
        }
    }

    /**
     * 解除关联
     */
    open fun detachView() {
        if (mViewRef != null) {
            mViewRef!!.clear()
            mViewRef = null
        }
    }
}
