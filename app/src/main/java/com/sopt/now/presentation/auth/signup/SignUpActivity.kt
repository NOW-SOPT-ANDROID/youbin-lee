package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.MEMBER_ID
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.shortToast

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpClickListener()
        observeSignUpFormat()
    }

    private fun initSignUpClickListener() {
        binding.btnSignUpSignup.setOnClickListener {
            signUpViewModel.setUser(with(binding) {
                User(
                    etSignUpId.text.toString().trim(),
                    etSignUpPw.text.toString().trim(),
                    etSignUpNickname.text.toString().trim(),
                    etSignUpMbti.text.toString().trim(),
                )
            })
            signUpViewModel.checkSignUpAvailable()
        }
    }

    private fun observeSignUpFormat() {
        signUpViewModel.signUpState.observe(this) { state ->
            when (state) {
                is AuthState.Success -> {
                    shortToast(R.string.sign_up_success)
                    navigateToLogin(signUpViewModel.getUser())
                }

                is AuthState.InputError ->
                    shortToast(R.string.sign_up_format_error)

                is AuthState.Failure -> {
                    shortToast(R.string.server_error)
                }
            }
        }
    }

    private fun navigateToLogin(user: User) {
        intent.apply {
            putExtra(USER, user)
            putExtra(MEMBER_ID, signUpViewModel.getMemberId())
            setResult(RESULT_OK, this)
        }
        finish()
    }

}