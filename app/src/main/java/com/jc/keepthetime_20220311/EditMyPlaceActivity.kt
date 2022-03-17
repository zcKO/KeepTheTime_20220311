package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityEditMyPlaceBinding

class EditMyPlaceActivity : BaseActivity() {

    lateinit var binding: ActivityEditMyPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_my_place)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}