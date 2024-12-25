package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import room

class screen_giohang : AppCompatActivity() {
    lateinit var btnExit:ImageView
    lateinit var phong:room
    lateinit var phong2: room
    lateinit var phong3:room
    lateinit var phong4:room
    lateinit var phong5:room
    lateinit var phong6:room
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_screen_giohang)
        setcontrol()
        setEvent()
    }
    fun setcontrol(){
        btnExit = findViewById(R.id.btnExit)
        //phong b101
        phong =room(1,"B1.01","https://th.bing.com/th/id/OIP.BENtDtrJF7Fg8dB3YoWf4QHaEo?rs=1&pid=ImgDetMain",
            "phòng vip chuẩn quốc tế 5 sao",1000000.0,450);
        //phong B102
        phong2 =room(2,"B1.02","https://th.bing.com/th/id/R.90947c88256cb1b241036115872f7254?rik=35wrQ3miYSfyWA&riu=http%3a%2f%2fnhamuong.com%2fwp-content%2fuploads%2f2017%2f12%2fTRE_8247-e1512544912308.jpg&ehk=3KLHu2e7Cr%2bShHctfCWnNlTxCwYqwQZ8y6zeXzWZkGA%3d&risl=&pid=ImgRaw&r=0",
            "Phòng có view gần biển gió mát cực kì chill",810000.0,500);
        //phong B103
        phong3 =room(3,"B1.03","https://th.bing.com/th/id/OIP.v1sG89BR8FJPikukpHJefgHaE8?w=253&h=180&c=7&r=0&o=5&pid=1.7",
            "Với góc nhìn toàn cảnh thành phố đây là một lựa chọn đáng chú ý",910000.0,400);
        //phong B104
        phong4 =room(4,"B1.04","https://th.bing.com/th/id/R.cacf51fe5087e4f1518c0c964b44247f?rik=LhOmM8%2b%2bxF02KA&pid=ImgRaw&r=0",
            "Phong khách sạn thoáng mát giá trị cao được nhiều người chú ý ",750000.0,150);
        phong5 =room(5,"B1.05","https://www.besthotelshanghai.com/data/Photos/OriginalPhoto/11274/1127497/1127497656.JPEG",
            "Phong khách sạn thoáng mát giá trị cao được nhiều người chú ý ",950000.0,150);
        phong6 =room(6,"B1.06","https://live.staticflickr.com/2832/8943636766_2bb6601b34_b.jpg",
            "Phong khách sạn thoáng mát giá trị cao được nhiều người chú ý ",6000000.0,900);

    }
    fun setEvent(){
        btnExit.setOnClickListener {
            val  i = Intent(this,screen_chitiet_phong::class.java)
            startActivity(i)
        }
    }
}