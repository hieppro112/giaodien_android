package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class thanhtoan : AppCompatActivity() {
    lateinit var btn_thanhtoan:Button
    lateinit var sotien:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_thanhtoan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontrol()
        setEvent()
    }
    fun setcontrol(){
        btn_thanhtoan=findViewById(R.id.thanhtoan_done)
        sotien=findViewById(R.id.thanhtoan_tien)

    }
    fun setEvent(){
        btn_thanhtoan.setOnClickListener {
            finish()
        }
        val tien = intent.getStringExtra("tien")
        sotien.text="Thanh toan so tien $tien"


    }

}