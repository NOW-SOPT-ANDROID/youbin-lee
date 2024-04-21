package com.sopt.now.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sopt.now.R
import com.sopt.now.data.User
import com.sopt.now.databinding.ActivityMainBinding
import com.sopt.now.presentation.auth.login.LoginActivity.Companion.USER
import com.sopt.now.presentation.main.home.HomeFragment
import com.sopt.now.presentation.main.mypage.MyPageFragment
import com.sopt.now.presentation.main.search.SearchFragment
import com.sopt.now.util.base.BaseActivity
import com.sopt.now.util.extension.getParcelable


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBnvItemClickListener()
        getUserData()
    }

    private fun initBnvItemClickListener() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.fcv_main)
        if (currentFragment == null || currentFragment !is HomeFragment) {
            replaceFragment(HomeFragment())
        }

        binding.bnvMain.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.menu_search -> {
                    replaceFragment(SearchFragment())
                    true
                }

                R.id.menu_my_page -> {
                    val myPageFragment = MyPageFragment().apply {
                        arguments = Bundle().apply {
                            putParcelable(USER, user)
                        }
                    }
                    replaceFragment(myPageFragment)
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv_main, fragment)
            .commit()
    }

    private fun getUserData() {
        user = intent.getParcelable(USER, User::class.java) ?: return
    }

}