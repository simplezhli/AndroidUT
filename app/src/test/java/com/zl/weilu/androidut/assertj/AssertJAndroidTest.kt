package com.zl.weilu.androidut.assertj

import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.ui.MainActivity
import org.assertj.android.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLog

/**
 * @Description:
 * @Author: weilu
 * @Time: 2018/5/15 0015 10:33.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class AssertJAndroidTest {

    private lateinit var mainActivity: MainActivity
    private lateinit var mJumpBtn: Button
    private lateinit var mRoot: LinearLayout
    private lateinit var checkBox: CheckBox

    @Before
    fun setUp() {
        //输出日志
        ShadowLog.stream = System.out
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        mJumpBtn = mainActivity.findViewById(R.id.button1)
        mRoot = mainActivity.findViewById(R.id.root)
        checkBox = mainActivity.findViewById(R.id.checkbox)
    }

    @Test
    fun testView() {
        // Button是否可见
        assertThat(mJumpBtn).isVisible
        // LinearLayout 方向，子View数量
        assertThat(mRoot)
                .isVertical
                .hasChildCount(4)
        // CheckBox是否未选中
        assertThat(checkBox).isNotChecked
    }
}
