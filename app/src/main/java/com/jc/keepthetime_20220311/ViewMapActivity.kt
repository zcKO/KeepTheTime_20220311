package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityViewMapBinding

class ViewMapActivity : BaseActivity() {

    lateinit var binding: ActivityViewMapBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_map)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {
    }
}