package com.zl.weilu.androidut.robolectric.shadow

import com.zl.weilu.androidut.bean.Person

import org.robolectric.annotation.Implementation
import org.robolectric.annotation.Implements

/**
 * [Person] 的影子类
 * @Description: 自定义Shadow
 * @Author: weilu
 * @Time: 2017/12/4 13:05.
 */
@Implements(Person::class)
class ShadowPerson {

    val name: String
        @Implementation
        get() = "AndroidUT"

    val sex: Int
        @Implementation
        get() = 0

    val age: Int
        @Implementation
        get() = 18
}
