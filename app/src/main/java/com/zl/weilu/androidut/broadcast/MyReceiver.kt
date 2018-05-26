package com.zl.weilu.androidut.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.preference.PreferenceManager

/**
 * @Description: 自定义广播接收器
 * @Author: weilu
 * @Time: 2017/12/4 11:23.
 */
class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
        val name = intent.getStringExtra(NAME)
        editor.putString(NAME, name)
        editor.apply()
    }

    companion object {

        const val NAME = "name"
    }
}
