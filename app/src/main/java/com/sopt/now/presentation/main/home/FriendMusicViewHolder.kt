package com.sopt.now.presentation.main.home

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemFriendMusicBinding

class FriendMusicViewHolder(private val binding: ItemFriendMusicBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(item: FriendInfo.FriendProfile) {
        with(binding) {
            ivProfile.load(item.profileImage)
            tvName.text = item.name
            tvMusicTitle.text = item.music
        }
    }
}