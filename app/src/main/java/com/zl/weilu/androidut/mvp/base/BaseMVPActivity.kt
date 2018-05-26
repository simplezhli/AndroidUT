package com.zl.weilu.androidut.mvp.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * Created by weilu on 2018/1/27.
 */

abstract class BaseMVPActivity<V : MvpView, T : BaseMVPPresenter<V>> : AppCompatActivity(), MvpView {

    /**
     * Presenter对象
     */
    var mPresenter: T? = null
    var mProgress: ProgressDialog? = null

    override val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = createPresenter()
        mPresenter!!.attachView(this as V)
        mProgress = ProgressDialog(this)
        mProgress!!.setMessage("加载中...")
    }

    override fun onDestroy() {
        if (mPresenter != null) {
            mPresenter!!.detachView()
        }
        super.onDestroy()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (mPresenter == null) {
            mPresenter = createPresenter()
        }
    }

    /**
     * 创建Presenter对象
     * @return Presenter对象
     */
    protected abstract fun createPresenter(): T

    override fun showProgress() {
        if (mProgress != null && !mProgress!!.isShowing) {
            mProgress!!.show()
        }
    }

    override fun closeProgress() {
        if (mProgress != null && mProgress!!.isShowing) {
            mProgress!!.dismiss()
        }
    }

    override fun showToast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
    }
}
