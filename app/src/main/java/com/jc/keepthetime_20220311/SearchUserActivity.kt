package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivitySearchUserBinding
import com.jc.keepthetime_20220311.datas.BasicResponse
import com.jc.keepthetime_20220311.datas.UserData
import com.jc.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchUserActivity : BaseActivity() {

    lateinit var binding: ActivitySearchUserBinding

    val mSearchedUserList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnSearch.setOnClickListener {

            val inputKeyword = binding.edtNickname.text.toString()
            apiList.getRequestSearchUser(
                ContextUtil.getLoginUserToken(mContext),
                inputKeyword
            ).enqueue(object: Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        val br = response.body()!!
                        mSearchedUserList.addAll(br.data.users)
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                    call.cancel()
                }

            })

        }

    }

    override fun setValues() {

    }
}