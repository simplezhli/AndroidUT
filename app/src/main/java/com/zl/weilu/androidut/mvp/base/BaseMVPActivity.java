package com.zl.weilu.androidut.mvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by weilu on 2018/1/27.
 */

public abstract class BaseMVPActivity<V extends MvpView, T extends BaseMVPPresenter<V>> extends AppCompatActivity implements MvpView{

    /**
     * Presenter对象
     */
    protected T mPresenter;
    public ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V)this);
        mProgress = new ProgressDialog(this);
        mProgress.setMessage("加载中...");
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null){
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null){
            mPresenter = createPresenter();
        }
    }

    /**
     * 创建Presenter对象
     * @return Presenter对象
     */
    protected abstract T createPresenter();

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void showProgress() {
        if (mProgress != null && !mProgress.isShowing()){
            mProgress.show();
        }
    }

    @Override
    public void closeProgress() {
        if (mProgress != null && mProgress.isShowing()) {
            mProgress.dismiss();
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}
