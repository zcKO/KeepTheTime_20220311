package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEamil.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()

            // 회원가입 API 호출 (PUT - "/user")

        }

    }

    override fun setValues() {

    }
}