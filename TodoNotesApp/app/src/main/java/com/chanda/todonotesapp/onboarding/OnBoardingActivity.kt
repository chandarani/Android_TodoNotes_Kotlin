package com.chanda.todonotesapp.onboarding

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.utils.PrefConstant
import com.chanda.todonotesapp.view.LoginActivity


class OnBoardingActivity : AppCompatActivity(), OnBoardingOneFragment.OnNextClick, OnBoardingTwoFragment.OnOptionClick {

    companion object {
        private const val FIRST_ITEM = 0
        private const val LAST_ITEM = 1
    }

    lateinit var viewPager: ViewPager
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        bindView()
        setupSharedPreference()
    }

    private fun setupSharedPreference() {
        sharedPreferences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

    private fun bindView() {
        viewPager = findViewById(R.id.viewPager)
        val adapter = FragmentAdapter(supportFragmentManager)
        viewPager.adapter = adapter
    }

    override fun onClick() {
        viewPager.currentItem = LAST_ITEM
    }

    override fun onOptionBack() {
        viewPager.currentItem = FIRST_ITEM
    }

    override fun onOptionDone() {
        editor = sharedPreferences.edit()
        editor.putBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY,true)
        editor.apply()
        navigateToLogin()
    }

    private fun navigateToLogin() {
        val intent = Intent(this@OnBoardingActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}
