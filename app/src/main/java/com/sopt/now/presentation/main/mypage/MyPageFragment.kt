package com.sopt.now.presentation.main.mypage

import android.os.Bundle
import android.view.View
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.FragmentMyPageBinding
import com.sopt.now.util.base.BaseFragment

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private lateinit var user: User

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        getUserData()
//        setUserData()
    }

//    private fun getUserData() {
//        user = intent.getParcelable(LoginActivity.USER, User::class.java) ?: return
//    }

//    private fun setUserData() {
//        with(binding) {
//            tvMyPageNickname.text = user.nickname
//            tvMyPageId.text = user.id
//            tvMyPageMbti.text = user.mbti
//        }
//    }
}