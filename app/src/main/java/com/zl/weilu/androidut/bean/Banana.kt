package com.zl.weilu.androidut.bean

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/11/18 11:54.
 */

object Banana : Fruit() {

    val bananaInfo: String
        get() = flavor() + COLOR

    val isLike: Boolean
        get() = true


    private fun flavor(): String {
        return "甜甜的"
    }

    private val COLOR = "黄色的"

    @JvmStatic
    fun color(): String{
        return COLOR
    }

}
