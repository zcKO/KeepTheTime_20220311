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