package com.zl.weilu.androidut.net;

import com.elvishew.xlog.XLog;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author weilu
 * Created by weilu on 2016/9/1.
 * 日志信息采集类
 * 有一个地方需要注意一下，在调用了response.body().string()方法之后，
 * response中的流会被关闭，我们需要创建出一个新的response给应用层处理
 *
 */
public class LoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        XLog.d(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
        long t1 = System.nanoTime();
        Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        XLog.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        try {
            XLog.json(content);
        } catch (Exception e) {
            XLog.d(content);
        }
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    }
}