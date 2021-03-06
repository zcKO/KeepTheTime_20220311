package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.api.APIList
import com.jc.keepthetime_20220311.api.ServerApi
import com.jc.keepthetime_20220311.datas.BasicResponse
import com.jc.keepthetime_20220311.datas.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestUserRecyclerAdapter(
    val mContext: Context,
    val mList: List<UserData>
): RecyclerView.Adapter<RequestUserRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val imgProfile = view.findViewById<ImageView>(R.id.imgProfile)
        val txtNickname = view.findViewById<TextView>(R.id.txtNickname)
        val imgSocialLoginLogo = view.findViewById<ImageView>(R.id.imgSocialLoginLogo)
        val txtEmail = view.findViewById<TextView>(R.id.txtEmail)
        val btnAccept = view.findViewById<Button>(R.id.btnAccept)
        val btnDeny = view.findViewById<Button>(R.id.btnDeny)

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
                    txtEmail.text = "????????? ?????????"
                }
                "facebook" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    imgSocialLoginLogo.setImageResource(R.drawable.facebook)
                    txtEmail.text = "???????????? ?????????"
                }
                "naver" -> {
                    imgSocialLoginLogo.visibility = View.VISIBLE
                    imgSocialLoginLogo.setImageResource(R.drawable.naver)
                    txtEmail.text = "????????? ?????????"
                }
            }

            // btnAccept, btnDeny ?????? ?????? API ??? ??????. (?????? ????????? ??????.)
            //  => type ??????????????? ???????????? ?????? ?????????. ("??????"/ "??????")

            // ??? ?????? ????????? ?????????, ??? ?????? ????????? ????????? ???????????? (?????? ?????? ?????? ?????????.)
            // ??? ??? : Interface => ?????? - object : ??????????????? ?????? { }
            // ?????? ?????? (lambda) => ??????????????? ?????? { }
            val ocl = View.OnClickListener {
                // ????????? ?????? / ?????? ?????? ??????.
                // ?????? ?????? : ??????, ?????? ?????? : ?????? => ?????? ????????? ???????????? ?????? ????????????, ??????????????? ????????? ?????? ??? ??? ??????.
                // it ?????? : ????????? ????????? ?????? ?????? ??????.
                // tag ?????? : ?????? ????????? ????????? ?????? ????????? ??????. ?????? / ?????? ??? ???????????? ?????? ???????????????

                val tagStr = it.tag.toString()
                Log.d("?????????????????????", tagStr)

                val retrofit = ServerApi.getRetrofit(mContext)
                val apiList = retrofit.create(APIList::class.java)

                apiList.putRequestAcceptOrDenyFriendRequest(
                    data.id,
                    tagStr
                ).enqueue(object : Callback<BasicResponse> {
                    override fun onResponse(
                        call: Call<BasicResponse>,
                        response: Response<BasicResponse>
                    ) {

                    }

                    override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                        call.cancel()
                    }

                })


            }

            btnAccept.setOnClickListener(ocl)
            btnDeny.setOnClickListener(ocl)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.requested_user_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mList.size

}