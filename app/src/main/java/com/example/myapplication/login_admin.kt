package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class login_admin : AppCompatActivity() {
    private  lateinit var  sw_admin_on : Switch
    private  lateinit var login_admin :Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_admin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontrol()
        sw_admin_on.isChecked = true;
        xuly_swadmin()
        xuly_login_admin()

    }
    fun setcontrol(){
        sw_admin_on = findViewById(R.id.sw_admin_off)
        login_admin = findViewById(R.id.login_admin)
    }
    fun xuly_login_admin(){
        login_admin.setOnClickListener{
            val i = Intent(this, screen_Login::class.java)
            startActivity(i)
        }
    }
    fun xuly_swadmin(){
        sw_admin_on.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked == true) {
                Toast.makeText(this,isChecked.toString(),Toast.LENGTH_LONG).show();

            }
            else{
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }
        }
    }
}