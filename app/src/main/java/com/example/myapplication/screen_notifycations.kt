package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible

class screen_notifycations : AppCompatActivity() {
    private lateinit var btn_admin_tb:Button
    private lateinit var txt_tb:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_notifycations)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.screen_notyfication)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontrol()
        chuyen_mh()
        on_add_thongbao()
        set_add_thongbao()

    }

    fun show_dialog_notifycations(){
        val dialog_view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_notifycations,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialog_view)
        val dialog = builder.create()
        val btn_huy = dialog_view.findViewById<Button>(R.id.dialog_tb_huy)
        btn_huy.setOnClickListener{
            dialog.cancel()
        }
        dialog.show()
    }
    fun set_add_thongbao(){
        btn_admin_tb.setOnClickListener{
            show_dialog_notifycations()
        }
    }
    fun on_add_thongbao(){
        val add_notify  = getSharedPreferences("admin_value", MODE_PRIVATE)
        val dulieu =add_notify.getBoolean("admin",false)
//        Toast.makeText(this,dulieu.toString(),Toast.LENGTH_LONG).show()
        if (dulieu==false){
            btn_admin_tb.isInvisible=false
            btn_admin_tb.alpha=0.5f
            btn_admin_tb.isEnabled=false
        }
    }
    fun  chuyen_mh(){
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
    fun setcontrol(){
        btn_admin_tb = findViewById(R.id.btn_add_notify)
        txt_tb=findViewById(R.id.txt_tb_dialog)
    }


    fun hienthi_tb(){
        val dialog_view = LayoutInflater.from(this).inflate(R.layout.chitiet_tb,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialog_view)
        val dialog = builder.create()
        txt_tb.setOnClickListener {
            dialog.show()
        }
    }
}
