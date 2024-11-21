package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_register : AppCompatActivity() {
    private lateinit var home_register:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        chuyenmh()
    }
    fun setcontrol(){
        home_register = findViewById(R.id.home_register)
    }
    fun chuyenmh(){
        val btn_exit = findViewById<Button>(R.id.btn_back_login)
        btn_exit.setOnClickListener{
            val i =Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val btn_register_login = findViewById<Button>(R.id.btn_register_login)
        btn_register_login.setOnClickListener{
            val i = Intent(this, screen_Login::class.java)
            startActivity(i)
        }
    }
}