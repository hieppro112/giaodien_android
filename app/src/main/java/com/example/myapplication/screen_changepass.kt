package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class screen_changepass : AppCompatActivity() {

    // khai báo
    private lateinit var passCu: EditText
    private lateinit var passMoi: EditText
    private lateinit var nhapLaiPassMoi: EditText
    private lateinit var btnDoi: Button

    // Khai báo SharedPreferences để lưu trữ thông tin người dùng
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_changepass)

        // Ánh xạ
        passCu = findViewById(R.id.edtNhapMKC)
        passMoi = findViewById(R.id.edtNhapMKM)
        nhapLaiPassMoi = findViewById(R.id.edtXacNhan)
        btnDoi = findViewById(R.id.btnThayDoi)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Các nút điều hướng
        val btn_home = findViewById<Button>(R.id.btn_home)
        btn_home.setOnClickListener {
            swap_Activy(screen_home::class.java)
        }

        val btn_profile = findViewById<Button>(R.id.btn_profile)
        btn_profile.setOnClickListener {
            swap_Activy(screen_profile::class.java)
        }

        val btn_contact = findViewById<Button>(R.id.btn_contact)
        btn_contact.setOnClickListener {
            swap_Activy(screen_contact::class.java)
        }

        val btn_statistics = findViewById<Button>(R.id.btn_statictisc)
        btn_statistics.setOnClickListener {
            swap_Activy(screen_statistics::class.java)
        }

        val btn_notifycation = findViewById<ImageView>(R.id.btn_notifycations)
        btn_notifycation.setOnClickListener {
            swap_Activy(screen_notifycations::class.java)
        }

        // Xử lý sự kiện thay đổi mật khẩu
        btnDoi.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        val oldPassword = passCu.text.toString()
        val newPassword = passMoi.text.toString()
        val confirmNewPassword = nhapLaiPassMoi.text.toString()

        // Kiểm tra các trường nhập liệu
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ các trường", Toast.LENGTH_LONG).show()
            return
        }

        // Lấy mật khẩu cũ từ SharedPreferences
        val savedPassword = sharedPreferences.getString("password", null)

        // Kiểm tra mật khẩu cũ có chính xác không
        if (savedPassword == null || oldPassword != savedPassword) {
            Toast.makeText(this, "Mật khẩu cũ không chính xác", Toast.LENGTH_LONG).show()
            return
        }

        // Kiểm tra mật khẩu mới và xác nhận mật khẩu mới có khớp không
        if (newPassword != confirmNewPassword) {
            Toast.makeText(this, "Mật khẩu mới và xác nhận mật khẩu không khớp", Toast.LENGTH_LONG).show()
            return
        }

        // Lưu mật khẩu mới vào SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("password", newPassword)
        editor.apply()

        Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_LONG).show()

        // Chuyển về màn hình đăng nhập và gửi mật khẩu mới qua Intent
        val intent = Intent(this, screen_Login::class.java)
        intent.putExtra("USERNAME", sharedPreferences.getString("username", null)) // Nếu bạn lưu tên người dùng
        intent.putExtra("PASSWORD", newPassword) // Gửi mật khẩu mới
        startActivity(intent)

        // Kết thúc màn hình thay đổi mật khẩu
        finish()
    }

    // Hàm chuyển Activity
    fun swap_Activy(ac: Class<*>) {
        val i = Intent(this, ac)
        startActivity(i)
    }
}
