package com.sopt.now.presentation.main.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.FriendInfo
import com.sopt.now.databinding.ItemFriendMusicBinding
import com.sopt.now.databinding.ItemFriendProfileBinding
import com.sopt.now.databinding.ItemMyProfileBinding

class HomeAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var friendList: List<FriendInfo> = emptyList()

    private val inflater by lazy { LayoutInflater.from(context) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            VIEW_TYPE_MY_PROFILE -> MyProfileViewHolder(
                ItemMyProfileBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            VIEW_TYPE_FRIEND_PROFILE -> FriendProfileViewHolder(
                ItemFriendProfileBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )

            else -> FriendMusicViewHolder(
                ItemFriendMusicBinding.inflate(
                    inflater,
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = friendList[position]

        when (holder) {
            is MyProfileViewHolder -> holder.onBind(item as FriendInfo.MyProfile)
            is FriendProfileViewHolder -> holder.onBind(item as FriendInfo.FriendProfile)
            is FriendMusicViewHolder -> holder.onBind(item as FriendInfo.FriendProfile)
        }
    }

    override fun getItemCount() = friendList.size
    fun setFriendList(friendList: List<FriendInfo>) {
        this.friendList = friendList.toList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int) = when (friendList[position]) {
        is FriendInfo.MyProfile -> VIEW_TYPE_MY_PROFILE
        is FriendInfo.FriendProfile -> if ((friendList[position] as FriendInfo.FriendProfile).music.isNullOrBlank()) VIEW_TYPE_FRIEND_PROFILE else VIEW_TYPE_FRIEND_MUSIC
    }

    companion object {
        private const val VIEW_TYPE_MY_PROFILE = 0
        private const val VIEW_TYPE_FRIEND_PROFILE = 1
        private const val VIEW_TYPE_FRIEND_MUSIC = 2
    }

}