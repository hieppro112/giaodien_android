package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import giohang_item
import room

public class giohangAdap(var context: Context, var mang:ArrayList<room>):BaseAdapter() {
    class ViewHolder(row:View){
        var img_item: ImageView
        var title_item: TextView
        var thanhtien: TextView
        init {
            img_item=row.findViewById(R.id.img_item)
            title_item = row.findViewById(R.id.title_item)
            thanhtien=row.findViewById(R.id.thanhtien_item)
        }
    }
    override fun getCount(): Int {
        return mang.size
    }

    override fun getItem(p0: Int): Any {
        return mang.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView==null){
            var layoutinflater: LayoutInflater = LayoutInflater.from(context)
            view=layoutinflater.inflate(R.layout.layout_giohang_item,null)
            viewHolder= ViewHolder(view)
            view.tag=viewHolder
        }
        else{
            view=convertView
            viewHolder=convertView.tag as ViewHolder
        }
        var giohang_item:room=mang.get(position) as room
        viewHolder.img_item.let { imageView ->
            Glide.with(context).load(giohang_item.img).error(R.drawable.room_background)
                .into(imageView)
        }
        viewHolder.title_item.text =giohang_item.title
        viewHolder.thanhtien.text = giohang_item.gia.toString()
        return view as View
    }
}