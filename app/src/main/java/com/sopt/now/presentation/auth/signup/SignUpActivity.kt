package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivitySignUpBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.shortToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SignUpActivity : BaseActivity<ActivitySignUpBinding>(R.layout.activity_sign_up) {

    private val signUpViewModel by viewModels<SignUpViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSignUpClickListener()
        observeSignUpFormat()
    }

    private fun initSignUpClickListener() {
        binding.btnSignUpSignup.setOnClickListener {
            Log.d("LYB", "유저 객체 셋해줌")
            signUpViewModel.setUser(with(binding) {
                User(
                    etSignUpId.text.toString().trim(),
                    etSignUpPw.text.toString().trim(),
                    etSignUpNickname.text.toString().trim(),
                    etSignUpMbti.text.toString().trim(),
                )
            })
            signUpViewModel.checkSignUpFormat()
        }
    }

    private fun observeSignUpFormat() {
        signUpViewModel.signUpState.flowWithLifecycle(lifecycle).onEach { signUpState ->
            Log.d("LYB", "현재 확인하는 중")
            when (signUpState) {
                is SignUpState.IdError -> shortToast(R.string.sign_up_id_format_error)
                is SignUpState.PwError -> shortToast(R.string.sign_up_pw_format_error)
                is SignUpState.BlankError -> shortToast(R.string.sign_up_nickname_mbti_format_error)
                is SignUpState.Success -> {
                    shortToast(R.string.sign_up_success)
                    navigateToLogin(signUpViewModel.getUser())
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToLogin(updateUser: User) {
        intent.putExtra(USER, updateUser)
        setResult(RESULT_OK, intent)
        finish()
    }

}