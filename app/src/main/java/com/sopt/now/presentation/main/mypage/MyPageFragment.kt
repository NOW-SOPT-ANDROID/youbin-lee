package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.View
import com.sopt.now.R
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.presentation.User
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private var user: User? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserData()
        setUserData()
    }

    private fun getUserData() {
        user = arguments?.getParcelable(USER)
    }

    private fun setUserData() {
        with(binding) {
            tvMyPageNickname.text = user?.nickname
            tvMyPageId.text = user?.id
            tvMyPageMbti.text = user?.phone
        }
    }
}