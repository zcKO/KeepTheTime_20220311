package com.jc.keepthetime_20220311.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jc.keepthetime_20220311.fragments.AppointmentListFragment
import com.jc.keepthetime_20220311.fragments.MyProfileFragment

class MainViewPager2Adapter(
    fa: FragmentActivity
) : FragmentStateAdapter(fa) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> AppointmentListFragment()
            else -> MyProfileFragment()
        }
    }
}