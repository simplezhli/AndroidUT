package com.zl.weilu.androidut.mockito;

import com.zl.weilu.androidut.bean.Person;

import org.junit.Rule;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

/**
 * @Description: MockitoRule方式Mock
 * @Author: weilu
 * @Time: 2017/11/4 14:43.
 */

public class MockitoRuleTest {

    @Mock 
    Person mPerson;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();
    
    
   
}
