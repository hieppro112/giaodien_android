package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class screen_register : AppCompatActivity() {
    private lateinit var user: EditText
    private lateinit var pass: EditText
    private lateinit var confirm_pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_register)
        setControl()
        setEvent()
    }

    private fun setControl() {
        user = findViewById(R.id.reg_user)
        pass = findViewById(R.id.reg_pass)
        confirm_pass = findViewById(R.id.txt_pass)
    }

    private fun setEvent() {
        val btnBack = findViewById<Button>(R.id.btn_back_login)
        btnBack.setOnClickListener {
            val intent = Intent(this, screen_Login::class.java)
            startActivity(intent)
        }

        val btnRegister = findViewById<Button>(R.id.btn_register_login)
        btnRegister.setOnClickListener {
            val tkUser = user.text.toString()
            val tkPass = pass.text.toString()
            val tkConfirm = confirm_pass.text.toString()

            if (tkUser.isEmpty() || tkPass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if (tkPass == tkConfirm) {
                val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("username", tkUser)
                editor.putString("password", tkPass)
                editor.apply()

                Toast.makeText(this, "Đăng ký thành công, bạn có thể đăng nhập ngay bay giờ ", Toast.LENGTH_LONG).show()

                val intent = Intent(this, screen_Login::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Xác nhận mật khẩu không chính xác", Toast.LENGTH_LONG).show()
            }
        }
    }
}
