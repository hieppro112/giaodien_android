package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class screen_Login : AppCompatActivity() {
    // khai báo
    private lateinit var user: EditText
    private lateinit var pass: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }
        // ánh xạ
        user = findViewById(R.id.txt_user)
        pass = findViewById(R.id.txt_pass)


        // láy giá trị từ intent đăng ký
        val duLieuUser = intent.getStringExtra("USERNAME")
        val duLieuPass = intent.getStringExtra("PASSWORD")

        val  btn_exit = findViewById<Button>(R.id.btn_back_login)
        btn_exit.setOnClickListener{


            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        val btn_login = findViewById<Button>(R.id.btn_login_home)
        btn_login.setOnClickListener{
            val nhapUser = user.text.toString()
            val nhapPass = pass.text.toString()

            if ((nhapUser == duLieuUser && nhapPass == duLieuPass)||laydl_admin(user.text.toString(),pass.text.toString())==true) {
                // Nếu đăng nhập thành công, chuyển sang màn hình Home
                Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show()
                swap_Activy(screen_home::class.java)  // Chuyển tới màn hình Home
            } else {
                Toast.makeText(this, "Thông tin đăng nhập không chính xác, xem lại tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show()
            }

        }

    }
    // hàm chuyển đổi
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }
    // kiểm tra xem người dùng có nhập đúng dữ liệu admin hay kh
    fun laydl_admin( user_a:String, pass_a:String):Boolean{
        val user_ad = intent.getStringExtra("user_admin")
        val pass_ad = intent.getStringExtra("pass_admin")
        if (user_ad==user_a&&pass_ad==pass_a){
            return true;
        };
        return false;

    }
}