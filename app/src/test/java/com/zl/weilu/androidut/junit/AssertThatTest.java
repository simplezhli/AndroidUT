package com.zl.weilu.androidut.junit;

import org.junit.Rule;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by weilu on 2017/10/15.
 */

public class AssertThatTest {

    @Rule
    public MyRule rule = new MyRule();

    @Test
    public void testMobilePhone() throws Exception {
        assertThat("13588888888", new IsMobilePhoneMatcher());
    }

    @Test
    public void testAssertThat1() throws Exception {
        assertThat(6, is(6));
    }

    @Test
    public void testAssertThat2() throws Exception {
        assertThat(null, nullValue());
    }

    @Test
    public void testAssertThat3() throws Exception {
        assertThat("Hello UT", both(startsWith("Hello")).and(endsWith("UT")));
    }
}
