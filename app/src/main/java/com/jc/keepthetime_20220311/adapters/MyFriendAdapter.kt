package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.service.autofill.UserData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jc.keepthetime_20220311.R

class MyFriendAdapter(
    val mContext: Context,
    resId: Int,
    val mList: List<UserData>
): ArrayAdapter<UserData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate(R.layout.my_friend_list_item, null)
        }

        val row = tempRow!!

        val data = mList[position]
        val imgProfile = row.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = row.findViewById<TextView>(R.id.txtNickname)
        val txtEmail = row.findViewById<TextView>(R.id.txtEmail)

        Glide.with(mContext)
            .load(data.profile_image)
            .into(imgProfile)

        txtNickname.text = data.nickname
        txtEmail.text = data.email


        return row
    }

}