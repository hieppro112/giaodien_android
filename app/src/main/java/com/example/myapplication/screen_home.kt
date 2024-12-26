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
import android.widget.TextView
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.example.myapplication.screen_profile
import room

class screen_home : AppCompatActivity() {
    lateinit var btn_top_sp:LinearLayout;
    lateinit var btn_order_room:LinearLayout;
    lateinit var sc_top_room:ScrollView;
    lateinit var sc_order_room:ScrollView;
    lateinit var phong:LinearLayout
    lateinit var phong2: LinearLayout
    lateinit var phong3:LinearLayout
    lateinit var phong4:LinearLayout
    lateinit var phong5:LinearLayout
    lateinit var phong6:LinearLayout
    lateinit var img_top1:ImageView
    lateinit var title_top1:TextView
    lateinit var img_top2:ImageView
    lateinit var title_top2:TextView
    lateinit var img_top3:ImageView
    lateinit var title_top3:TextView
    lateinit var top1_baner:ImageView
    lateinit var top2_baner:ImageView
    lateinit var top3_baner:ImageView

    lateinit var img_b101:ImageView
    lateinit var id_b101:TextView
    lateinit var img_b102:ImageView
    lateinit var id_b102:TextView
    lateinit var img_b103:ImageView
    lateinit var id_b103:TextView
    lateinit var img_b104:ImageView
    lateinit var id_b104:TextView
    lateinit var img_b105:ImageView
    lateinit var id_b105:TextView
    lateinit var img_b106:ImageView
    lateinit var id_b106:TextView


    lateinit var lay_top1_room:LinearLayout
    lateinit var lay_top2_room:LinearLayout
    lateinit var lay_top3_room:LinearLayout

    private val dulieu_phong = dulieu_Room.rooms.toMutableList()
    private lateinit var list_room: List<LinearLayout>



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
//        click_checkin()
        Event_banner()
        chuyen_top()

//        top_room(phong,phong2)
        banner_top()
        tim_top_room()

        chitiet_phong_top()
        full_room()

//        val spinnerT = findViewById<Spinner>(R.id.spinner1)
//        setSpinner(spinnerT)

    }
    fun sapXepPhongTheoLuotDat(phongs: List<room>): List<room> {
        return phongs.sortedByDescending { it.luotdat }
    }
    //hiep top san pham
    fun tim_top_room(){
        val sapxepphong = sapXepPhongTheoLuotDat(dulieu_phong)
        val top3 = sapxepphong.take(3)
        var i:Int =0
        dulieu_phong.forEach {value ->
                i += 1
                //Toast.makeText(this,"title: ${phong.title}  img: ${phong.luotdat}",Toast.LENGTH_LONG).show()
                when (i) {

                    1 -> {
                        title_top1.text = value.title
                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img_1)
                            .into(img_top1)

                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img)
                            .into(top1_baner)
                    }

                    2 -> {
                        title_top2.text = value.title
                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img_1)
                            .into(img_top2)
                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img)
                            .into(top2_baner)

                    }

                    3 -> {
                        title_top3.text = value.title
                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img_1)
                            .into(img_top3)
                        Glide.with(this).load(value.img).placeholder(R.drawable.img_1)
                            .error(R.drawable.img)
                            .into(top3_baner)
                    }
                }


        }

    }
    fun chitiet_sanpham(){

    }

    //hiep banner
    fun banner_top(){

    }


    fun chitiet_phong_top(){
        lay_top1_room.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            if (title_top1.text==null){
                Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
            }
            else {
                i.putExtra("tt", title_top1.text.toString())
            }
            startActivity(i)
        }
        lay_top2_room.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            if (title_top2.text==null){
                Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
            }
            else {
                i.putExtra("tt", title_top2.text.toString())
            }
            startActivity(i)
        }
        lay_top3_room.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            if (title_top3.text==null){
                Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
            }
            else {
                i.putExtra("tt", title_top3.text.toString())
            }
            startActivity(i)
        }

        //load dl
        list_room.forEachIndexed { index, phong ->
            phong.setOnClickListener {
                val roomNumber = index + 1
                Toast.makeText(this, "Phòng $index được nhấn", Toast.LENGTH_SHORT).show()
                when(index){
                    0->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[0].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[0].title)
                        }
                        startActivity(i)
                    }
                    1->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[1].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[1].title)
                        }
                        startActivity(i)
                    }
                    2->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[2].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[2].title)
                        }
                        startActivity(i)
                    }
                    3->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[3].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[3].title)
                        }
                        startActivity(i)
                    }
                    4->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[4].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[4].title)
                        }
                        startActivity(i)
                    }
                    5->{
                        val i = Intent(this,screen_chitiet_phong::class.java)
                        if (dulieu_phong[5].title==null){
                            Toast.makeText(this,"gitri null",Toast.LENGTH_LONG).show()
                        }
                        else {
                            i.putExtra("tt", dulieu_phong[5].title)
                        }
                        startActivity(i)
                    }
                }
            }
        }
    }


    //hiep hien thi top
    fun full_room(){
        var i:Int =0
        dulieu_phong.forEach { value ->
            i += 1
            var link:String=""
            link = value.img.toString()
            when(i) {
                1-> {
                    id_b101.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b101)
                }
                2-> {
                    id_b102.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b102)
                }
                3-> {
                    id_b103.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b103)
                }
                4-> {
                    id_b104.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b104)
                }
                5-> {
                    id_b105.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b105)
                }
                6-> {
                    id_b106.text = value.title.toString()
                    Glide.with(this).load(link).placeholder(R.drawable.room_background)
                        .error(R.drawable.room_background)
                        .override(500, 400).into(img_b106)
                }
                else->{}
            }
        }

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
         lay_top1_room = findViewById(R.id.lay_top1_room)
         lay_top2_room = findViewById(R.id.lay_top2_room)
         lay_top3_room = findViewById(R.id.lay_top3_room)


        img_b101=findViewById(R.id.img_b101)
        id_b101 = findViewById(R.id.id_b101)

        img_b102=findViewById(R.id.img_b102)
        id_b102 = findViewById(R.id.id_b102)

        img_b103=findViewById(R.id.img_b103)
        id_b103 = findViewById(R.id.id_b103)

        img_b104=findViewById(R.id.img_b104)
        id_b104 = findViewById(R.id.id_b104)

        img_b105=findViewById(R.id.img_b105)
        id_b105 = findViewById(R.id.id_b105)

        img_b106=findViewById(R.id.img_b106)
        id_b106 = findViewById(R.id.id_b106)



        btn_top_sp = findViewById(R.id.btn_top_sp)
        btn_order_room = findViewById(R.id.btn_order_room)
        sc_top_room  = findViewById(R.id.sc_top_room)
        sc_order_room = findViewById(R.id.sc_order_room)


        img_top1 = findViewById(R.id.img_top1)
        title_top1 = findViewById(R.id.top1_title)
        img_top2= findViewById(R.id.top2_img)
        title_top2 = findViewById(R.id.top2_title)
        img_top3 =findViewById(R.id.img_top3)
        title_top3 = findViewById(R.id.top3_title)

        phong = findViewById(R.id.b1_01)
        phong2 = findViewById(R.id.b1_02)
        phong3= findViewById(R.id.b1_03)
        phong4 = findViewById(R.id.b1_04)
        phong5 = findViewById(R.id.b1_05)
        phong6 = findViewById(R.id.b1_06)

        list_room= listOf(phong,phong2,phong3,phong4,phong5,phong6)



        top1_baner=findViewById(R.id.top1_baner)
        top2_baner=findViewById(R.id.top2_baner)
        top3_baner=findViewById(R.id.top3_baner)

    }
    //thanh menu
    fun chuyenmh(){
        val btn_cart = findViewById<ImageView>(R.id.btn_cart)
        btn_cart.setOnClickListener {
            val i = Intent(this, screen_giohang::class.java)
            startActivity(i)
        }
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