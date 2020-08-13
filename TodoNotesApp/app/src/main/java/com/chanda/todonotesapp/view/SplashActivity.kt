package com.chanda.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chanda.todonotesapp.utils.PrefConstant
import com.chanda.todonotesapp.R

class SplashActivity : AppCompatActivity(){

    lateinit var sharedPrefernce : SharedPreferences

    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPrefernce.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        if (isLoggedIn){
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        }else{
            val intent = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setupSharedPreference() {
        sharedPrefernce = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME,Context.MODE_PRIVATE)
    }

}