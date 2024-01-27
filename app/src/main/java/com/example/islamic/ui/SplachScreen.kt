package com.example.islamic.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.islamic.R
import com.example.islamic.ui.home.MainActivity

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splach_screen)
        val handler = Handler()
        handler.postDelayed({
            val intent: Intent = Intent(this@SplachScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}