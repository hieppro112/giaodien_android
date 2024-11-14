package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_notifycations : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_notifycations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val  btn_home = findViewById<Button>(R.id.btn_home)
        btn_home.setOnClickListener{
            swap_Activy(screen_home::class.java)
        }
        val  btn_profile = findViewById<Button>(R.id.btn_profile)
        btn_profile.setOnClickListener{
            swap_Activy(screen_profile::class.java)
        }
        val  btn_contact = findViewById<Button>(R.id.btn_contact)
        btn_contact.setOnClickListener{
            swap_Activy(screen_contact::class.java)
        }
        val  btn_statistics = findViewById<Button>(R.id.btn_statictisc)
        btn_statistics.setOnClickListener{
            swap_Activy(screen_statistics::class.java)
        }


    }
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }
    }
