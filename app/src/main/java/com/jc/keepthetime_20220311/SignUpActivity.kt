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



    }

    override fun setValues() {

    }
}