package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class chitiet_TB : AppCompatActivity() {
    lateinit var title:EditText
    lateinit var content:EditText
    lateinit var btn_close:Button
    lateinit var btn_delete: Button
    lateinit var chitiet_tb_done:Button
    private var pos:Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.chitiet_tb)
        super.onCreate(savedInstanceState)
        pos=-2
        setcontrol()
        nhandl()
        setEvent()
    }

    fun setcontrol(){
        title = findViewById(R.id.chitiet_tb_title)
        content = findViewById(R.id.chitiet_tb_content)
        btn_close = findViewById(R.id.btn_close)
        btn_delete = findViewById(R.id.chitiet_tb_delete)
        chitiet_tb_done=findViewById(R.id.chitiet_tb_done)
    }
    fun nhandl(){
        val dltitle = intent.getStringExtra("title")
        val dlcontent = intent.getStringExtra("content")
        pos = intent.getIntExtra("pos",-1)


        title.setText(dltitle)
        content.setText(dlcontent)

    }
    fun setEvent(){
        btn_close.setOnClickListener {
            finish()
        }
        btn_delete.setOnClickListener {
            val i = Intent(this,screen_notifycations::class.java)
            i.putExtra("delete",pos)
            startActivity(i)
        }
        chitiet_tb_done.setOnClickListener {
            val i = Intent(this,screen_notifycations::class.java)
            i.putExtra("title",title.text.toString())
            i.putExtra("content",content.text.toString())
            i.putExtra("vitri",pos)
            startActivity(i)
        }
    }
}