package com.jc.keepthetime_20220311

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivitySignInBinding
import com.jc.keepthetime_20220311.datas.BasicResponse
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

            apiList.postRequestLogin(inputEmail, inputPassword).enqueue(object: Callback<BasicResponse> {
                override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {
                    Log.d("서버응답", response.toString())

                    // retrofit 라이브러리의 response 는, "성공 / 실패" 여부에 따라 다른 본문을 봐야한다.
                    // 성공인지
                    if (response.isSuccessful) {
                        // 모든 결과가 최종 성공인 경우. (code = 200 으로 내려옴)
                        // response.body() 이용

                        val br = response.body()!!       // 성공시 무조건 본문이 있다. => BasicResponse 형태의 변수로 파싱되어 나온다.

                        // Retrofit 의 Callback 은 UIThread 안으로 다시 돌아오도록 처리되어 있다.
                        // UI 조작을 위해 runOnUiThread { } 작성이 필요 없다.
                        Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

                    } else {
                        // 실패인지
                        // 서버 연결은 되어 있지만, 로직만 실패. (로그인 비번 틀림 등)
                        // response.errorBody() 활용.
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