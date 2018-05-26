package com.zl.weilu.androidut.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * @Description: 自定义服务
 * @Author: weilu
 * @Time: 2017/12/4 11:40.
 */
class MyService : Service() {

    private val TAG = "MyService"

    override fun onBind(intent: Intent): IBinder? {
        Log.d(TAG, "onBind")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.d(TAG, "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }
}
