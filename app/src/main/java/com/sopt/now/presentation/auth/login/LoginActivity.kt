package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.util.base.BaseActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLoginClickListener()
        initSignUpClickListener()
    }

    private fun initLoginClickListener() {
        binding.btnLoginLogin.setOnClickListener {

        }
    }

    private fun initSignUpClickListener() {
        binding.btnLoginSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                startActivity(this)
            }
        }
    }

}