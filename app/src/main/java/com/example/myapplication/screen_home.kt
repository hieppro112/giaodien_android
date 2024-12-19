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
import room

class screen_home : AppCompatActivity() {
    lateinit var btn_top_sp:LinearLayout;
    lateinit var btn_order_room:LinearLayout;
    lateinit var sc_top_room:ScrollView;
    lateinit var sc_order_room:ScrollView;
    lateinit var phong:room
    lateinit var phong2: room
    lateinit var phong3:room
    lateinit var phong4:room
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

        top_room(phong,phong2)
        chitiet_topsp(phong,phong2)
        banner_top()

//        val spinnerT = findViewById<Spinner>(R.id.spinner1)
//        setSpinner(spinnerT)

    }
    fun chitiet_sanpham(){

    }
    fun banner_top(){
        Glide.with(this).load(phong.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(top1_baner)
        Glide.with(this).load(phong2.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(top2_baner)
        Glide.with(this).load(phong3.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(top3_baner)
    }
    fun chitiet_topsp(phong1:room,phong2: room){
        val l_phong1 = findViewById<LinearLayout>(R.id.lay_top1_room)
        l_phong1.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            i.putExtra("title",phong1.title)
            i.putExtra("img",phong1.img)
            i.putExtra("luotdat",phong1.luotdat)
            i.putExtra("content",phong1.content)
            i.putExtra("gia",phong1.gia)
            startActivity(i)
        }
        val l_phong2 = findViewById<LinearLayout>(R.id.lay_top2_room)
        l_phong2.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            i.putExtra("title",phong2.title)
            i.putExtra("img",phong2.img)
            i.putExtra("luotdat",phong2.luotdat)
            i.putExtra("content",phong2.content)
            i.putExtra("gia",phong2.gia)
            startActivity(i)
        }
        val l_phong3 = findViewById<LinearLayout>(R.id.lay_top3_room)
        l_phong3.setOnClickListener {
            val i = Intent(this,screen_chitiet_phong::class.java)
            i.putExtra("title",phong3.title)
            i.putExtra("img",phong3.img)
            i.putExtra("luotdat",phong3.luotdat)
            i.putExtra("content",phong3.content)
            i.putExtra("gia",phong3.gia)
            startActivity(i)
        }
    }
    fun top_room(phong:room,phong2:room){
        title_top1.text=phong.title
        title_top2.text=phong2.title
        title_top3.text=phong3.title
        id_b101.text = phong.title

        Glide.with(this).load(phong.img).placeholder(R.drawable.img_1).error(R.drawable.img_1)
            .into(img_b101)
        Glide.with(this).load(phong.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(img_top1)
        Glide.with(this).load(phong2.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(img_top2)
        Glide.with(this).load(phong3.img).placeholder(R.drawable.img_1).error(R.drawable.img)
            .into(img_top3)
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
        img_b101=findViewById(R.id.img_b101)
        id_b101 = findViewById(R.id.id_b101)


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

        //phong b101
         phong =room(1,"B1.01","https://th.bing.com/th/id/OIP.BENtDtrJF7Fg8dB3YoWf4QHaEo?rs=1&pid=ImgDetMain",
            "phòng vip chuẩn quốc tế 5 sao",1000000.0,215);
        //phong B102
        phong2 =room(2,"B1.02","https://th.bing.com/th/id/R.90947c88256cb1b241036115872f7254?rik=35wrQ3miYSfyWA&riu=http%3a%2f%2fnhamuong.com%2fwp-content%2fuploads%2f2017%2f12%2fTRE_8247-e1512544912308.jpg&ehk=3KLHu2e7Cr%2bShHctfCWnNlTxCwYqwQZ8y6zeXzWZkGA%3d&risl=&pid=ImgRaw&r=0",
            "Phòng có view gần biển gió mát cực kì chill",810000.0,210);
        //phong B103
        phong3 =room(3,"B1.03","https://th.bing.com/th/id/OIP.v1sG89BR8FJPikukpHJefgHaE8?w=253&h=180&c=7&r=0&o=5&pid=1.7",
            "Với góc nhìn toàn cảnh thành phố đây là một lựa chọn đáng chú ý",910000.0,208);
        //phong B104
        phong4 =room(4,"B1.04","https://th.bing.com/th/id/R.cacf51fe5087e4f1518c0c964b44247f?rik=LhOmM8%2b%2bxF02KA&pid=ImgRaw&r=0",
            "Phong khách sạn thoáng mát giá trị cao được nhiều người chú ý ",750000.0,150);




        top1_baner=findViewById(R.id.top1_baner)
        top2_baner=findViewById(R.id.top2_baner)
        top3_baner=findViewById(R.id.top3_baner)

    }

    //thanh menu
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