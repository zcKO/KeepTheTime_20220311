package com.jc.keepthetime_20220311

import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityManageMyFriendsBinding
import com.jc.keepthetime_20220311.datas.BasicResponse
import com.jc.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ManageMyFriendsActivity : BaseActivity() {

    lateinit var binding: ActivityManageMyFriendsBinding

    val mFriendList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_my_friends)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        getMyFriendListFromServer()


    }


    fun getMyFriendListFromServer() {

        apiList.getRequestFriendList(
            ContextUtil.getLoginUserToken(mContext),
            "my"  // 수락 완료된 친구목록만 불러오기
        ).enqueue(object: Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
                    val br = response.body()!!

                    // br.data.friends 는 UserData 목록으로 이미 내려옴
                    // 목록의 내용물을 통째로 => mFriendList 변수의 내용물로 담자.
                    mFriendList.addAll( br.data.friends )

                    // 어댑터 새로 고침

                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                call.cancel()
            }

        })

    }

}