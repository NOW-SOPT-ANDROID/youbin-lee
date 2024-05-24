package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.MEMBER_ID
import com.sopt.now.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val myPageViewModel by viewModels<MyPageViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMemberId()
        observeUserInfo()
    }

    private fun setMemberId() {
        myPageViewModel.getUserInfo(arguments?.getString(MEMBER_ID)?.toInt() ?: 0)
    }

    private fun observeUserInfo() {
        myPageViewModel.myPageState.flowWithLifecycle(lifecycle).onEach { myPageState ->
            when (myPageState) {
                true -> setUserData()
                false -> return@onEach
            }
        }.launchIn(lifecycleScope)
    }

    private fun setUserData() {
        with(binding) {
            tvMyPageId.text = myPageViewModel.id
            tvMyPageNickname.text = myPageViewModel.id
            tvMyPagePhone.text = myPageViewModel.phone
        }
    }
}