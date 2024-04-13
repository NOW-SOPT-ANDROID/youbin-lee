package com.sopt.now.presentation.main

import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.getParcelable


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getUserData()
        setUserData()
    }

    private fun getUserData() {
        user = intent.getParcelable(USER, User::class.java) ?: return
    }

    private fun setUserData() {
        with(binding) {
            tvMyPageNickname.text = user.nickname
            tvMyPageId.text = user.id
            tvMyPageMbti.text = user.mbti
        }
    }

}