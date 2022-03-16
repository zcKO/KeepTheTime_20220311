package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.datas.AppointmentData

class AppointmentRecyclerAdapter(
    val mContext: Context,
    val mList: List<AppointmentData>
) : RecyclerView.Adapter<AppointmentRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtTitle = view.findViewById<TextView>(R.id.txtTitle)
        val txtDateTime = view.findViewById<TextView>(R.id.txtDateTime)
        val txtPlaceName = view.findViewById<TextView>(R.id.txtPlaceName)
        val imgViewMap = view.findViewById<ImageView>(R.id.imgViewMap)

        fun bind(data: AppointmentData) {

            txtTitle.text = data.title
            txtPlaceName.text = data.place

            // 서버가 주는 datetime (String - 2022-03-15 10:57:23 양식)

            // 중간에 calendar / Date 형태로 변환 (parse) => 파싱 할 때 부터 Calendar / Date 라고 한다면


            // 출력하고 싶은 datetime (String - 22년 3월 5일 오후 10:57 양식)


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater
                .from(mContext)
                .inflate(R.layout.appointment_list_item, parent, false)
        )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val data = mList[position]
        holder.bind(data)

    }

    override fun getItemCount(): Int = mList.size

}