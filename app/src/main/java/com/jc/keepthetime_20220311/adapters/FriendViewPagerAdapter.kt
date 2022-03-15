package com.jc.keepthetime_20220311.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.jc.keepthetime_20220311.fragments.AppointmentListFragment
import com.jc.keepthetime_20220311.fragments.MyFriendsFragment
import com.jc.keepthetime_20220311.fragments.MyProfileFragment
import com.jc.keepthetime_20220311.fragments.RequestedUsersFragment

class FriendViewPagerAdapter(
    fm: FragmentManager
): FragmentPagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {

        return when(position) {
            0 -> {
                MyFriendsFragment()
            }
            else -> {
                RequestedUsersFragment()
            }
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> {"내 친구 목록"}
            else -> {"친구 요청 확인"}
        }
    }

}