package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
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
            user.id.length !in ID_MIN_LENGTH..ID_MAX_LENGTH -> shortToast(R.string.sign_up_id_format_error)

            user.password.length !in PW_MIN_LENGTH..PW_MAX_LENGTH -> shortToast(R.string.sign_up_password_format_error)

            user.nickName.isEmpty() || user.mbti.isEmpty() -> shortToast(R.string.sign_up_check_format)

            else -> {
                shortToast(R.string.sign_up_success)
                navigateToLogin(user)
            }
        }
    }

    private fun navigateToLogin(updateUser: User) {
        intent.putExtra(USER, updateUser)
        setResult(RESULT_OK, intent)
        finish()
    }

    companion object {
        private const val ID_MIN_LENGTH = 6
        private const val ID_MAX_LENGTH = 10
        private const val PW_MIN_LENGTH = 8
        private const val PW_MAX_LENGTH = 12
    }

}