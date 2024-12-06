package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_chitiet_phong : AppCompatActivity() {
    lateinit var img_room_ext:ImageView
    lateinit var btn_chitietsp_cart:LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_chitiet_phong)

        setcontrol()
        setEvent()
    }
    fun setcontrol(){
        img_room_ext = findViewById(R.id.img_room_ext)
        btn_chitietsp_cart = findViewById(R.id.btn_chitietsp_cart)
    }
    fun setEvent(){
        img_room_ext.setOnClickListener {
            val  i = Intent(this,screen_home::class.java)
            startActivity(i)
        }
        btn_chitietsp_cart.setOnClickListener {
            val  i = Intent(this,screen_giohang::class.java)
            startActivity(i)
        }
    }
}