package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.content.Context
import androidx.core.view.WindowInsetsCompat

class screen_register : AppCompatActivity() {
    private lateinit var user:EditText
    private lateinit var pass:EditText
    private lateinit var confirm_pass:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontrol()
        chuyenmh()

    }
    fun setcontrol(){
        // anh xa
        user = findViewById(R.id.reg_user)
        pass = findViewById(R.id.reg_pass)
        confirm_pass= findViewById(R.id.txt_pass)

    }
    fun chuyenmh(){
        val btn_exit = findViewById<Button>(R.id.btn_back_login)
        btn_exit.setOnClickListener{
            val i =Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val btn_register_login = findViewById<Button>(R.id.btn_register_login)
        btn_register_login.setOnClickListener{
            // lưu mật khẩu mà người dùng đã nhập
            val tk_user: String = user.text.toString()
            if (pass.text.toString() == confirm_pass.text.toString()) {
                val tk_pass = pass.text.toString()

                // Lưu thông tin người dùng vào SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", tk_user)
                editor.putString("password", tk_pass)
                editor.apply()

                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_LONG).show()

                val i = Intent(this, screen_Login::class.java)
                i.putExtra("USERNAME", tk_user)
                i.putExtra("PASSWORD", tk_pass)
                startActivity(i)  // Chuyển đến màn hình đăng nhập
            } else {
                Toast.makeText(this, "Vui lòng nhập xác minh mật khẩu chính xác", Toast.LENGTH_LONG).show()
            }


        }
    }

}
