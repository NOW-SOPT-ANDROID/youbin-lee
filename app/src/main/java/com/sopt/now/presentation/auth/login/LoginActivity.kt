package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.sopt.now.R
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.AuthState
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.getParcelable
import com.sopt.now.util.extension.shortToast
import com.sopt.now.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSignUpActivityLauncher()
        initSignUpClickListener()
        initLoginClickListener()
        observeLoginFormat()
    }

    private fun setSignUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                with(loginViewModel) {
                    setUser(
                        result.data?.getParcelable(USER, User::class.java)
                            ?: return@registerForActivityResult
                    )
                }

            }
        }
    }

    private fun initSignUpClickListener() {
        binding.btnLoginSignUp.setOnClickListener {
            Intent(this, SignUpActivity::class.java).apply {
                resultLauncher.launch(this)
            }
        }
    }

    private fun initLoginClickListener() {
        binding.btnLoginLogin.setOnClickListener {
            loginViewModel.checkLoginAvailable(
                binding.etLoginId.text.toString(),
                binding.etLoginPw.text.toString()
            )
        }
    }

    private fun observeLoginFormat() {
        loginViewModel.loginState.observe(this) { state ->
            when (state) {
                is AuthState.Success -> {
                    toast(getString(R.string.login_success, loginViewModel.getMemberId()))
                    navigateToMain(loginViewModel.getUser())
                }

                is AuthState.InputError -> shortToast(R.string.sign_up_format_error)

                is AuthState.Failure -> shortToast(R.string.server_error)
            }
        }
    }

    private fun navigateToMain(user: User) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(USER, user)
            putExtra(MEMBER_ID, loginViewModel.getMemberId())
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    companion object {
        const val USER = "User"
        const val MEMBER_ID = "memberId"
    }
}