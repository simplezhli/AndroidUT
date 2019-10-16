package com.zl.weilu.androidut.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 网络请求的拦截器，用于Mock响应数据
 * 参考文章：
 * http://stackoverflow.com/questions/17544751/square-retrofit-server-mock-for-testing
 * https://github.com/square/okhttp/wiki/Interceptors
 */
public class MockInterceptor implements Interceptor {

    private final String responeJsonPath;

    public MockInterceptor(String responeJsonPath) {
        this.responeJsonPath = responeJsonPath;
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {

        String responseString = createResponseBody(chain);

        return new Response.Builder()
                .code(200)
                .message(responseString)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                .addHeader("content-type", "application/json")
                .build();
    }

    /**
     * 读文件获取json字符串，生成ResponseBody
     *
     * @param chain
     * @return
     */
    private String createResponseBody(Chain chain) {

        String responseString = null;

        HttpUrl uri = chain.request().url();
        String path = uri.url().getPath();

        if (path.matches("^(/users/)+[^/]*+$")) {//匹配/users/{username}
            responseString = getResponseString("users.json");
        }
        return responseString;
    }

    private String getResponseString(String fileName) {
        return FileUtil.readFile(responeJsonPath + fileName, "UTF-8").toString();
    }

}
