package com.zl.weilu.androidut.mvp.ui;

import com.zl.weilu.androidut.mvp.base.MvpView;

/**
 * Created by weilu on 2018/1/27.
 */

public interface LoginMvpView extends MvpView{

    /**
     * 倒计时完成
     */
    void countdownComplete();

    /**
     * 倒计时中
     * @param time 剩余时间
     */
    void countdownNext(String time);

    /**
     * 登录成功
     */
    void loginSuccess();

}
