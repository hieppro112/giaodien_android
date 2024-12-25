package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class notifyAdap(val Title:Context,var mang:ArrayList<notify>):BaseAdapter() {
    class ViewHolder(row:View){
        var texttitle: TextView
        var textcontent: TextView
        init {
            texttitle=row.findViewById(R.id.tv_title_notify)
            textcontent = row.findViewById(R.id.tv_content_notify)
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

    override fun getView(positon: Int, convertView: View?, p2: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView==null){
            var layoutinflater: LayoutInflater = LayoutInflater.from(Title)
            view=layoutinflater.inflate(R.layout.layout_notiffy,null)
            viewHolder= ViewHolder(view)
            view.tag=viewHolder
        }
        else{
            view=convertView
            viewHolder=convertView.tag as ViewHolder
        }
        var noti:notify=mang.get(positon) as notify
        viewHolder.texttitle.text=noti.title
        viewHolder.textcontent.text = noti.content
        return view as View
    }
}