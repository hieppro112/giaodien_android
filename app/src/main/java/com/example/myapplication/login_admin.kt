package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
        xuly_login_admin()
        chuyenmanghinh_admin()
        lay_admin()
    }

    fun setcontrol(){
        sw_admin_on = findViewById(R.id.sw_admin_off)
        login_admin = findViewById(R.id.login_admin)
    }
    fun xuly_login_admin(){
        Toast.makeText(this, "Admin is on", Toast.LENGTH_SHORT).show();
        login_admin.setOnClickListener{
            if (sw_admin_on.isChecked) {

                // gán dữ liệu
                val user_admin: String = "admin"
                val pass_admin: String = "123"
                val i = Intent(this, screen_Login::class.java)
                i.putExtra("user_admin",user_admin)
                i.putExtra("pass_admin",pass_admin)
                startActivity(i)
            }
            else {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            }

        }
    }
    fun chuyenmanghinh_admin(){
        sw_admin_on.setOnCheckedChangeListener { _, ischecked ->
            if (ischecked==false){
                val i = Intent(this,MainActivity::class.java)
                Toast.makeText(this, "Admin is off", Toast.LENGTH_SHORT).show();

                startActivity(i)
            }
            else{

            }
        }
    }

    fun lay_admin(){
        if (sw_admin_on.isChecked){
            val quyen_admin = getSharedPreferences("admin", MODE_PRIVATE)
            val editor = quyen_admin.edit()
            editor.putString("admin", "true")
            editor.apply()
        }
    }




}
