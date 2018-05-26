package com.zl.weilu.androidut.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期操作工具类.
 *
 * 作者：weilu on 2017/1/21 13:14
 */
object DateUtil {

    /**
     * 英文全称  如：2017-11-01 22:11:00
     */
    var FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss"

    /**
     * 掉此方法输入所要转换的时间输入例如（"2017-11-01 22:11:00"）返回时间戳
     *
     * @param time
     * @return 时间戳
     */
    @Throws(ParseException::class)
    fun dateToStamp(time: String): Long {
        val sdr = SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA)
        val date = sdr.parse(time)
        return date.time
    }

    /**
     * 将时间戳转换为时间
     */
    fun stampToDate(lt: Long): String {
        val res: String
        val simpleDateFormat = SimpleDateFormat(FORMAT_YMDHMS, Locale.CHINA)
        val date = Date(lt)
        res = simpleDateFormat.format(date)
        return res
    }

}