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

class screen_Login : AppCompatActivity() {
    // khai báo
    private lateinit var user: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_login)


        // ánh xạ
        user = findViewById(R.id.txt_user)
        pass = findViewById(R.id.txt_pass)

        // Lấy mật khẩu đã lưu trong SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val savedUser = sharedPreferences.getString("username", null)
        val savedPassword = sharedPreferences.getString("password", null)

        // Lấy thông tin người dùng và mật khẩu từ Intent nếu có
        val duLieuUser = intent.getStringExtra("USERNAME")
        val duLieuPass = intent.getStringExtra("PASSWORD")

        val btn_exit = findViewById<Button>(R.id.btn_back_login)
        btn_exit.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        val btn_login = findViewById<Button>(R.id.btn_login_home)
        btn_login.setOnClickListener {
            val nhapUser = user.text.toString()
            val nhapPass = pass.text.toString()

            // Kiểm tra mật khẩu với thông tin trong SharedPreferences hoặc mật khẩu được gửi từ Intent
            if ((nhapUser == savedUser && nhapPass == savedPassword) ||
                (duLieuUser != null && duLieuPass != null && nhapUser == duLieuUser && nhapPass == duLieuPass)||(laydl_admin("admin","123"))) {
                // Nếu đăng nhập thành công, chuyển sang màn hình Home
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                swap_Activy(screen_home::class.java)  // Chuyển tới màn hình Home
            } else {
                Toast.makeText(this, "Thông tin đăng nhập không chính xác", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Hàm chuyển đổi màn hình
    fun swap_Activy(ac: Class<*>) {
        val i = Intent(this, ac)
        startActivity(i)
    }

    // Kiểm tra xem người dùng có nhập đúng dữ liệu admin hay không
    fun laydl_admin(user_a: String, pass_a: String): Boolean {
        val user_ad = intent.getStringExtra("user_admin")
        val pass_ad = intent.getStringExtra("pass_admin")
        return user_ad == user_a && pass_ad == pass_a
    }
}
