package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import room

private val KEY_LIST = "dulieu_giohang_item"
private val gson = Gson()
private val PREFS_NAME = "giohang_item"
private  var pos = -1
lateinit var btn_thanhtoan:Button
private var Arr:ArrayList<room> = ArrayList()
class screen_giohang : AppCompatActivity() {
    lateinit var list: ListView
    lateinit var btnExit:ImageView
    private var title:String=""
    lateinit var btnClearCart:ImageButton
    private val dulieu_phong = dulieu_Room.rooms.toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_giohang)
        val sharedPreferences1 = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
        setcontrol(sharedPreferences1)
        setEvent(sharedPreferences1)
        delete_giohang(sharedPreferences1)
    }
    fun setcontrol(sharedPreferences: SharedPreferences){
        loaddl(sharedPreferences)
        btnExit = findViewById(R.id.btnExit)
        list=findViewById(R.id.list_giohang_item)
        btnClearCart=findViewById(R.id.btnClearCart)
        title=intent.getStringExtra("title").toString()
        Toast.makeText(this,title,Toast.LENGTH_LONG).show()
        btn_thanhtoan=findViewById(R.id.btn_thanhtoan)
    }
    fun setEvent(sharedPreferences: SharedPreferences){
        btnExit.setOnClickListener {
            finish()
        }
//        dulieu_phong.forEach { value->
//            if (value.title==title){
//                Arr.add(value)
//                val adapter = list.adapter as giohangAdap
//                adapter.notifyDataSetChanged()
//                Toast.makeText(this, "Phòng đã được thêm", Toast.LENGTH_SHORT).show()
//
//                savedl(sharedPreferences, Arr)
//            }
//
//        }
        val list_value = ArrayList<room>()
        dulieu_phong.forEach { value ->
            if (value.title == title) {
                Arr.add(value)
            }
        }

        // Tạo adapter một lần
        val adapter = giohangAdap(this@screen_giohang, Arr)
        list.adapter = adapter

        // Lưu dữ liệu đã lọc vào SharedPreferences
        savedl(sharedPreferences, Arr)

    }

    fun delete_giohang(sharedPreferences: SharedPreferences){
        list.setOnItemClickListener { parent, view, position, id ->
            val notification = Arr[position]
            pos=position
            btnClearCart.setOnClickListener{
                Arr.removeAt(pos)
                val adapter = list.adapter as giohangAdap
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Đã xóa phòng", Toast.LENGTH_SHORT).show()
                savedl(sharedPreferences, Arr)
            }
            btn_thanhtoan.setOnClickListener {
                val  i = Intent(this,thanhtoan::class.java)
                i.putExtra("tien",Arr[position].gia.toString())
                startActivity(i)
            }

        }
    }
    fun thanhtoan(){

    }
    private fun savedl(sharedPreferences: SharedPreferences, list: ArrayList<room>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(KEY_LIST, json)
        editor.apply()
    }

    // Hàm tải danh sách từ SharedPreferences
    private fun loaddl(sharedPreferences: SharedPreferences): ArrayList<room> {
        val json = sharedPreferences.getString(KEY_LIST, null)
        return if (json != null) {
            try {
                val type = object : TypeToken<ArrayList<room>>() {}.type
                gson.fromJson(json, type)
            } catch (e: Exception) {
                e.printStackTrace()
                ArrayList() // Trả về danh sách trống nếu gặp lỗi
            }
        } else {
            ArrayList() // Trả về danh sách trống nếu không có dữ liệu
        }
    }

}