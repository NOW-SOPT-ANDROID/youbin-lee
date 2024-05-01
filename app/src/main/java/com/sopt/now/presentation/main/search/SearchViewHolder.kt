package com.sopt.now.presentation.main.search

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.R
import com.sopt.now.databinding.ItemFollowerBinding
import com.sopt.now.domain.entity.response.FollowerResponseModel

class SearchViewHolder(private val binding: ItemFollowerBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(followerData: FollowerResponseModel) {
        with(binding) {
            tvFollowerName.text = followerData.first_name
            tvFollowerEmail.text = followerData.email
            imgFollowerProfile.load(followerData.avatar) {
                placeholder(R.drawable.img_main_profile)
                error(R.drawable.img_main_profile)
            }
        }
    }
}