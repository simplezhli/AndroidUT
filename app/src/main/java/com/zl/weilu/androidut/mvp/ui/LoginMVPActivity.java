package com.zl.weilu.androidut.mvp.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zl.weilu.androidut.R;
import com.zl.weilu.androidut.mvp.base.BaseMVPActivity;

public class LoginMVPActivity extends BaseMVPActivity<LoginMvpView, LoginPresenter> implements LoginMvpView, View.OnClickListener{

    private TextView mTvSendIdentify;
    private EditText mEtMobile;
    private EditText mEtIdentify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtMobile = (EditText) this.findViewById(R.id.et_mobile);
        mEtIdentify = (EditText) this.findViewById(R.id.et_identify);
        mTvSendIdentify = (TextView) this.findViewById(R.id.tv_send_identify);

        this.findViewById(R.id.tv_login).setOnClickListener(this);
        mTvSendIdentify.setOnClickListener(this);
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void countdownComplete() {
        mTvSendIdentify.setText(R.string.login_send_identify);
        mTvSendIdentify.setEnabled(true);
    }

    @Override
    public void countdownNext(String time) {
        mTvSendIdentify.setText(TextUtils.concat(time, "秒后重试"));
    }

    @Override
    public void loginSuccess() {
        showToast("登录成功");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_send_identify:
                mTvSendIdentify.setEnabled(false);
                mPresenter.getIdentify();
                break;
            case R.id.tv_login:
                mPresenter.login(mEtMobile.getText().toString().trim(),
                        mEtIdentify.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
