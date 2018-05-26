package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit

/**
 * @Description: 常用参数匹配器示例
 * @Author: weilu
 * @Time: 2017/11/4 18:32.
 */

class MockitoMatchersTest {

    @Mock
    lateinit var mPerson: Person

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    fun testPersonAny() {
        `when`(mPerson.eat(ArgumentMatchers.any(String::class.java))).thenReturn("米饭")
        //或
//        `when`(mPerson.eat(anyString())).thenReturn("米饭")

        //输出米饭
        println(mPerson.eat("面条"))

    }


    @Test
    fun testPersonContains() {

        `when`(mPerson.eat(ArgumentMatchers.contains("面"))).thenReturn("面条")
        //输出面条
        println(mPerson.eat("面"))

    }

    @Test
    fun testPersonArgThat() {

        //自定义输入字符长度为偶数时，输出面条。
        `when`(mPerson.eat(ArgumentMatchers.argThat({ argument ->
            argument!!.length % 2 == 0 }))).thenReturn("面条"
        )
        //输出面条
        println(mPerson.eat("1234"))

    }

}
