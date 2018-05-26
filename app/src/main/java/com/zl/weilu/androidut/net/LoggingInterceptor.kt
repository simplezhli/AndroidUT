package com.zl.weilu.androidut.net

import android.text.TextUtils
import com.elvishew.xlog.XLog
import okhttp3.FormBody
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.URLDecoder

/**
 * @author weilu
 * Created by weilu on 2016/9/1.
 * 日志信息采集类
 * 有一个地方需要注意一下，在调用了response.body().string()方法之后，
 * response中的流会被关闭，我们需要创建出一个新的response给应用层处理
 */
class LoggingInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.currentTimeMillis()
        val response = chain.proceed(chain.request())
        val endTime = System.currentTimeMillis()
        val duration = endTime - startTime
        val mediaType = response.body()!!.contentType()
        XLog.d("\n")
        XLog.d("----------Start----------------")
        XLog.d("| RequestUrl:" + request.url())
        XLog.d("| RequestHeaders:\n" + request.headers())

        val method = request.method()
        if ("POST" == method) {
            val sb = StringBuilder()
            if (request.body() is FormBody) {
                val body = request.body() as FormBody?
                for (i in 0 until body!!.size()) {
                    sb.append(body.encodedName(i) + ":" + (if (TextUtils.isEmpty(body.encodedValue(i)))
                        "参数为空"
                    else
                        URLDecoder.decode(body.encodedValue(i), "utf-8")) + ",")
                }
                if (sb.length != 0) {
                    sb.delete(sb.length - 1, sb.length)
                }
                XLog.d("| RequestParams:\n")
                try {
                    XLog.json("{" + sb.toString() + "}")
                } catch (e: Exception) {
                    XLog.d("{" + sb.toString() + "}")
                }

            }
        }
        XLog.d("| ResponseHeaders:\n" + response.headers())
        val content = response.body()!!.string()
        try {
            XLog.json(content)
        } catch (e: Exception) {
            XLog.d(content)
        }

        XLog.d("----------End:" + duration + "毫秒----------")
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build()
    }
}