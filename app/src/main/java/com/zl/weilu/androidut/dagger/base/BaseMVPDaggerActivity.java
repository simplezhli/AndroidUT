package com.zl.weilu.androidut.dagger.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.zl.weilu.androidut.mvp.base.BaseMVPPresenter;
import com.zl.weilu.androidut.mvp.base.MvpView;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

/**
 * @author weilu
 * Created by weilu on 2018/2/5.
 */
public abstract class BaseMVPDaggerActivity<V extends MvpView, T extends BaseMVPPresenter<V>> extends DaggerAppCompatActivity implements MvpView {

    @Inject
    protected T mPresenter;
    public ProgressDialog mProgress;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
