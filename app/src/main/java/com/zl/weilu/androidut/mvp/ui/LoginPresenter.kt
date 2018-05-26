package com.zl.weilu.androidut.mvp.ui

import com.zl.weilu.androidut.bean.User
import com.zl.weilu.androidut.mvp.base.BaseMVPPresenter
import com.zl.weilu.androidut.net.GithubService
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by weilu on 2018/1/27.
 */

class LoginPresenter : BaseMVPPresenter<LoginMvpView>() {

    private val mCompositeDisposable = CompositeDisposable()

    fun getIdentify() {
        // interval隔一秒发一次，到120结束
        val mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(120)
                .subscribeWith(object : DisposableObserver<Long>() {
                    override fun onComplete() {
                        mMvpView.countdownComplete()
                    }

                    override fun onError(e: Throwable) {
                        mMvpView.showToast("倒计时出现错误！")
                    }

                    override fun onNext(aLong: Long) {
                        mMvpView.countdownNext(Math.abs(aLong - 120).toString())
                    }
                })
        mCompositeDisposable.add(mDisposable)
    }

    fun login(mobile: String, code: String) {
        if (mobile.length != 11) {
            mMvpView.showToast("手机号码不正确")
            return
        }
        if (code.length != 6) {
            mMvpView.showToast("验证码不正确")
            return
        }

        GithubService.createGithubService()
                .getUser("simplezhli")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    if (isViewAttached) {
                        mMvpView.showProgress()
                    }
                }
                .doAfterTerminate {
                    if (isViewAttached) {
                        mMvpView.closeProgress()
                    }
                }
                .subscribe(object : Observer<User> {
                    override fun onSubscribe(d: Disposable) {
                        mCompositeDisposable.add(d)
                    }

                    override fun onNext(user: User) {
                        mMvpView.showToast("登录成功")
                        mMvpView.loginSuccess()
                    }

                    override fun onError(e: Throwable) {
                        mMvpView.showToast("登录失败")
                    }

                    override fun onComplete() {}
                })
    }

    override fun detachView() {
        super.detachView()
        mCompositeDisposable.clear()
    }

}
