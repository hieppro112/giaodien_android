package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.animation.AnimationUtils
import android.widget.ScrollView
import android.widget.Scroller
import androidx.transition.Visibility

class screen_home : AppCompatActivity() {
    lateinit var btn_top_sp:LinearLayout;
    lateinit var btn_order_room:LinearLayout;
    lateinit var sc_top_room:ScrollView;
    lateinit var sc_order_room:ScrollView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_home)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setcontrol()
        chuyenmh()
        click_checkin()
        Event_banner()
        chuyen_top()

//        val spinnerT = findViewById<Spinner>(R.id.spinner1)
//        setSpinner(spinnerT)

    }

    fun chuyen_top(){
        btn_order_room.setOnClickListener {
            sc_order_room.visibility = View.VISIBLE
            sc_top_room.visibility = View.GONE
            btn_top_sp.setBackgroundResource(R.drawable.custom_btn_zoom_click)
            btn_order_room.setBackgroundResource(R.drawable.custom_btn_zoom)

        }
        btn_top_sp.setOnClickListener {
            sc_order_room.visibility = View.GONE
            sc_top_room.visibility = View.VISIBLE
            btn_order_room.setBackgroundResource(R.drawable.custom_btn_zoom_click)
            btn_top_sp.setBackgroundResource(R.drawable.custom_btn_zoom)
        }
    }
    fun setcontrol(){
        btn_top_sp = findViewById(R.id.btn_top_sp)
        btn_order_room = findViewById(R.id.btn_order_room)
        sc_top_room  = findViewById(R.id.sc_top_room)
        sc_order_room = findViewById(R.id.sc_order_room)
    }

    fun chuyenmh(){
        val btn_profile = findViewById<Button>(R.id.btn_profile)
        btn_profile.setOnClickListener {
            val i = Intent(this, screen_profile::class.java)
            startActivity(i)
        }
        val  btn_call = findViewById<Button>(R.id.btn_contact)
        btn_call.setOnClickListener{
            swap_Activy(screen_contact::class.java)
        }
        val btn_statics = findViewById<Button>(R.id.btn_statictisc)
        btn_statics.setOnClickListener{
            swap_Activy(screen_statistics::class.java)
        }

        val btn_notifycations = findViewById<ImageView>(R.id.btn_notifycations)
        btn_notifycations.setOnClickListener{
            swap_Activy(screen_notifycations::class.java)
        }
    }


    private fun setSpinner(spinnerT: Spinner){
        val list = resources.getStringArray(R.array.spinner_tang);
        val hienthi = ArrayAdapter(this, android.R.layout.simple_spinner_item,list)
        spinnerT.adapter = hienthi
        spinnerT.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@screen_home, "ban da chon: "+ list[p2],Toast.LENGTH_LONG).show();
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
    fun swap_Activy(ac:Class<*>){
        val i = Intent(this, ac)
        startActivity(i)
    }

    fun showDialog(){
        val dialogView = LayoutInflater.from(this).inflate(R.layout.custom_dialog_checkin,null)

        val builer = AlertDialog.Builder(this)
        builer.setView(dialogView)

        val dialog = builer.create()
        val btn_close = dialogView.findViewById<Button>(R.id.btn_close)
        btn_close.setOnClickListener{
            dialog.cancel()
        }
        val btn_xong_dialog_checkin =dialogView.findViewById<Button>(R.id.btnxong_dialog_checkin)
        btn_xong_dialog_checkin.setOnClickListener{
            val list_img = listOf(
                Pair(findViewById<LinearLayout>(R.id.b1_01),findViewById<ImageView>(R.id.img1)),
                Pair(findViewById<LinearLayout>(R.id.b1_02),findViewById<ImageView>(R.id.img2)),
                Pair(findViewById<LinearLayout>(R.id.b1_03),findViewById<ImageView>(R.id.img3)),
                Pair(findViewById<LinearLayout>(R.id.b1_04),findViewById<ImageView>(R.id.img4)),
                Pair(findViewById<LinearLayout>(R.id.b1_05),findViewById<ImageView>(R.id.img5)),
                Pair(findViewById<LinearLayout>(R.id.b1_06),findViewById<ImageView>(R.id.img6))
            )
            //Toast.makeText(this,statu)
            for ((layout,img) in list_img){
                layout.setOnClickListener {
                    if(img.drawable.constantState == resources.getDrawable(R.drawable.icon_green).constantState){
                        img.setImageResource(R.drawable.icon_red)
                    }
                }
            }
            dialog.cancel()
        }
        dialog.show()
    }
    fun show_chitiet_sp(){
        val i = Intent(this,screen_chitiet_phong::class.java)
        startActivity(i)
    }

    fun click_checkin(){
        val list_zoom = listOf(
            R.id.b1_01,
            R.id.b1_02,
            R.id.b1_03,
            R.id.b1_04,
            R.id.b1_05,
            R.id.b1_06)

        list_zoom.forEach { id->
            val room_layout = findViewById<LinearLayout>(id)
            room_layout.setOnClickListener {
//                showDialog()
                show_chitiet_sp()
            }
        }

    }
    fun Event_banner(){
        val view_Flapip = findViewById<ViewFlipper>(R.id.v_Flipper_banner)
//            view_Flapip.inAnimation = AnimationUtils.lo
        view_Flapip.inAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        view_Flapip.outAnimation = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)
        view_Flapip.startFlipping()
    }
}