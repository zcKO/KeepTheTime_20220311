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
        val imgSocialLoginLogo = row.findViewById<ImageView>(R.id.imgSocialLoginLogo)

        Glide.with(mContext)
            .load(data.profile_image)
            .into(imgProfile)

        txtNickname.text = data.nickname

        when (data.provider) {
            "default" -> {
                // 이메일 표시
                txtEmail.text = data.email
                // 로고 이미지 숨김
            }
            "kakao" -> {
                // "카카오 로그인"
                txtEmail.text = "카카오 로그인"
                // 로고 이미지 : 카카오 아이콘
            }
            "fackbook" -> {
                txtEmail.text = "페북 로그인"
            }
            "naver" -> {
                txtEmail.text = "네이버 로그인"
            }
            else -> {
                // 그 외의 잘못된 경우
            }
        }


        // 사용자의 provider  "default" : 이메일 표시, "kakao" : 카카오 로그인, "facebook" : 페북 로그인, "naver" : 네이버 로그인
        // txtEamil 자리에 반영

        return row
    }

}