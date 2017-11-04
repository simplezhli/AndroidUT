package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;

/**
 * @Description: 运行器Mock
 * @Author: weilu
 * @Time: 2017/11/4 0004 10:50.
 */

@RunWith(MockitoJUnitRunner.class) //<--使用MockitoJUnitRunner
public class MockitoJUnitRunnerTest {

    @Mock //<--使用@Mock注解
    Person mPerson;

    @Test
    public void testIsNotNull(){
        assertNotNull(mPerson);
    }

}
