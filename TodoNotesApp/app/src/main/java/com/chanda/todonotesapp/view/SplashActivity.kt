package com.chanda.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.onboarding.OnBoardingActivity
import com.chanda.todonotesapp.utils.PrefConstant
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class SplashActivity : AppCompatActivity(){

    lateinit var sharedPrefernce : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        checkLoginStatus()
        getFCMToken()
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("SplashActivity", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }

                    // Get new Instance ID token
                    val token = task.result!!.token

                    // Log and toast

                    Log.d("SplashActivity", token)
                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
    }

    private fun checkLoginStatus() {
        val isLoggedIn = sharedPrefernce.getBoolean(PrefConstant.IS_LOGGED_IN, false)
        val isBoardingSuccess = sharedPrefernce.getBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY,false)
        if (isLoggedIn){
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        }else{
            if (isBoardingSuccess) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setupSharedPreference() {
        sharedPrefernce = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }

}