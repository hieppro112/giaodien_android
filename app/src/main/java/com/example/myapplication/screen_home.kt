package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btn_profile = findViewById<Button>(R.id.btn_profile)
        btn_profile.setOnClickListener {
            val i = Intent(this, screen_profile::class.java)
            startActivity(i)
        }
        val  btn_call = findViewById<Button>(R.id.btn_contact)
        btn_call.setOnClickListener{
            swap_Activy(screen_contact::class.java)
        }
        val btn_statictis = findViewById<Button>(R.id.btn_statictisc)
        btn_statictis.setOnClickListener{
            swap_Activy(screen_statistics::class.java)
        }

        val btn_notifycations = findViewById<Button>(R.id.btn_notifycations)
        btn_notifycations.setOnClickListener{
            swap_Activy(screen_notifycations::class.java)
        }

        val spinnerT = findViewById<Spinner>(R.id.spinner1)
        setSpinner(spinnerT)

    }


    private fun setSpinner(spinnerT: Spinner){
        val list = resources.getStringArray(R.array.spinner_tang);
        val hienthi = ArrayAdapter(this, android.R.layout.simple_spinner_item,list)
        spinnerT.adapter = hienthi
        spinnerT.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@screen_home, "ban da chonj: "+ list[p2],Toast.LENGTH_LONG).show();
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }
}