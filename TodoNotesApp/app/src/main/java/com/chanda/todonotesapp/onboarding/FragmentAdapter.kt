package com.chanda.todonotesapp.onboarding


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.chanda.todonotesapp.onboarding.OnBoardingOneFragment
import com.chanda.todonotesapp.onboarding.OnBoardingTwoFragment

class FragmentAdapter(fm: FragmentActivity) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> OnBoardingOneFragment()
            else -> OnBoardingTwoFragment()
        }
    }

}