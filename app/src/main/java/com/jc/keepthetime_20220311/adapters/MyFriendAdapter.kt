package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.datas.UserData

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

        Log.d("TAG", "getView: ${data.email}")
        Log.d("TAG", "getView: ${data.provider}")

        Glide.with(mContext)
            .load(data.profile_img)
            .into(imgProfile)

        txtNickname.text = data.nick_name

//        사용자의 provider - "default" : 이메일 표시, "kakao" : 카카오 로그인, "facebook" : 페북 로그인, "naver" : 네이버 로그인
//        txtEmail 자리에 반영
        when (data.provider) {
            "default" -> {
//                이메일 표시
                txtEmail.text = data.email
//                로고 이미지 숨김
                imgSocialLoginLogo.visibility = View.GONE
            }
            "kakao" -> {
//                "카카오로그인"
                txtEmail.text = "카카오로그인"
//                로고 이미지 : 카카오 아이콘
                imgSocialLoginLogo.visibility = View.VISIBLE
//                imgSocialLoginLogo.setImageResource(R.drawable.kakao)
                Glide.with(mContext)
                    .load(R.drawable.kakao)
                    .into(imgSocialLoginLogo)
            }
            "facebook" -> {
                txtEmail.text = "페북 로그인"
                imgSocialLoginLogo.visibility = View.VISIBLE
                Glide.with(mContext)
                    .load(R.drawable.facebook)
                    .into(imgSocialLoginLogo)
            }
            "naver" -> {
                txtEmail.text  = "네이버 로그인"
                imgSocialLoginLogo.visibility = View.VISIBLE
                // Glide 는 웹의 이미지 뿐 아니라, 우리 프로젝트 내부의 이미지도 불러올 수 있다.
                Glide.with(mContext)
                    .load(R.drawable.naver)
                    .into(imgSocialLoginLogo)
            }
            else -> {
//                그 외의 잘못된 경우.
            }
        }



        return row
    }

}