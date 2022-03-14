package com.jc.keepthetime_20220311.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jc.keepthetime_20220311.fragments.AppointmentListFragment
import com.jc.keepthetime_20220311.fragments.MyProfileFragment

class MainViewPagerAdapter(
    fm: FragmentManager
): FragmentPagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {
                AppointmentListFragment()
            }
            else -> {
                MyProfileFragment()
            }
        }

    }

}