package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.View
import com.sopt.now.R
import com.sopt.now.domain.entity.UserEntity
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.util.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private var userEntity: UserEntity? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getUserData()
        setUserData()
    }

    private fun getUserData() {
        userEntity = arguments?.getParcelable(USER)
    }

    private fun setUserData() {
        with(binding) {
            tvMyPageNickname.text = userEntity?.nickname
            tvMyPageId.text = userEntity?.id
            tvMyPageMbti.text = userEntity?.mbti
        }
    }
}