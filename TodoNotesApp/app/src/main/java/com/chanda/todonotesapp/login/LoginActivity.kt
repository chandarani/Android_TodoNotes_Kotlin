package com.chanda.todonotesapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.chanda.todonotesapp.utils.AppConstant
import com.chanda.todonotesapp.data.local.pref.PrefConstant
import com.chanda.todonotesapp.R
import com.chanda.todonotesapp.data.local.pref.StoreSession
import com.chanda.todonotesapp.mynotes.MyNotesActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var editTextFullName: EditText
    private lateinit var editTextUserName: EditText
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupSharedPreference()
        bindViews()
        setupClickListeners()
    }

    private fun setupClickListeners() {
        val clickAction = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val fullName = editTextFullName.text.toString()
                val userName = editTextUserName.text.toString()
                if (fullName.isNotEmpty() && userName.isNotEmpty()) {
                    val intent = Intent(this@LoginActivity, MyNotesActivity::class.java)
                    intent.putExtra(AppConstant.FULL_NAME, fullName)
                    startActivity(intent)
                    saveFullName(fullName)
                    saveLoginState()
                }
            }
        }
        buttonLogin.setOnClickListener(clickAction)
    }

    private fun setupSharedPreference() {
        StoreSession.init(this)
    }

    private fun bindViews() {
        editTextFullName = findViewById(R.id.editTextFullName)
        editTextUserName = findViewById(R.id.editTextUserName)
        buttonLogin = findViewById(R.id.buttonLogin)
    }

    private fun saveLoginState() {
        StoreSession.write(PrefConstant.IS_LOGGED_IN, true)
    }

    private fun saveFullName(fullName: String) {
        StoreSession.write(PrefConstant.FULL_NAME, fullName)
    }
}
