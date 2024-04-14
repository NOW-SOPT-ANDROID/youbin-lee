package com.sopt.now.presentation.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.R
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemFriendMusicBinding
import com.sopt.now.databinding.ItemFriendProfileBinding
import com.sopt.now.databinding.ItemMyProfileBinding

class HomeAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var friendList: List<FriendInfo> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(viewType, parent, false)

        return when (viewType) {
            R.layout.item_friend_profile -> FriendProfileViewHolder(
                ItemFriendProfileBinding.bind(view),
            )

            R.layout.item_friend_music -> FriendMusicViewHolder(
                ItemFriendMusicBinding.bind(view),
            )

            else -> MyProfileViewHolder(
                ItemMyProfileBinding.bind(view),
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendList[position]

        when (holder) {
            is FriendProfileViewHolder -> holder.onBind(item as FriendInfo.FriendProfile)
            is MyProfileViewHolder -> holder.onBind(item as FriendInfo.MyProfile)
            is FriendMusicViewHolder -> holder.onBind(item as FriendInfo.FriendMusic)
        }
    }

    override fun getItemCount() = friendList.size
    fun setFriendList(friendList: List<FriendInfo>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (friendList[position]) {
        is FriendInfo.MyProfile -> R.layout.item_my_profile
        is FriendInfo.FriendProfile -> R.layout.item_friend_profile
        is FriendInfo.FriendMusic -> R.layout.item_friend_music
    }

}