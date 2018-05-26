package com.zl.weilu.androidut.mvp.ui

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.mvp.base.BaseMVPActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginMVPActivity : BaseMVPActivity<LoginMvpView, LoginPresenter>(), LoginMvpView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tv_login.setOnClickListener(this)
        tv_send_identify.setOnClickListener(this)
    }

    override fun createPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun countdownComplete() {
        tv_send_identify.setText(R.string.login_send_identify)
        tv_send_identify.isEnabled = true
    }

    override fun countdownNext(time: String) {
        tv_send_identify.text = TextUtils.concat(time, "秒后重试")
    }

    override fun loginSuccess() {
        showToast("登录成功")
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tv_send_identify -> {
                tv_send_identify.isEnabled = false
                mPresenter!!.getIdentify()
            }
            R.id.tv_login -> mPresenter!!.login(et_mobile.text.toString().trim { it <= ' ' },
                    et_identify.text.toString().trim { it <= ' ' })
            else -> {
            }
        }
    }
}
