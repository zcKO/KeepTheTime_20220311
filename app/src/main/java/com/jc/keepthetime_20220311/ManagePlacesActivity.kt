package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.databinding.ActivityManagePlacesBinding

class ManagePlacesActivity : BaseActivity() {

    lateinit var binding: ActivityManagePlacesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_places)

        setupEvents()
        setValues()

    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}