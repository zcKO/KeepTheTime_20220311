package com.jc.keepthetime_20220311

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jc.keepthetime_20220311.adapters.MainViewPager2Adapter
import com.jc.keepthetime_20220311.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        // 바텀 네비게이션의 메뉴 선택 > 뷰페이저의 페이지 이동
        binding.mainBottomNav.setOnItemSelectedListener {

            // 어떤 메뉴가 선택되었는지 it 변수가 알려준다.
            when (it.itemId) {

                R.id.myAppointment -> {
                    // 0 번 페이지로 이동
                    binding.mainViewPager2.currentItem = 0
                }
                R.id.myProfile -> {
                    binding.mainViewPager2.currentItem = 1
                }

            }

            return@setOnItemSelectedListener true
        }

        // 뷰페이저의 페이지 이동 > 바텀 네비게이션의 메뉴 선택

    }

    override fun setValues() {

        binding.mainViewPager2.adapter =
            MainViewPager2Adapter(this) // 변수: Activity => 객체 : Context 로 대입 불가

    }
}