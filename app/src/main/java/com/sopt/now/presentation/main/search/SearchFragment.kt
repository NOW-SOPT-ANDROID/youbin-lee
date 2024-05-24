package com.sopt.now.presentation.main.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.sopt.now.R
import com.sopt.now.databinding.FragmentSearchBinding
import com.sopt.now.domain.entity.response.FollowerResponseModel
import com.sopt.now.util.UiState
import com.sopt.now.util.base.BaseFragment
import com.sopt.now.util.extension.shortToast
import com.sopt.now.util.extension.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var followerAdapter: SearchAdapter
    private val followerViewModel: SearchViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initFollowerList()
        observeFollowerState()
    }

    private fun initAdapter() {
        followerAdapter = SearchAdapter(requireContext())
        binding.rvSearch.adapter = followerAdapter
    }

    private fun initFollowerList() {
        followerViewModel.getFollowerListFromServer(PAGE)
    }

    private fun observeFollowerState() {
        followerViewModel.followerState.flowWithLifecycle(lifecycle).onEach { followerState ->
            when (followerState) {
                is UiState.Success -> {
                    val followerData = followerState.data
                    setFollowerList(followerData)
                }

                is UiState.Failure -> toast(followerState.msg)

                is UiState.Loading -> shortToast(R.string.ui_state_loading)

            }
        }.launchIn(lifecycleScope)
    }

    private fun setFollowerList(followerData: List<FollowerResponseModel>) {
        followerAdapter.setFollowerList(followerData)
    }

    companion object {
        private const val PAGE = 2
    }

}
