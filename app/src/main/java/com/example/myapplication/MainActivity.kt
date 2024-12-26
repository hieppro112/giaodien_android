package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var home_login:Button
    private lateinit var btn_register:Button
    private lateinit var sw_admin:Switch
    private var confirm:Boolean=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontent()
        onResume()
        chuyenmh()
        getbool()
        chuyen_admin()

    }
    fun getbool(){
        on_add_notifycations(confirm)
        home_login.setOnClickListener {
            confirm=false
            on_add_notifycations(confirm)
            val i = Intent(this, screen_Login::class.java)
            startActivity(i)
        }

    }
    fun setcontent(){
        home_login = findViewById(R.id.home_login)
        btn_register = findViewById(R.id.home_register)
        sw_admin = findViewById(R.id.sw_admin)

        val quyen_admin = getSharedPreferences("admin", MODE_PRIVATE)
        val editor = quyen_admin.edit()
        editor.putString("admin", "false")
        editor.apply()
    }
    override fun onResume() {
        super.onResume()
        confirm = true
    }
    fun xuly_radio(){
        val rd_admin = findViewById<Switch>(R.id.sw_admin)
    }
    fun chuyenmh(){

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
    fun chuyen_admin(){
        sw_admin.setOnCheckedChangeListener { _, ischecked ->
            if (ischecked == true){
                val i = Intent(this, login_admin::class.java)
                startActivity(i)
            }
            else{
                val i = Intent(this,MainActivity::class.java)
                startActivity(i)
            }
        }
    }

    fun on_add_notifycations(con:Boolean){
        val luu = getSharedPreferences("admin_value", MODE_PRIVATE)
        val edit = luu.edit()
        edit.putBoolean("admin",con)
        edit.apply()
    }
}