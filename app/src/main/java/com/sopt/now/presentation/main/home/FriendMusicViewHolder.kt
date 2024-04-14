package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemFriendMusicBinding

class FriendMusicViewHolder(private val binding: ItemFriendMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(friendData: FriendInfo.FriendMusic) {
        with(binding) {
            ivProfile.setImageResource(friendData.profileImage)
            tvName.text = friendData.name
            tvMusicTitle.text = friendData.music
        }
    }
}