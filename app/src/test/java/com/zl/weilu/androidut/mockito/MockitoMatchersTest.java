package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @Description: 常用参数匹配器示例
 * @Author: weilu
 * @Time: 2017/11/4 0004 10:51.
 */

public class MockitoMatchersTest {

    @Mock
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    
}
