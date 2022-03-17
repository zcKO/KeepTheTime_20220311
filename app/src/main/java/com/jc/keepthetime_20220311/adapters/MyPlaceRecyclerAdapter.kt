package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.datas.PlaceData

class MyPlaceRecyclerAdapter(
    val mContext: Context,
    val mList: List<PlaceData>
) : RecyclerView.Adapter<MyPlaceRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtStartPlaceName = view.findViewById<TextView>(R.id.txtStartPlaceName)

        // 실제 데이터 반영 기능이 있는 함수
        fun bind(data: PlaceData) {
            txtStartPlaceName.text = data.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // xml 을 inflate 해와서 => 이를 가지고, MyViewHolder 객체로 성생. 리턴
        // 재사용성을 알아서 보존해준다.
        val row =
            LayoutInflater.from(mContext).inflate(R.layout.my_place_list_item, parent, false)
        return MyViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 실제 데이터 반영 함수
        val data = mList[position]

        // 이 함수에서 직접 코딩하면 => holder.UI 변수로 매번 holder 단어를 사용해야한다.
        // holder 변수의 멤버 변수들을 사용할 수 있는 것처럼, 함수도 사용할 수 있다.
        holder.bind(data)
    }

    // 몇 개의 아이템을 보여줄 것인지 => 데이터 목록의 갯 수 만큼 보여준다.
    override fun getItemCount(): Int = mList.size

}