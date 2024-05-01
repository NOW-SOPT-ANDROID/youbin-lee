package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.UiState
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.shortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                is UiState.Success -> {
                    shortToast(R.string.sign_up_success)
                    navigateToLogin(signUpViewModel.getUser())
                }

                is UiState.Failure -> {
                    Log.d("LYB", state.msg)
                    Toast.makeText(this, (state.msg), Toast.LENGTH_SHORT).show()
                }
                is UiState.Loading -> return@observe
            }

        }
    }

    private fun navigateToLogin(user: User) {
        intent.putExtra(USER, user)
        setResult(RESULT_OK, intent)
        finish()
    }

}