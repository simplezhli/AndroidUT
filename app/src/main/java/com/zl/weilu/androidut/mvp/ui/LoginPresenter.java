package com.zl.weilu.androidut.mvp.ui;

import com.zl.weilu.androidut.mvp.base.BaseMVPPresenter;
import com.zl.weilu.androidut.bean.User;
import com.zl.weilu.androidut.net.GithubService;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by weilu on 2018/1/27.
 */

public class LoginPresenter extends BaseMVPPresenter<LoginMvpView>{

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    public void getIdentify() {
        // interval隔一秒发一次，到120结束
        Disposable mDisposable = Observable
                .interval(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .take(120)
                .subscribeWith(new DisposableObserver<Long>() {
                    @Override
                    public void onComplete() {
                        mMvpView.countdownComplete();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("倒计时出现错误！");
                    }

                    @Override
                    public void onNext(Long aLong) {
                        mMvpView.countdownNext(String.valueOf(Math.abs(aLong - 120)));
                    }
                });
        mCompositeDisposable.add(mDisposable);
    }

    public void login(String mobile, String code) {
        if(mobile.length() != 11){
            mMvpView.showToast("手机号码不正确");
            return;
        }
        if(code.length() != 6){
            mMvpView.showToast("验证码不正确");
            return;
        }

        GithubService.createGithubService()
                .getUser("simplezhli")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        if (isViewAttached()){
                            mMvpView.showProgress();
                        }

                    }
                })
                .doAfterTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()){
                            mMvpView.closeProgress();
                        }
                    }
                })
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mCompositeDisposable.add(d);
                    }

                    @Override
                    public void onNext(User user) {
                        mMvpView.showToast("登录成功");
                        mMvpView.loginSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMvpView.showToast("登录失败");
                    }

                    @Override
                    public void onComplete() {}
                });
    }

    @Override
    public void detachView(){
        super.detachView();
        mCompositeDisposable.clear();
    }

}
