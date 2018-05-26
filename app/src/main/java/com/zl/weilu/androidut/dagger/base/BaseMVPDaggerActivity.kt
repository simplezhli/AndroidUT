package com.zl.weilu.androidut.dagger.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.zl.weilu.androidut.mvp.base.BaseMVPPresenter
import com.zl.weilu.androidut.mvp.base.MvpView
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * @author weilu
 * Created by weilu on 2018/2/5.
 */
abstract class BaseMVPDaggerActivity<V : MvpView, T : BaseMVPPresenter<V>> : DaggerAppCompatActivity(), MvpView {

    @Inject
    lateinit var mPresenter: T
    var mProgress: ProgressDialog? = null

    override val context: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.attachView(this as V)
        mProgress = ProgressDialog(this)
        mProgress!!.setMessage("加载中...")
    }

    override fun onDestroy() {
        mPresenter.detachView()
        super.onDestroy()
    }

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
