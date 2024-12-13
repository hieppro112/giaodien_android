package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class screen_chitiet_phong : AppCompatActivity() {
    lateinit var img_room_ext:ImageView
    lateinit var btn_chitietsp_cart:LinearLayout
    lateinit var img_chitiet:ImageView
    lateinit var maphong_chitiet:TextView
    lateinit var luotdat_chitiet:TextView
    lateinit var content_chittiet:TextView
    lateinit var giaphong_chitiet:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_chitiet_phong)

        setcontrol()
        setEvent()
        nhandl()
    }
    fun setcontrol(){
        img_room_ext = findViewById(R.id.img_room_ext)
        btn_chitietsp_cart = findViewById(R.id.btn_chitietsp_cart)
        img_chitiet=findViewById(R.id.img_chitiet)
        maphong_chitiet=findViewById(R.id.maphong_chitiet)
        luotdat_chitiet = findViewById(R.id.luotdat_chitiet)
        content_chittiet = findViewById(R.id.content_chittiet)
        giaphong_chitiet=findViewById(R.id.giaphong_chitiet)
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
    fun nhandl(){
        val img = intent.getStringExtra("img")
        val title = intent.getStringExtra("title")
        val luotdat = intent.getIntExtra("luotdat",0)
        val content = intent.getStringExtra("content")
        val gia = intent.getDoubleExtra("gia",0.0)

        maphong_chitiet.text=title.toString()
        luotdat_chitiet.text="Đã có hơn ${luotdat.toString()} lượt đặt"
        content_chittiet.text = content.toString()
        giaphong_chitiet.text ="giá: ${gia.toString()}/ngày"
        val link:String=img.toString()

        img_chitiet.setImageDrawable(null)

        Glide.with(this).load(link).placeholder(R.drawable.img_1).error(R.drawable.img)
            .override(500, 400).into(img_chitiet)

    }
}