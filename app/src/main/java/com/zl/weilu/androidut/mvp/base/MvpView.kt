package com.zl.weilu.androidut.mvp.base

import android.content.Context

/**
 * Created by weilu on 2018/1/27.
 */

interface MvpView {

    /***
     * 获取Context
     * @return Context
     */
    val context: Context

    /***
     * 显示Progress
     */
    fun showProgress()

    /***
     * 关闭Progress
     */
    fun closeProgress()

    /***
     * @param string 消息内容
     */
    fun showToast(string: String)
}
