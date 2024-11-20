package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AlertDialog.Builder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_notifycations : AppCompatActivity() {
    private lateinit var btn_dialog:Button
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
//   btn_create_notifycations.setOnClickListener{
    set_add_thongbao()

    }
    fun set_add_thongbao(){
        btn_dialog.setOnClickListener{
            show_dialog_notifycations()
            Toast.makeText(this,"hello",Toast.LENGTH_LONG).show()

        }
    }
    fun setcontrol(){
        btn_dialog = findViewById(R.id.btn_dialog_tb)
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
    fun show_dialog_notifycations(){
        setcontrol()
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
}
