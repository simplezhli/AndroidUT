package com.zl.weilu.androidut.junit

import org.hamcrest.CoreMatchers.*
import org.hamcrest.core.Is.`is`
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

/**
 * Created by weilu on 2017/10/15.
 */

class AssertThatTest {

    @get:Rule
    var rule = MyRule()

    @Test
    @Throws(Exception::class)
    fun testMobilePhone() {
        assertThat("13588888888", IsMobilePhoneMatcher())
    }

    @Test
    @Throws(Exception::class)
    fun testAssertThat1() {
        assertThat(6, `is`(6))
    }

    @Test
    @Throws(Exception::class)
    fun testAssertThat2() {
        assertThat<Any>(null, nullValue())
    }

    @Test
    @Throws(Exception::class)
    fun testAssertThat3() {
        assertThat("Hello UT", both(startsWith("Hello")).and(endsWith("UT")))
    }
}
