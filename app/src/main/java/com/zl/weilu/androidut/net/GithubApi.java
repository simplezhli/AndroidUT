package com.zl.weilu.androidut.net;

import com.zl.weilu.androidut.bean.User;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @Description: Github接口
 * @Author: weilu
 * @Time: 2017/12/22 0022 14:03.
 */
public interface GithubApi {

    String BASE_URL = "https://api.github.com/";

    @GET("users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
