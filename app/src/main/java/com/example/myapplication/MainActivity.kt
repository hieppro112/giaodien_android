package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        val Btn_login = findViewById<Button>(R.id.home_login);
        Btn_login.setOnClickListener{
//            Toast.makeText(this, "hello",Toast.LENGTH_LONG).show()
            val i = Intent(this ,screen_Login::class.java)
            startActivity(i)
        }
        val btn_register = findViewById<Button>(R.id.home_register);
        btn_register.setOnClickListener{
            val i = Intent(this, screen_register::class.java)
            startActivity(i)
        }
        val txt_forgot = findViewById<TextView>(R.id.txt_forgot)
        txt_forgot.setOnClickListener{
            val i = Intent(this, screen_forgot::class.java)
            startActivity(i)

        }
    }

}