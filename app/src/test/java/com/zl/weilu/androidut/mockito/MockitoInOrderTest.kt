package com.zl.weilu.androidut.mockito

import com.zl.weilu.androidut.bean.Person
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.junit.MockitoJUnit

/**
 * Created by weilu on 2017/11/5.
 */

class MockitoInOrderTest {

    @Mock
    lateinit var mPerson: Person

    @Mock
    lateinit var mPerson1: Person

    @get:Rule
    var mockitoRule = MockitoJUnit.rule()


    @Test
    fun testPersonInOrder() {

        mPerson.name = "小明"
        mPerson.sex = 1

        mPerson1.name = "小红"
        mPerson1.sex = 0

        val mInOrder = inOrder(mPerson, mPerson1)
        //执行顺序正确
        mInOrder.verify(mPerson).name = "小明"
        mInOrder.verify(mPerson).sex = 1

        //执行顺序错误
        // mInOrder.verify(mPerson1).sex = 0
        // mInOrder.verify(mPerson1).name = "小红"

    }
}
