package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.datas.UserData

class SearchedUserRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData>
) : RecyclerView.Adapter<SearchedUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        // xml 을 inflate 해와서 => 이를 가지고, MyViewHolder 객체로 성생. 리턴
        // 재사용성을 알아서 보존해준다.
        val row = LayoutInflater.from(mContext).inflate(R.layout.searched_user_list_item, parent, false)
        return MyViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // 실제 데이터 반영 함수

    }

    // 몇 개의 아이템을 보여줄 것인지 => 데이터 목록의 갯 수 만큼 보여준다.
    override fun getItemCount(): Int = mList.size

}