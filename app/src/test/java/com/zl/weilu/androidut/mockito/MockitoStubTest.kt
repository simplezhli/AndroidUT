package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit

/**
 * @Description: 常用打桩方法示例
 * @Author: weilu
 * @Time: 2017/11/4 11:11.
 */

class MockitoStubTest {

    @Mock
    lateinit var mPerson: Person

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()

    @Test
    fun testPersonReturn() {
        `when`(mPerson.name).thenReturn("小明")
        `when`(mPerson.sex).thenThrow(NullPointerException("滑稽：性别不明"))

        // 输出"小明"
        println(mPerson.name)

        doReturn("小小").`when`<Person>(mPerson).name
        // 输出"小小"
        println(mPerson.name)

        // 抛出异常
//          System.out.println(mPerson.sex)

    }

    @Test
    fun testPersonRealMethod() {
        doCallRealMethod().`when`<Person>(mPerson).age
        //调用Person的真实getAge()方法
        println(mPerson.age)
    }

    @Test
    fun testPersonNothing() {
        doNothing().doThrow(RuntimeException()).`when`<Person>(mPerson).sex = 1
        //第一次什么都不做
        mPerson.sex = 1
        //异常抛出在下面这行
        //mPerson.sex = 1
    }

    @Test
    fun testPersonAnswer() {

        `when`(mPerson.eat(anyString())).thenAnswer { invocation ->
            val args = invocation.arguments
            args[0].toString() + "真好吃"
        }
        //输出：饺子真好吃
        println(mPerson.eat("饺子"))
    }
}
