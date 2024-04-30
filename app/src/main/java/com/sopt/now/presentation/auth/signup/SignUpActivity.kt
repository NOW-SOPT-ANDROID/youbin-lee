package com.sopt.now.presentation.auth.signup

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.domain.entity.UserEntity
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
            signUpViewModel.setUser(with(binding) {
                UserEntity(
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

    private fun navigateToLogin(updateUserEntity: UserEntity) {
        intent.putExtra(USER, updateUserEntity)
        setResult(RESULT_OK, intent)
        finish()
    }

}