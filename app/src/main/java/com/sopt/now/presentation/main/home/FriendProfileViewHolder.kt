package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemFriendProfileBinding

class FriendProfileViewHolder(private val binding: ItemFriendProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: FriendInfo.FriendProfile) {
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
        }
    }
}