package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class screen_profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        val txt_logout = findViewById<TextView>(R.id.txt_logout)
        txt_logout.setOnClickListener{
//            val i = Intent(this, MainActivity::class.java)
//            startActivity(i)
            swap_Activy(MainActivity::class.java)
        }
        val btn_home = findViewById<Button>(R.id.btn_home)
        btn_home.setOnClickListener{
            swap_Activy(screen_home::class.java)
        }
        val btn_staticsis = findViewById<Button>(R.id.btn_statictisc)
        btn_staticsis.setOnClickListener{
            swap_Activy(screen_statistics::class.java)
        }
        val btn_contact = findViewById<Button>(R.id.btn_call)
        btn_contact.setOnClickListener {
            swap_Activy(screen_contact::class.java)
        }


    }
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }
}