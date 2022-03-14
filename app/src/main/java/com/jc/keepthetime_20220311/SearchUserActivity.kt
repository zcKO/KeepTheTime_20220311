package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivitySearchUserBinding

class SearchUserActivity : BaseActivity() {

    lateinit var binding: ActivitySearchUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {



    }

    override fun setValues() {

    }
}