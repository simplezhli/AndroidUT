package com.zl.weilu.androidut.net

import okhttp3.*
import java.io.IOException

/**
 * 网络请求的拦截器，用于Mock响应数据
 * 参考文章：
 * http://stackoverflow.com/questions/17544751/square-retrofit-server-mock-for-testing
 * https://github.com/square/okhttp/wiki/Interceptors
 */
class MockInterceptor(private val responeJsonPath: String) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val responseString = createResponseBody(chain)

        val response = Response.Builder()
                .code(200)
                .message(responseString!!)
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse("application/json"), responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        return response
    }

    /**
     * 读文件获取json字符串，生成ResponseBody
     *
     * @param chain
     * @return
     */
    private fun createResponseBody(chain: Interceptor.Chain): String? {

        var responseString: String? = null

        val uri = chain.request().url()
        val path = uri.url().path

        if (path.matches("^(/users/)+[^/]*+$".toRegex())) {//匹配/users/{username}
            responseString = getResponseString("users.json")
        }
        return responseString
    }

    private fun getResponseString(fileName: String): String {
        return FileUtil.readFile(responeJsonPath + fileName, "UTF-8")!!.toString()
    }

}
