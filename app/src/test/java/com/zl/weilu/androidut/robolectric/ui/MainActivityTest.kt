package com.zl.weilu.androidut.robolectric.ui

import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.zl.weilu.androidut.BuildConfig
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.mvp.ui.LoginMVPActivity
import com.zl.weilu.androidut.ui.MainActivity
import com.zl.weilu.androidut.ui.fragment.SampleFragment
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowAlertDialog
import org.robolectric.shadows.ShadowLog
import org.robolectric.shadows.ShadowToast
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil

/**
 * @Description: 测试MainActivity
 * @Author: weilu
 * @Time: 2017/12/3 12:20.
 */
@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, sdk = [23])
class MainActivityTest {

    private val TAG = "test"

    private lateinit var mainActivity: MainActivity
    private lateinit var mJumpBtn: Button
    private lateinit var mToastBtn: Button
    private lateinit var mDialogBtn: Button
    private lateinit var mInverseBtn: Button
    private lateinit var checkBox: CheckBox

    @Before
    fun setUp() {
        //输出日志
        ShadowLog.stream = System.out
        // 默认会调用Activity的生命周期: onCreate->onStart->onResume
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
        mJumpBtn = mainActivity.findViewById(R.id.button1) as Button
        mToastBtn = mainActivity.findViewById(R.id.button2) as Button
        mDialogBtn = mainActivity.findViewById(R.id.button3) as Button
        mInverseBtn = mainActivity.findViewById(R.id.button4) as Button
        checkBox = mainActivity.findViewById(R.id.checkbox) as CheckBox
    }

    /**
     * 创建Activity测试
     */
    @Test
    fun testMainActivity() {

        assertNotNull(mainActivity)
        Log.d(TAG, "测试Log输出")

    }


    /**
     * 验证点击事件是否触发了页面跳转，验证目标页面是否预期页面
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testJump() {

        Assert.assertEquals(mJumpBtn.text.toString(), "Activity跳转")

        // 触发按钮点击
        mJumpBtn.performClick()

        // 获取对应的Shadow类
        val shadowActivity = Shadows.shadowOf(mainActivity)
        // 借助Shadow类获取启动下一Activity的Intent
        val nextIntent = shadowActivity.nextStartedActivity
        // 校验Intent的正确性
        assertEquals(nextIntent.component.className, LoginMVPActivity::class.java.getName())
    }

    /**
     * 验证Toast是否正确弹出
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testToast() {
        var toast = ShadowToast.getLatestToast()
        // 判断Toast尚未弹出
        assertNull(toast)

        mToastBtn.performClick()
        toast = ShadowToast.getLatestToast()
        // 判断Toast已经弹出
        assertNotNull(toast)
        // 获取Shadow类进行验证
        val shadowToast = Shadows.shadowOf(toast)
        assertEquals(Toast.LENGTH_LONG.toLong(), shadowToast.duration.toLong())
        assertEquals("Hello UT!", ShadowToast.getTextOfLatestToast())
    }

    /**
     * 验证Dialog是否正确弹出
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testDialog() {
        var dialog = ShadowAlertDialog.getLatestAlertDialog()
        // 判断Dialog尚未弹出
        assertNull(dialog)

        mDialogBtn.performClick()
        dialog = ShadowAlertDialog.getLatestAlertDialog()
        // 判断Dialog已经弹出
        assertNotNull(dialog)
        // 获取Shadow类进行验证
        val shadowDialog = Shadows.shadowOf(dialog)
        assertEquals("Hello UT！", shadowDialog.message)
    }

    /**
     * 验证UI组件状态
     *
     * @throws Exception
     */
    @Test
    @Throws(Exception::class)
    fun testCheckBoxState() {

        // 验证CheckBox初始状态
        assertFalse(checkBox.isChecked)

        // 点击按钮反转CheckBox状态
        mInverseBtn.performClick()
        // 验证状态是否正确
        assertTrue(checkBox.isChecked)

        // 点击按钮反转CheckBox状态
        mInverseBtn.performClick()
        // 验证状态是否正确
        assertFalse(checkBox.isChecked)
    }

    /**
     * 验证Fragment
     */
    @Test
    fun testFragment() {
        val sampleFragment = SampleFragment()
        //添加Fragment到Activity中，会触发Fragment的onCreateView()
        SupportFragmentTestUtil.startFragment(sampleFragment)
        assertNotNull(sampleFragment.view)
    }

    /**
     * 资源文件访问测试
     */
    @Test
    fun testResources() {
        val application = RuntimeEnvironment.application
        val appName = application.getString(R.string.app_name)
        assertEquals("AndroidUT", appName)
    }

    @Test
    @Throws(Exception::class)
    fun testLifecycle() {
        // 创建Activity控制器
        val controller = Robolectric.buildActivity<MainActivity>(MainActivity::class.java)
        val activity = controller.get()
        assertNull(activity.lifecycleState)

        // 调用Activity的performCreate方法
        controller.create()
        assertEquals("onCreate", activity.lifecycleState)

        // 调用Activity的performStart方法
        controller.start()
        assertEquals("onStart", activity.lifecycleState)

        // 调用Activity的performResume方法
        controller.resume()
        assertEquals("onResume", activity.lifecycleState)

        // 调用Activity的performPause方法
        controller.pause()
        assertEquals("onPause", activity.lifecycleState)

        // 调用Activity的performStop方法
        controller.stop()
        assertEquals("onStop", activity.lifecycleState)

        // 调用Activity的performRestart方法
        controller.restart()
        // 注意此处应该是onStart，因为performRestart不仅会调用restart，还会调用onStart
        assertEquals("onStart", activity.lifecycleState)

        // 调用Activity的performDestroy方法
        controller.destroy()
        assertEquals("onDestroy", activity.lifecycleState)
    }

}