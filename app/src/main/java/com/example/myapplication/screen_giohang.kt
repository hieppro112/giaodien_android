package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_giohang : AppCompatActivity() {
    lateinit var btnExit:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_giohang)
        setcontrol()
        setEvent()
    }
    fun setcontrol(){
        btnExit = findViewById(R.id.btnExit)
    }
    fun setEvent(){
        btnExit.setOnClickListener {
            val  i = Intent(this,screen_chitiet_phong::class.java)
            startActivity(i)
        }
    }
}