package com.sopt.now.presentation.main.search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.data.service.FollowerService
import com.sopt.now.databinding.ItemFollowerBinding
import com.sopt.now.domain.entity.response.FollowerResponseModel

class SearchAdapter(context: Context) :
    RecyclerView.Adapter<SearchViewHolder>() {

    private val inflater by lazy { LayoutInflater.from(context) }
    private var followerList = mutableListOf<FollowerResponseModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(ItemFollowerBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount() = followerList.size

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(followerList[position])
    }

    fun setFollowerList(followerData: List<FollowerResponseModel>) {
        followerList.clear()
        followerList.addAll(followerData)
        notifyDataSetChanged()
    }
}