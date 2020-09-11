package com.chanda.todonotesapp.splash

import android.content.Intent
import android.os.Handler
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.onboarding.OnBoardingActivity
import com.chanda.todonotesapp.data.local.pref.PrefConstant
import com.chanda.todonotesapp.data.local.pref.StoreSession
import com.chanda.todonotesapp.login.LoginActivity
import com.chanda.todonotesapp.mynotes.MyNotesActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId


class SplashActivity : AppCompatActivity() {

    lateinit var handler:Handler
    lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreference()
        goToNext()
    }

    private fun goToNext() {
        handler = Handler()
        runnable = Runnable {
            checkLoginStatus()
        }
        handler.postDelayed(runnable,2000)
    }


    private fun setupSharedPreference() {
        StoreSession.init(this)
    }

    private fun checkLoginStatus() {
        val isLoggedIn = StoreSession.read(PrefConstant.IS_LOGGED_IN)
        val isBoardingSuccess = StoreSession.read(PrefConstant.ON_BOARDED_SUCCESSFULLY)

        if (isLoggedIn!!) {
            val intent = Intent(this@SplashActivity, MyNotesActivity::class.java)
            startActivity(intent)
        } else {
            if (isBoardingSuccess!!) {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intent)
            }
        }
        finish()
    }

    private fun getFCMToken() {
        FirebaseInstanceId.getInstance().instanceId
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        Log.w("SplashActivity", "getInstanceId failed", task.exception)
                        return@OnCompleteListener
                    }
                    // Get new Instance ID token
                    val token = task.result?.token
                    // Log and toast

                    Toast.makeText(baseContext, token, Toast.LENGTH_SHORT).show()
                })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(runnable)
    }

}
