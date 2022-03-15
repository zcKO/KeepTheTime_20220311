package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.datas.UserData

class RequestUserRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData>
): RecyclerView.Adapter<RequestUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)

        fun bind(data: UserData) {

            Glide.with(mContext)
                .load(data.profile_img)
                .into(imgProfile)

            txtNickname.text = data.nick_name

            Glide.with(mContext)
                .load(data.profile_img)
                .into(imgProfile)

            when (data.provider) {
                "default" -> {
                    imgSocialLoginLogo.visibility = View.GONE
                    txtEmail.text = data.email
                }
                "kakao" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    imgSocialLoginLogo.setImageResource(R.drawable.kakao)
                    txtEmail.text = "카카오 로그인"
                }
                "facebook" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    imgSocialLoginLogo.setImageResource(R.drawable.facebook)
                    txtEmail.text = "페이스북 로그인"
                }
                "naver" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    imgSocialLoginLogo.setImageResource(R.drawable.naver)
                    txtEmail.text = "네이버 로그인"
                }
            }

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.requested_user_list_item, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mList.size

}