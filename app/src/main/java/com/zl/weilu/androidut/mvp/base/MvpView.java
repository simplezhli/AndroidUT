package com.zl.weilu.androidut.mvp.base;

import android.content.Context;

/**
 * Created by weilu on 2018/1/27.
 */

public interface MvpView {

    /***
     * 获取Context
     * @return Context
     */
    Context getContext();

    /***
     * 显示Progress
     */
    void showProgress();

    /***
     * 关闭Progress
     */
    void closeProgress();

    /***
     * @param string 消息内容
     */
    void showToast(String string);
}
