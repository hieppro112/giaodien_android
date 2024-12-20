package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class screen_forgot : AppCompatActivity() {
    private lateinit var edtUsername: EditText
    private lateinit var btnSendPassword: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_forgot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtUsername = findViewById(R.id.edt_forgot_user)
        btnSendPassword = findViewById(R.id.btn_forgot_send)

        btnSendPassword.setOnClickListener {
            val username = edtUsername.text.toString()
            resetPassword(username)
        }

        val btn_forgot_exit = findViewById<Button>(R.id.btn_forgot_Exit)
        btn_forgot_exit.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }

    // Hàm tạo mật khẩu ngẫu nhiên
    private fun taoPassNgauNhien(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        val random = Random()
        val password = StringBuilder()

        for (i in 0 until length) {
            val randomChar = charset[random.nextInt(charset.length)]
            password.append(randomChar)
        }

        return password.toString()
    }

    // Hàm gửi mật khẩu mới
    private fun resetPassword(username: String) {
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedUsername = sharedPreferences.getString("username", null)

        if (savedUsername == username) {
            // Tạo mật khẩu mới ngẫu nhiên
            val newPassword = taoPassNgauNhien(3)  // Mật khẩu mới dài 3 ký tự

            // Lưu mật khẩu mới vào SharedPreferences
            val editor = sharedPreferences.edit()
            editor.putString("password", newPassword)
            editor.apply()

            // Hiển thị mật khẩu mới cho người dùng
            Toast.makeText(this, "Mật khẩu mới của bạn là: $newPassword", Toast.LENGTH_LONG).show()

            // Quay lại màn hình đăng nhập sau khi đổi mật khẩu
            val i = Intent(this, screen_Login::class.java)
            i.putExtra("USERNAME", username)
            i.putExtra("PASSWORD", newPassword)
            startActivity(i)

        } else {
            // Nếu tên người dùng không đúng
            Toast.makeText(this, "Tên người dùng không tồn tại", Toast.LENGTH_LONG).show()
        }
    }
}
