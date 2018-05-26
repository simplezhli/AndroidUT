package com.zl.weilu.androidut.bean

/**
 * @Description:
 * @Author: weilu
 * @Time: 2017/11/4 10:45.
 */

open class Person {

    open var name: String? = null
    open var sex: Int = 0

    open val age: Int
        get() = 11

    open fun eat(food: String?): String? {
        return food
    }
}
