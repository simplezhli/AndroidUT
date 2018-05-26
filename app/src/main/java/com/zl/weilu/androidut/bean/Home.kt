package com.zl.weilu.androidut.bean

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/2/11 0011 15:00.
 */

class Home(private val mPerson: Person) {

    val master: String?
        get() = mPerson.name
}
