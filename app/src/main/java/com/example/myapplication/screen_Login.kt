package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class screen_Login : AppCompatActivity() {
    // Khai báo
    private lateinit var user: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen_login)

        // Ánh xạ
        user = findViewById(R.id.txt_user)
        pass = findViewById(R.id.txt_pass)

        // Lấy mật khẩu đã lưu trong SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedUser = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        // Nút quay lại
        val btnBack = findViewById<Button>(R.id.btn_back_login)
        btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Nút đăng nhập
        val btnLogin = findViewById<Button>(R.id.btn_login_home)
        btnLogin.setOnClickListener {
            val nhapUser = user.text.toString()
            val nhapPass = pass.text.toString()

            val lay_admin = getSharedPreferences("admin", MODE_PRIVATE)
            val admin = lay_admin.getString("admin","false")

            // Kiểm tra thông tin đăng nhập
            if (kiemTraAdmin(nhapUser, nhapPass) || kiemTraUserLuu(savedUser, savedPassword, nhapUser, nhapPass)) {
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                if (admin!="true") {
                    chuyenManHinh(screen_home::class.java) // Chuyển tới màn hình chính
                }
                else{
                    chuyenManHinh(screen_notifycations::class.java)
                }
            } else {
                Toast.makeText(this, "Thông tin đăng nhập không chính xác", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm kiểm tra thông tin admin
    private fun kiemTraAdmin(nhapUser: String, nhapPass: String): Boolean {
        val adminUser = "admin"
        val adminPass = "123"
        return nhapUser == adminUser && nhapPass == adminPass
    }

    // Hàm kiểm tra thông tin người dùng đã lưu
    private fun kiemTraUserLuu(savedUser: String?, savedPassword: String?, nhapUser: String, nhapPass: String): Boolean {
        return savedUser != null && savedPassword != null && nhapUser == savedUser && nhapPass == savedPassword
    }

    // Hàm chuyển đổi màn hình
    private fun chuyenManHinh(manHinh: Class<*>) {
        val intent = Intent(this, manHinh)
        startActivity(intent)
    }

    fun login_admin(){
        val lay_admin = getSharedPreferences("admin", MODE_PRIVATE)
        val admin = lay_admin.getString("admin","false")
        if (admin == "true"){
            val i = Intent(this, screen_notifycations::class.java)
            startActivity(i)
        }
    }
}
