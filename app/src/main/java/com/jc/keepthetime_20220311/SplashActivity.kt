package com.jc.keepthetime_20220311

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jc.keepthetime_20220311.datas.BasicResponse
import com.jc.keepthetime_20220311.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

        // 실제 - 저장된 토큰으로, 내 정보 조회 시도 먼저 진행
        // 2.5 초후에, 내 정보가 불러와졌는지 확인 그 결과에 따라 다른 화면으로 이동.

        var isMyInfoLoaded = false

        apiList.getRequestMyInfo().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                // CODE : 200 => 성공 응답이 왔다면 내 정보가 일단 잘 불려졌다.
                if (response.isSuccessful) {
                    isMyInfoLoaded = true
                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
                call.cancel()
            }

        })

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed({

            val myIntent: Intent

            if (isMyInfoLoaded) {
                myIntent = Intent(mContext, MainActivity::class.java)
            } else {
                myIntent = Intent(mContext, SignInActivity::class.java)
            }
            startActivity(myIntent)
            finish()

        }, 2500)

    }
}