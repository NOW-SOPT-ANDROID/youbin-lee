package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemMyProfileBinding

class MyProfileViewHolder(private val binding: ItemMyProfileBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: FriendInfo.MyProfile) {
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
        }
    }
}