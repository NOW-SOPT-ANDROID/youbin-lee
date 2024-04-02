package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.shortToast

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpClickListener()
    }

    private fun initSignUpClickListener() {
        binding.btnSignUpSignup.setOnClickListener {
            user = createUser()
            checkSignUpFormat(user)
        }
    }

    private fun createUser(): User {
        with(binding) {
            return User(
                etSignUpId.text.toString(),
                etSignUpPw.text.toString(),
                etSignUpNickname.text.toString(),
                etSignUpMbti.text.toString(),
            )
        }
    }

    private fun checkSignUpFormat(user: User) {
        when {
            user.id.length !in 6..10 -> shortToast(getString(R.string.sign_up_id_format_error))

            user.password.length !in 8..12 -> shortToast(getString(R.string.sign_up_password_format_error))

            user.nickName.isEmpty() || user.mbti.isEmpty() -> shortToast(getString(R.string.sign_up_check_format))

            else -> {
                shortToast(getString(R.string.sign_up_success))
                finish()
            }
        }
    }

}