package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.*;

/**
 * @Description: 常用验证方法示例
 * @Author: weilu
 * @Time: 2017/11/4 11:51.
 */

public class MockitoVerifyTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testPersonVerifyAfter(){
        when(mPerson.getAge()).thenReturn(18);
        //延时1s验证
        System.out.println(mPerson.getAge());
        System.out.println(System.currentTimeMillis());
        verify(mPerson, after(1000)).getAge();
        System.out.println(System.currentTimeMillis());
        // 抛出异常
//        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtLeast(){
        mPerson.getAge();
        mPerson.getAge();
        //至少验证2次
        verify(mPerson, atLeast(2)).getAge();
    }

    @Test
    public void testPersonVerifyAtMost(){
        mPerson.getAge();
        //至多验证2次
        verify(mPerson, atMost(2)).getAge();
    }

    @Test
    public void testPersonVerifyTimes(){
        mPerson.getAge();
        mPerson.getAge();
        //验证方法在100ms超时前调用2次
        verify(mPerson, timeout(100).times(2)).getAge();
        reset(mPerson);
    }

}
