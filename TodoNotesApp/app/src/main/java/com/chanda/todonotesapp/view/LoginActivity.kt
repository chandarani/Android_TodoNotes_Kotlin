package com.chanda.todonotesapp.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.chanda.todonotesapp.utils.AppConstant
import com.chanda.todonotesapp.utils.PrefConstant
import com.chanda.todonotesapp.R

class LoginActivity : AppCompatActivity(){
    lateinit var editTextFullName: EditText
    lateinit var editTextUserName: EditText
    lateinit var buttonLogin: Button
    lateinit var editor: SharedPreferences.Editor
    lateinit var sharedPrefrences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bindViews()
        setupSharedPreference()
    }

    private fun bindViews() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName = findViewById(R.id.editTextUserName)
        buttonLogin = findViewById(R.id.buttonLogin)
        val clickAction = object : View.OnClickListener{
            override fun onClick(v: View?){
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()
                if (fullName.isNotEmpty() && userName.isNotEmpty()){
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(AppConstant.FULL_NAME, fullName)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginState()
                }else{

                }
            }

        }
        buttonLogin.setOnClickListener(clickAction)
    }

    private fun saveLoginState() {
        editor = sharedPrefrences.edit()
        editor.putBoolean(PrefConstant.IS_LOGGED_IN,true)
        editor.apply()
    }

    private fun saveFullName(fullName: String) {
        editor = sharedPrefrences.edit()
        editor.putString(PrefConstant.FULL_NAME, fullName)
        editor.apply()
    }

    private fun setupSharedPreference() {
        sharedPrefrences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}