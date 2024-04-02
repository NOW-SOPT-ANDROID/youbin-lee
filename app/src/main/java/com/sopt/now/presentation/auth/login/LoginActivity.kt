package com.sopt.now.presentation.auth.login

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityLoginBinding
import com.sopt.now.presentation.auth.signup.SignUpActivity
import com.sopt.now.presentation.main.MainActivity
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.getParcelable
import com.sopt.now.util.extension.shortToast

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private lateinit var resultLauncher: ActivityResultLauncher<Intent>
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        signUpActivityLauncher()
        initSignUpClickListener()
        initLoginClickListener()
    }

    private fun signUpActivityLauncher() {
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                user = result.data?.getParcelable(USER, User::class.java)
                    ?: return@registerForActivityResult
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
            when {
                !::user.isInitialized -> shortToast(getString(R.string.login_is_not_initialized))
                checkIdnAvailable(user) -> shortToast(getString(R.string.login_id_fail))
                checkPwAvailable(user) -> shortToast(getString(R.string.login_pw_fail))
                else -> {
                    shortToast(getString(R.string.login_success))
                    navigateToMain(user)
                }
            }
        }
    }

    private fun checkIdnAvailable(user: User): Boolean =
        binding.etLoginId.text.toString() != user.id

    private fun checkPwAvailable(user: User): Boolean =
        binding.etLoginPw.text.toString() != user.password

    private fun navigateToMain(user: User) {
        Intent(this, MainActivity::class.java).apply {
            putExtra(USER, user)
            addFlags(FLAG_ACTIVITY_CLEAR_TASK or FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
        }
    }

    companion object {
        const val USER = "User"
    }
}