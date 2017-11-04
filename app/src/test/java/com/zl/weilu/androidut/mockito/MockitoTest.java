package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * @Description: 普通方法Mock
 * @Author: weilu
 * @Time: 2017/11/4 10:44.
 */

public class MockitoTest {

    @Test
    public void testIsNotNull(){
        Person mPerson = mock(Person.class); //<--使用mock方法

        assertNotNull(mPerson);
    }
}
