package com.sopt.now.presentation.main.search

import android.os.Bundle
import android.view.View
import com.sopt.now.R
import com.sopt.now.databinding.FragmentSearchBinding
import com.sopt.now.util.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}