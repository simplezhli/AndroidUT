package com.zl.weilu.androidut.junit

import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import java.util.regex.Pattern

/**
 * Created by weilu on 2017/10/15.
 */

class IsMobilePhoneMatcher : BaseMatcher<String>() {

    /**
     * 进行断言判定，返回true则断言成功，否则断言失败
     */

    override fun matches(item: Any?): Boolean {
        if (item == null) {
            return false
        }

        val pattern = Pattern.compile("(1|861)(3|5|7|8)\\d{9}$*")
        val matcher = pattern.matcher((item as String?)!!)

        return matcher.find()
    }

    /**
     * 给期待断言成功的对象增加描述
     */
    override fun describeTo(description: Description) {
        description.appendText("预计此字符串是手机号码！")
    }

    /**
     * 给断言失败的对象增加描述
     */
    override fun describeMismatch(item: Any, description: Description) {
        description.appendText(item.toString() + "不是手机号码！")
    }
}
