package com.example.myapplication

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class screen_notifycations : AppCompatActivity() {
    lateinit var btn_admin_tb:Button
    lateinit var btn_add_notify: Button
    lateinit var notifycations_title: EditText
    lateinit var notifycations_content: EditText
    lateinit var dialog_tb_save:Button
    lateinit var dialog_tb_huy:Button
    lateinit var list_notify:ListView
    private lateinit var dialog_view :View

    private val KEY_LIST = "data_list"
    private val gson = Gson()
    private val PREFS_NAME = "MyPrefs"
    private  var pos = -1
    private var Arr:ArrayList<notify> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_notifycations)
        val sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        setcontrol(sharedPreferences)
//        setEvent()
        chuyen_mh()
        on_add_thongbao()
        get_position(sharedPreferences)
        set_add_thongbao(sharedPreferences)
    }


    fun show_dialog_notifycations(sharedPreferences: SharedPreferences) {
        dialog_view = LayoutInflater.from(this).inflate(R.layout.custom_dialog_notifycations, null)
        val notifycations_title = dialog_view.findViewById<EditText>(R.id.notifycations_title)
        val notifycations_content = dialog_view.findViewById<EditText>(R.id.notifycations_content)
        val dialog_tb_huy = dialog_view.findViewById<Button>(R.id.dialog_tb_huy)
        val dialog_tb_save = dialog_view.findViewById<Button>(R.id.dialog_tb_save)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialog_view)
        val dialog = builder.create()
        dialog_tb_huy.setOnClickListener {
            dialog.cancel()
        }
        dialog_tb_save.setOnClickListener {
            if (notifycations_title.text.isBlank() || notifycations_content.text.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show()
            } else {
                Arr.add(notify(notifycations_title.text.toString(), notifycations_content.text.toString()))

                val adapter = list_notify.adapter as notifyAdap
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Thông báo đã được thêm", Toast.LENGTH_SHORT).show()
                dialog.cancel()

                savedl(sharedPreferences, Arr)
            }
        }
        dialog.show()
    }

    fun set_add_thongbao( sharedPreferences: SharedPreferences){
        btn_admin_tb.setOnClickListener{
            show_dialog_notifycations(sharedPreferences)
        }
    }
    fun on_add_thongbao(){
        val add_notify  = getSharedPreferences("admin", MODE_PRIVATE)
        val dulieu =add_notify.getString("admin","false")
//        Toast.makeText(this,dulieu.toString(),Toast.LENGTH_LONG).show()
        if (dulieu!="true"){

            btn_admin_tb.alpha=0.5f
            btn_admin_tb.isEnabled=false
        }
        else{
            btn_admin_tb.alpha=1f
            btn_admin_tb.isEnabled=true
        }
    }
    fun  chuyen_mh(){
        val  btn_home = findViewById<Button>(R.id.btn_home)
        btn_home.setOnClickListener{
            swap_Activy(screen_home::class.java)
        }
        val  btn_profile = findViewById<Button>(R.id.btn_profile)
        btn_profile.setOnClickListener{
            swap_Activy(screen_profile::class.java)
        }
        val  btn_contact = findViewById<Button>(R.id.btn_contact)
        btn_contact.setOnClickListener{
            swap_Activy(screen_contact::class.java)
        }
        val  btn_statistics = findViewById<Button>(R.id.btn_statictisc)
        btn_statistics.setOnClickListener{
            swap_Activy(screen_statistics::class.java)
        }
    }
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }
    fun setcontrol(sharedPreferences: SharedPreferences){

        btn_admin_tb=findViewById(R.id.btn_add_notify)
        list_notify=findViewById(R.id.list_notify)
        Arr = loaddl(sharedPreferences)
        loaddl(sharedPreferences)

        list_notify.adapter = notifyAdap(this@screen_notifycations,Arr)
    }

    fun get_position(sharedPreferences:SharedPreferences) {
        list_notify.setOnItemClickListener { parent, view, position, id ->
            val notification = Arr[position]
            pos=position
            if (notification != null) {
                val i = Intent(this@screen_notifycations, chitiet_TB::class.java)
                i.putExtra("title", notification.title.toString())
                i.putExtra("content", notification.content.toString())
                i.putExtra("pos", pos)

                startActivity(i)
            }

        }


        val delete =intent.getIntExtra("delete",-1)
        if (delete!=-1&&delete!=null){
            Arr.removeAt(delete)
            val adapter = list_notify.adapter as notifyAdap
            adapter.notifyDataSetChanged()
            Toast.makeText(this, "Đã xóa thông báo", Toast.LENGTH_SHORT).show()
            savedl(sharedPreferences, Arr)
        }
        var vitri = intent.getIntExtra("vitri",-1)
        if (vitri!=-1&&delete!=null){
            val tt =intent.getStringExtra("title")
            val ct =intent.getStringExtra("content")
            Arr.set(vitri,notify(tt.toString(),ct.toString()))
            val adapter = list_notify.adapter as notifyAdap
            adapter.notifyDataSetChanged()
            savedl(sharedPreferences, Arr)
        }





    }


    fun hienthi_tb(){
        val dialog_view = LayoutInflater.from(this).inflate(R.layout.chitiet_tb,null)
        val builder = AlertDialog.Builder(this)
        builder.setView(dialog_view)
        val dialog = builder.create()

    }
    private fun savedl(sharedPreferences: SharedPreferences, list: ArrayList<notify>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(list)
        editor.putString(KEY_LIST, json)
        editor.apply()
    }

    // Hàm tải danh sách từ SharedPreferences
    private fun loaddl(sharedPreferences: SharedPreferences): ArrayList<notify> {
        val json = sharedPreferences.getString(KEY_LIST, null)
        return if (json != null) {
            try {
                val type = object : TypeToken<ArrayList<notify>>() {}.type
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
