package com.chanda.todonotesapp.onboarding


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import com.chanda.todonotesapp.onboarding.OnBoardingOneFragment
import com.chanda.todonotesapp.onboarding.OnBoardingTwoFragment

class FragmentAdapter(fragmentManager: FragmentActivity) : FragmentStateAdapter(fragmentManager) {

    fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingOneFragment()
            else -> OnBoardingTwoFragment()
        }
    }

     fun getCount(): Int {
        return 2
    }

}

