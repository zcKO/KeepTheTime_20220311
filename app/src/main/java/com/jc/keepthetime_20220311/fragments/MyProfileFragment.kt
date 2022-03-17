package com.jc.keepthetime_20220311.fragments

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.jc.keepthetime_20220311.ManageMyFriendsActivity
import com.jc.keepthetime_20220311.ManagePlacesActivity
import com.jc.keepthetime_20220311.R
import com.jc.keepthetime_20220311.SplashActivity
import com.jc.keepthetime_20220311.databinding.FragmentMyProfileBinding
import com.jc.keepthetime_20220311.datas.BasicResponse
import com.jc.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnPlaces.setOnClickListener {
            val myIntent = Intent(mContext, ManagePlacesActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnManageMyFriends.setOnClickListener {
            val myIntent = Intent(mContext, ManageMyFriendsActivity::class.java)
            startActivity(myIntent)
        }

        binding.btnLogout.setOnClickListener {

            val alert = AlertDialog.Builder(mContext)
                .setTitle("로그아웃 경고창")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("확인", DialogInterface.OnClickListener { _, _ ->
                    // 실제 로그아웃 처리 -> 저장 된 토큰을 초기화한다.
                    ContextUtil.setLoginUserToken(mContext, "")

                    // 로딩화면으로 복귀
                    val myIntent = Intent(mContext, SplashActivity::class.java)
                    // flag 로 부가적인 옵션 추가가 가능하다.
                    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)

                    // 기존 메인화면 종료. 프래그먼트에는 finish() 기능이 없다.
                    // 구글링 : https://stackoverflow.com/questions/3473168/clear-the-entire-history-stack-and-start-a-new-activity-on-android


                })
                .setNegativeButton("취소", null)
                .show()

        }

    }

    override fun setValues() {

        // 내 정보 조회 > UI 반영
        apiList.getRequestMyInfo().enqueue(object: Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {

                    val br = response.body()!!
//                  ?? = br.data.user.nick_name   // 프래그먼트의 txtNickname 은 어떻게 가져와야 하는가
                    binding.txtNickname.text = br.data.user.nick_name

                    Glide.with(mContext)
                        .load(br.data.user.profile_img)
                        .into(binding.imgProfile)

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                call.cancel()
            }

        })

    }

}