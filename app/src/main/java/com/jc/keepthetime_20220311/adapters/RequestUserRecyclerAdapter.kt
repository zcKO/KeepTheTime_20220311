package com.jc.keepthetime_20220311.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
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

            // btnAccept, btnDeny 모두 같은 API 를 호출. (하는 행동이 같다.)
            //  => type 파라미터에 첨부하는 값만 다르다. ("수락"/ "거절")

            // 두 개의 버튼이 눌리면, 할 일을 하나의 변수에 담아두자 (같은 일을 하게 만든다.)
            // 할 일 : Interface => 정석 - object : 인터페이스 종류 { }
            // 축약 문법 (lambda) => 인터페이스 종류 { }
            val ocl = View.OnClickListener {
                // 서버에 수락 / 거절 의사 전달.
                // 수락 버튼 : 수락, 거절 버튼 : 거절 => 어느 버튼이 눌렸는지 파악 가능해야, 파라미터도 다르게 전달 할 수 있다.
                // it 변수 : 클릭된 버튼을 담고 있는 역할.
                // tag 속성 : 아무 말이나 적어도 되는 일종의 메모. 수락 / 거절 등 보내야할 값을 메모해두자

                val tagStr = it.tag.toString()
                Log.d("보낼파라미터값", tagStr)

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
        val view = LayoutInflater.from(mContext).inflate(R.layout.requested_user_list_item, null)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = mList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = mList.size

}