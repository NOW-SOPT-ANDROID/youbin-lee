package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.getParcelable
import com.sopt.now.util.extension.shortToast
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

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
                loginViewModel.setUser(
                    result.data?.getParcelable(USER, UserEntity::class.java)
                        ?: return@registerForActivityResult
                )
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
            with(loginViewModel) {
                checkIdInput(binding.etLoginId.text.toString())
                checkPwInput(binding.etLoginPw.text.toString())
                checkLoginFormat()
            }
        }
    }

    private fun observeLoginFormat() {
        loginViewModel.loginState.flowWithLifecycle(lifecycle).onEach { loginState ->
            when (loginState) {
                is LoginState.IdError -> shortToast(R.string.login_id_error)
                is LoginState.PwError -> shortToast(R.string.login_pw_error)
                is LoginState.Success -> {
                    shortToast(R.string.login_success)
                    navigateToMain(loginViewModel.getUser())
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun navigateToMain(userEntity: UserEntity) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(USER, userEntity)
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    companion object {
        const val USER = "User"
    }
}