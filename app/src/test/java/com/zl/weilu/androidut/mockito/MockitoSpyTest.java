package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Spy;
import org.mockito.junit.*;

import static org.junit.Assert.assertNotNull;

/**
 * @Description: Spy示例
 * @Author: weilu
 * @Time: 2017/11/4 19:12.
 */

public class MockitoSpyTest {

    @Spy
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

    @Test
    public void testPersonSpy(){
        //输出11
        System.out.print(mPerson.getAge());
    }

}
