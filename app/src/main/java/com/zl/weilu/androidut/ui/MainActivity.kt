package com.zl.weilu.androidut.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.zl.weilu.androidut.R
import com.zl.weilu.androidut.mvp.ui.LoginMVPActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lifecycleState: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleState = "onCreate"
    }

    fun jump(view: View) {

//        startActivity(Intent(this, LoginActivity::class.java))

          startActivity(Intent(this, LoginMVPActivity::class.java))

//          startActivity(Intent(this, LoginDaggerActivity::class.java))
    }

    fun showToast(view: View) {
        Toast.makeText(this, "Hello UT!", Toast.LENGTH_LONG).show()
    }

    fun showDialog(view: View) {
        val alertDialog = AlertDialog.Builder(this)
                .setMessage("Hello UT！")
                .setTitle("提示")
                .create()
        alertDialog.show()
    }

    fun inverse(view: View) {
        checkbox.isChecked = !checkbox.isChecked
    }

    override fun onStart() {
        super.onStart()
        lifecycleState = "onStart"
    }

    override fun onResume() {
        super.onResume()
        lifecycleState = "onResume"
    }

    override fun onPause() {
        super.onPause()
        lifecycleState = "onPause"
    }

    override fun onStop() {
        super.onStop()
        lifecycleState = "onStop"
    }

    override fun onRestart() {
        super.onRestart()
        lifecycleState = "onRestart"
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleState = "onDestroy"
    }
}
