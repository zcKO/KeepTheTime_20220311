package com.jc.keepthetime_20220311

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivitySignInBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : BaseActivity() {

    lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

        binding.btnLogin.setOnClickListener {
            val inputEmail = binding.edtEamil.text.toString()
            val inputPassword = binding.edtPassword.text.toString()

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object: Callback<JSONObject> {
                override fun onResponse(call: Call<JSONObject>, response: Response<JSONObject>) {
                    Log.d("서버응답", response.toString())

                    // retrofit 라이브러리의 response 는, "성공 / 실패" 여부에 따라 다른 본문을 봐야한다.
                    // 성공인지
                    if (response.isSuccessful) {
                        // 모든 결과가 최종 성공인 경우. (code = 200 으로 내려옴)
                        // response.body() 이용
                    } else {
                        // 실패인지
                        // 서버 연결은 되어 있지만, 로직만 실패. (로그인 비번 틀림 등)
                        // response.errorBody() 활용.
                    }


                }

                override fun onFailure(call: Call<JSONObject>, t: Throwable) {
                    call.cancel()
                }

            })

        }

    }

    override fun setValues() {

    }
}